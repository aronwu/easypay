package com.wuswoo.easypay.http.server;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.http.router.RouteResult;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxinjun on 16/9/21.
 */
public class Request implements FullHttpRequest {
    private static final Logger logger = LogManager.getLogger(Request.class);

    private final Channel channel;
    private final RouteResult<String> routeResult;
    private final String clientIp;
    private final String remoteIp;

    private final FullHttpRequest request;


    /**
     * Set if the request content type is
     * <code>application/x-www-form-urlencoded</code>.
     */
    private final Map<String, List<String>> formParams;
    private final JSONObject json;

    private final String controllerMethod;
    private final String controllerBeanId;

    private Object contract;

    public Request(Channel channel, FullHttpRequest request, RouteResult<String> routeResult) {
        this.channel = channel;
        this.request = request;
        this.routeResult = routeResult;

        clientIp = getClientIpFromChannel();
        remoteIp = getRemoteIpFromClientIpOrReverseProxy();
        String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if (HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString()
            .equalsIgnoreCase(contentType)) {
            String content = request.content().toString(Feature.charset);
            QueryStringDecoder qsd = new QueryStringDecoder("?" + content);
            formParams = qsd.parameters();
        } else {
            formParams = Collections.<String, List<String>>emptyMap();
        }
        if (HttpHeaderValues.APPLICATION_JSON.toString().equalsIgnoreCase(contentType)) {
            String content = request.content().toString(Feature.charset);
            json = JSONObject.parseObject(content);
        } else {
            json = null;
        }
        logger.info("Request uri: {}", request.uri());
        if (json != null) {
            logger.info("Request json: {}", json);
        } else if (formParams != null && formParams.size() > 0) {
            logger.info("Request form: {}", JSONObject.toJSONString(formParams));
        }
        //初始化控制器方法和控制器Bean
        if (routeResult != null) {
            String target = routeResult.target();
            int index = target.indexOf('@');
            if (index > 0) {
                controllerBeanId = target.substring(0, index);
                controllerMethod = target.substring(index + 1);
            } else {
                controllerBeanId = target;
                controllerMethod = "index";
            }
        } else {
            controllerBeanId = null;
            controllerMethod = null;
        }
        contract = null;
    }

    public Object getContract() {
        return contract;
    }

    public void setContract(Object contract) {
        this.contract = contract;
    }

    public String clientIp() {
        return clientIp;
    }

    public String remoteIp() {
        return remoteIp;
    }

    public Channel channel() {
        return channel;
    }

    public String target() {
        return routeResult.target();
    }

    public Map<String, String> pathParams() {
        return routeResult.pathParams();
    }

    public String pathParam(String name) {
        return routeResult.pathParams().get(name);
    }

    public Map<String, List<String>> queryParams() {
        return routeResult.queryParams();
    }

    public String queryParam(String name) {
        return routeResult.queryParam(name);
    }


    public Map<String, List<String>> formParams() {
        return formParams;
    }

    public List<String> formParam(String name) {
        return formParams.get(name);
    }

    public String param(String name) {
        return routeResult.param(name);
    }

    public JSONObject json() {
        return json;
    }

    public String controllerMethod() {
        return controllerMethod;
    }

    public String controllerBeanId() {
        return controllerBeanId;
    }

    public String uri() {
        return request.uri();
    }

    public HttpMethod method() {
        return request.method();
    }

    public String contentType() {
        return request.headers().get(HttpHeaderNames.CONTENT_TYPE);
    }

    private String getRemoteIpFromClientIpOrReverseProxy() {
        if (request.headers().get("HTTP_X_FORWARDED_HOST") != null) {
            return request.headers().get("HTTP_X_FORWARDED_HOST");
        }
        return getClientIpFromChannel();
    }

    private String getClientIpFromChannel() {
        SocketAddress remoteAddress = channel.remoteAddress();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) remoteAddress;
        InetAddress addr = inetSocketAddress.getAddress();
        return addr.getHostAddress();
    }

    //----------------------------------------------------------------------------
    // Implement FullHttpRequest

    @Override
    public HttpMethod getMethod() {
        return request.method();
    }

    @Override
    public String getUri() {
        return request.uri();
    }

    @Override
    public HttpVersion getProtocolVersion() {
        return request.protocolVersion();
    }

    @Override
    public HttpHeaders headers() {
        return request.headers();
    }

    @Override
    public DecoderResult decoderResult() {
        return request.decoderResult();
    }

    @Override
    public HttpHeaders trailingHeaders() {
        return request.trailingHeaders();
    }

    @Override
    public FullHttpRequest duplicate() {
        return request.duplicate();
    }

    @Override
    public ByteBuf content() {
        return request.content();
    }

    @Override
    public int refCnt() {
        return request.refCnt();
    }

    @Override
    public boolean release() {
        return request.release();
    }

    @Override
    public boolean release(int arg0) {
        return request.release(arg0);
    }

    @Override
    public FullHttpRequest copy() {
        return request.copy();
    }

    @Override
    public FullHttpRequest retain() {
        return request.retain();
    }

    @Override
    public FullHttpRequest retain(int arg0) {
        return request.retain(arg0);
    }

    @Override
    public FullHttpRequest setMethod(HttpMethod arg0) {
        return request.setMethod(arg0);
    }

    @Override
    public FullHttpRequest setProtocolVersion(HttpVersion arg0) {
        return request.setProtocolVersion(arg0);
    }

    @Override
    public FullHttpRequest setUri(String arg0) {
        return request.setUri(arg0);
    }

    @Override
    public FullHttpRequest retainedDuplicate() {
        return request.retainedDuplicate();
    }

    @Override
    public FullHttpRequest replace(ByteBuf content) {
        return request.replace(content);
    }

    @Override
    public FullHttpRequest touch() {
        return request.touch();
    }

    @Override
    public FullHttpRequest touch(Object hint) {
        return request.touch(hint);
    }

    @Override
    public HttpVersion protocolVersion() {
        return request.protocolVersion();
    }

    @Override
    public DecoderResult getDecoderResult() {
        return request.decoderResult();
    }

    @Override
    public void setDecoderResult(DecoderResult arg0) {
        request.setDecoderResult(arg0);
    }

    @Override
    public String toString() {
        return request.toString();
    }
}

