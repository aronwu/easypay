package com.wuswoo.easypay.http.server;

import com.wuswoo.easypay.common.util.MyApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wuxinjun on 16/9/7.
 */
public class ShutdownThread extends Thread {
    private IServer server;
    private static Logger logger = LogManager.getLogger(ShutdownThread.class);

    public ShutdownThread(IServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        logger.info("start shutdown Thread");
        try {
            server.stop();
            MyApplicationContext.getInstance().destroy();
        } catch (Exception ex) {
            logger.info(ex.getMessage(), ex);
        }
        logger.info("end shutdown Thread");
    }
}
