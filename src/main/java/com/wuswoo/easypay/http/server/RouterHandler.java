package com.wuswoo.easypay.http.server;

import com.wuswoo.easypay.common.util.ExceptionUtil;
import com.wuswoo.easypay.http.controller.IController;
import com.wuswoo.easypay.http.controller.IControllerAdapter;
import com.wuswoo.easypay.http.exception.*;
import com.wuswoo.easypay.http.router.RouteResult;
import com.wuswoo.easypay.http.router.Router;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wuxinjun on 16/9/20.
 */
@ChannelHandler.Sharable
public class RouterHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LogManager.getLogger(RouterHandler.class);
    private static final Router<String> router = new Router<String>();
    private IControllerAdapter adapter;

    public static Router<String> getRouter() {
        return router;
    }

    public IControllerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(IControllerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        final Channel channel = ctx.channel();
        final RouteResult<String> routeResult = router.route(msg.method(), msg.uri());
        final Request request = new Request(channel, msg, routeResult);
        final Response response = new Response(channel, request);
        channel.closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                //                if (request.refCnt() > 0 ) request.release(request.refCnt());
                //                if (response.refCnt() > 0) response.release(response.refCnt());
            }
        });

        if (routeResult == null) {
            handleError(request, response, new MissingUriException(msg.uri()));
            return;
        }
        try {
            doAction(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof InvocationTargetException) {
                Exception cause = (Exception) ex.getCause();
                if (cause != null)
                    ex = cause;
            } else if (ex instanceof NoSuchMethodException) {
                ex = new MissingMethodException(request.controllerMethod(), ex);
            }
            handleError(request, response, ex);
        }
    }

    protected void doAction(Request request, Response response) throws Exception {
        String controllerBeanId = request.controllerBeanId();
        String controllerMethod = request.controllerMethod();
        IController adapterController = adapter.getControllerByBeanId(controllerBeanId);
        final Method method =
            adapterController.getClass().getMethod(controllerMethod, Request.class, Response.class);
        if (method != null)
            method.invoke(adapterController, request, response);
        else
            throw new MissingMethodException(controllerMethod);

    }

    protected void onUnknownMessage(Object msg) {
        logger.warn("Unknown msg: " + msg);
    }

    protected void onBadClient(Throwable e) {
        logger.warn("Caught exception (maybe client is bad)", e);
    }

    protected void onBadServer(Throwable e) {
        logger.warn("Caught exception (maybe server is bad)", e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        ctx.close();
        // To clarify where exceptions are from, imports are not used
        if (e instanceof java.io.IOException ||  // Connection reset by peer, Broken pipe
            e instanceof java.nio.channels.ClosedChannelException ||
            e instanceof io.netty.handler.codec.DecoderException ||
            e instanceof io.netty.handler.codec.CorruptedFrameException ||  // Bad WebSocket frame
            e instanceof IllegalArgumentException ||  // Use https://... to connect to HTTP server
            e instanceof javax.net.ssl.SSLException ||  // Use http://... to connect to HTTPS server
            e instanceof io.netty.handler.ssl.NotSslRecordException) {
            onBadClient(e);  // Maybe client is bad
        } else {
            onBadServer(e);  // Maybe server is bad
        }
    }

    protected void handleError(Request request, Response response, Exception e) throws Exception {
        if (e instanceof MissingParamException) {
            MissingParamException mp = (MissingParamException) e;
            response.setStatus(HttpResponseStatus.BAD_REQUEST);
            HttpExceptionResponse ex = new HttpExceptionResponse();
            ex.setCode(HttpResponseStatus.BAD_REQUEST.codeAsText().toString());
            ex.setMessage("Missing param: " + mp.param());
            ex.addError(ExceptionUtil.convertExcepitonToString(e));
            response.respondJson(ex);
        } else if (e instanceof MissingUriException) {
            MissingUriException mp = (MissingUriException) e;
            response.setStatus(HttpResponseStatus.BAD_REQUEST);
            HttpExceptionResponse ex = new HttpExceptionResponse();
            ex.setCode(HttpResponseStatus.BAD_REQUEST.codeAsText().toString());
            ex.setMessage("Missing uri: " + mp.uri());
            ex.addError(ExceptionUtil.convertExcepitonToString(e));
            response.respondJson(ex);
        } else if (e instanceof IExceptionToHttpResponse) {
            HttpExceptionResponse ex = ((IExceptionToHttpResponse) e).getHttpResponse();
            response.setStatus(
                new HttpResponseStatus(Integer.parseInt(ex.getCode()), "Internal Server Error"));
            response.respondJson(ex);
        } else {
            //统一格式化成HttpExceptionResponse输出
            logger.error("Server error: {}\nWhen processing request: {}", e, request);
            HttpExceptionResponse ex = new HttpExceptionResponse();
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            ex.setCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.codeAsText().toString());
            ex.setMessage("内部发生错误uri: " + request.getUri());
            ex.addError(ExceptionUtil.convertExcepitonToString(e));
            response.respondJson(ex);
        }
    }


}
