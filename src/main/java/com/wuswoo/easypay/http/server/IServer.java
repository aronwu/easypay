package com.wuswoo.easypay.http.server;

/**
 * Created by wuxinjun on 16/8/26.
 */
public interface IServer {
    public void init();

    public void start();

    public void stop();

    public String serverName();
}
