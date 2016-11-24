package com.wuswoo.easypay.http.server;

import java.io.Serializable;

/**
 * Created by wuxinjun on 16/8/26.
 */
public class ServerAddress implements Serializable {
    private static final long serialVersionUID = 5142207361335126959L;
    private String host;
    private int port;

    public ServerAddress(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ServerAddress(String hostAndPort) {
        int index = hostAndPort.indexOf(":");
        if (index < 0) {
            throw new IllegalArgumentException(
                "Must have a : in addressAndPort,got:" + hostAndPort);
        }
        this.host = hostAndPort.substring(0, index);
        this.port = Integer.parseInt(hostAndPort.substring(index + 1));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ServerAddress)) {
            return false;
        }
        ServerAddress n = (ServerAddress) obj;
        return (this.host == n.getHost()) && (this.port == n.getPort());
    }

    @Override
    public int hashCode() {
        return (host + ":" + port).hashCode();
    }

    @Override
    public String toString() {
        return (host + ":" + port);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
