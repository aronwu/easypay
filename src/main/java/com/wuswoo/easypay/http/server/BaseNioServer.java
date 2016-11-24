package com.wuswoo.easypay.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wuxinjun on 16/8/30.
 */
public abstract class BaseNioServer implements IServer {
    protected final Logger logger = LogManager.getLogger(BaseNioServer.class);
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private ServerBootstrap bootstrap = null;
    private ServerAddress serverAddress = null;

    public BaseNioServer() {

    }

    @Override
    public void init() {

    }

    @Override
    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            bootstrap = new ServerBootstrap();
            serverAddress = getServerAddress();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024).childHandler(getChannelInitializer());
            ChannelFuture f =
                bootstrap.bind(serverAddress.getHost(), serverAddress.getPort()).sync();
            System.out.println("start to listen on" + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.stop();
        }
    }

    @Override
    public void stop() {
        if (bossGroup != null && workerGroup != null) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        if (bootstrap != null) {
            //TODO
        }
    }

    @Override
    public String serverName() {
        return "BaseNioServer";
    }

    protected abstract ServerAddress getServerAddress();

    protected abstract ChannelInitializer<SocketChannel> getChannelInitializer();

}
