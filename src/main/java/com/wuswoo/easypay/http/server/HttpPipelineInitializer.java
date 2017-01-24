package com.wuswoo.easypay.http.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by wuxinjun on 16/8/30.
 */
public class HttpPipelineInitializer extends ChannelInitializer<SocketChannel> {

    private RouterHandler routerHandler;

    public HttpPipelineInitializer(RouterHandler routerHandler) {
        this.routerHandler = routerHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast("aggegator", new HttpObjectAggregator(512 * 1024));
        pipeline.addLast("routerHandler", routerHandler);
    }

}
