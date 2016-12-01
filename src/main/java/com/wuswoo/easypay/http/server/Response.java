package com.wuswoo.easypay.http.server;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.common.util.TimeCost;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wuxinjun on 16/9/21.
 */
public class Response implements FullHttpResponse {
    private static final Logger logger = LogManager.getLogger(Response.class);
    private final FullHttpResponse response;
    private final Request request;
    private final Channel channel;
    private TimeCost timeCost;

    public Response(Channel channel, Request request) {
        this.request = request;
        this.channel = channel;
        response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        timeCost = new TimeCost();
        timeCost.start();
    }

    //----------------------------------------------------------------------------
    public ChannelFuture respondText(Object text) throws Exception {
        return respondText(text, null);
    }

    public long cost() {
        return timeCost.cost();
    }

    public ChannelFuture respondText(Object text, String fallbackContentType) throws Exception {
        final String respondedText = text.toString();
        if (!response.headers().contains(HttpHeaderNames.CONTENT_TYPE)) {
            String charset = Feature.charset.displayName();
            if (fallbackContentType != null) {
                // https://developers.google.com/speed/docs/best-practices/rendering#SpecifyCharsetEarly
                final String withCharset =
                    fallbackContentType.toLowerCase().contains("charset") ?
                        fallbackContentType :
                        fallbackContentType + "; charset=" + charset;
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, withCharset);
            } else {
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=" + charset);
            }
        }
        logger.info("Response: {}", respondedText);
        ByteBuf buf = Unpooled.copiedBuffer(respondedText,Feature.charset );
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
        response.content().writeBytes(buf);
        return channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /** xml Content-Type 是  "application/xml". */
    public ChannelFuture respondXml(Object any) throws Exception {
        return respondText(any, "application/xml");
    }

    /** html Content-Type 是  "text/html". */
    public ChannelFuture respondHtml(Object any) throws Exception {
        return respondText(any, "text/html");
    }

    /** js Content-Type 是 "application/javascript". */
    public ChannelFuture respondJs(Object any) throws Exception {
        return respondText(any, "application/javascript");
    }

    /** 对象 Content-Type 是 "application/json". */
    public ChannelFuture respondJsonText(Object any) throws Exception {
        return respondText(any, "application/json");
    }

    /** json Content-Type 是 "application/json". */
    public ChannelFuture respondJson(Object ref) throws Exception {
        final String json = JSONObject.toJSONString(ref, Feature.RESPONSE_JSON_SERIAL_FEATURE);
        return respondText(json, "application/json");
    }

    /** jsonp Content-Type 是 "application/javascript". */
    public ChannelFuture respondJsonP(Object ref, String function) throws Exception {
        final String json = JSONObject.toJSONString(ref, Feature.RESPONSE_JSON_SERIAL_FEATURE);
        final String text = function + "(" + json + ");\r\n";
        return respondJs(text);
    }

    public ChannelFuture respondJsonPText(Object text, String function) throws Exception {
        return respondJs(function + "(" + text + ");\r\n");
    }

    //----------------------------------------------------------------------------

    /** 默认 Content-Type 是 "application/octet-stream". */
    public ChannelFuture respondBinary(byte[] bytes) throws Exception {
        return respondBinary(Unpooled.wrappedBuffer(bytes));
    }

    /** 默认 Content-Type 是 "application/octet-stream". */
    public ChannelFuture respondBinary(ByteBuf byteBuf) throws Exception {
        if (!response.headers().contains(HttpHeaderNames.CONTENT_TYPE))
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/octet-stream");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        response.content().writeBytes(byteBuf);
        return channel.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public FullHttpResponse copy() {
        return response.copy();
    }

    @Override
    public FullHttpResponse duplicate() {
        return response.duplicate();
    }

    @Override
    public FullHttpResponse retainedDuplicate() {
        return response.retainedDuplicate();
    }

    @Override
    public FullHttpResponse replace(ByteBuf content) {
        return response.replace(content);
    }

    @Override
    public FullHttpResponse retain(int increment) {
        return response.retain(increment);
    }

    @Override
    public FullHttpResponse retain() {
        return response.retain();
    }

    @Override
    public FullHttpResponse touch() {
        return response.touch();
    }

    @Override
    public FullHttpResponse touch(Object hint) {
        return response.touch(hint);
    }

    @Override
    public FullHttpResponse setProtocolVersion(HttpVersion version) {
        return response.setProtocolVersion(version);
    }

    @Override
    public FullHttpResponse setStatus(HttpResponseStatus status) {
        return response.setStatus(status);
    }

    @Override
    public HttpHeaders trailingHeaders() {
        return response.trailingHeaders();
    }

    @Override
    public ByteBuf content() {
        return response.content();
    }

    @Override
    public HttpResponseStatus getStatus() {
        return response.status();
    }

    @Override
    public HttpResponseStatus status() {
        return response.status();
    }

    @Override
    public HttpVersion getProtocolVersion() {
        return response.protocolVersion();
    }

    @Override
    public HttpVersion protocolVersion() {
        return response.protocolVersion();
    }

    @Override
    public HttpHeaders headers() {
        return response.headers();
    }

    @Override
    public DecoderResult getDecoderResult() {
        return response.decoderResult();
    }

    @Override
    public DecoderResult decoderResult() {
        return response.decoderResult();
    }

    @Override
    public void setDecoderResult(DecoderResult result) {
        response.setDecoderResult(result);
    }

    @Override
    public int refCnt() {
        return response.refCnt();
    }

    @Override
    public boolean release() {
        return response.release();
    }

    @Override
    public boolean release(int decrement) {
        return response.release(decrement);
    }
}
