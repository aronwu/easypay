package com.wuswoo.easypay.main;

import com.wuswoo.easypay.common.util.MyApplicationContext;
import com.wuswoo.easypay.common.util.ThreadPoolUtil;
import com.wuswoo.easypay.http.controller.IController;
import com.wuswoo.easypay.http.controller.IControllerAdapter;
import com.wuswoo.easypay.http.server.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Property;
import weixin.popular.client.LocalHttpClient;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wuxinjun on 16/8/31.
 */
public class EasyPaymentApp extends BaseNioServer {

    private static final Logger logger = LogManager.getLogger(EasyPaymentApp.class);

    private static int port = 12001;

    static
    {
        //根据配置文件来设置
        /** 加载 微信APP证书 */
        String wechatAppMchId = MyApplicationContext.getProperties().getProperty("wechat.app.mch_id");
        if (!StringUtils.isBlank(wechatAppMchId)){
            File wechatAppCertLocation = new File("conf/certs/weixin_app_cert/apiclient_cert.p12");
            LocalHttpClient.initMchKeyStore(wechatAppMchId, wechatAppCertLocation.getAbsolutePath());
        }

        /** 加载 微信WAP证书 */
        String wechatWapMchId = MyApplicationContext.getProperties().getProperty("wechat.wap.mch_id");
        if (!StringUtils.isBlank(wechatWapMchId)){
            File wechatWapCertLocation = new File("conf/certs/weixin_wap_cert/apiclient_cert.p12");
            LocalHttpClient.initMchKeyStore(wechatWapMchId, wechatWapCertLocation.getAbsolutePath());
        }

        if (MyApplicationContext.getProperties().getProperty("server.port") != null)
            port = Integer.parseInt(MyApplicationContext.getProperties().getProperty("server.port"));
        //		checkHttpProxy();
    }

    public EasyPaymentApp() {
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        logger.info("shutting down netty");
        super.stop();
        logger.info("shutting down service thread pool");
        ThreadPoolExecutor executor = ThreadPoolUtil.getExecutor();
        if (executor != null) {
            executor.shutdown();
        }
        logger.info("shut down netty completely");
    }

    @Override
    public String serverName() {
        return "EastPaymentApp";
    }

    @Override
    protected ServerAddress getServerAddress() {
        return new ServerAddress("0.0.0.0", port);
    }

    @Override
    protected ChannelInitializer<SocketChannel> getChannelInitializer() {
        return new HttpPipelineInitializer(initHttpHandler());
    }

    protected RouterHandler initHttpHandler() {
        initHttpRouters();
        RouterHandler httpHandler = new RouterHandler();
        httpHandler.setAdapter(new PaymentControllerAdapter());
        return httpHandler;
    }

    protected void initHttpRouters() {
        //初始化路由
        RouterHandler.getRouter()
            .POST("/api/payments", "paymentController@postPayment")
            .POST("/api/refunds", "paymentController@postRefund")
            .GET("/api/payments/:paymentCode", "paymentController@getPayment")
            .GET("/api/refunds/:paymentCode", "paymentController@getRefund")
            .POST("/notify/payments/:platformId/:paymentCode", "notifyController@notifyPayment")
            .POST("/notify/refunds/:platformId/:paymentCode", "notifyController@notifyRefund");

    }

    public static void main(String[] args) throws Exception {
        System.out.println("user.dir:" + System.getProperty("user.dir"));
        MyApplicationContext.getInstance();
        EasyPaymentApp paymentService = new EasyPaymentApp();
        paymentService.init();
        paymentService.start();
        Runtime.getRuntime().addShutdownHook(new ShutdownThread(paymentService));
    }

    private class PaymentControllerAdapter implements IControllerAdapter {
        @Override
        public IController getControllerByBeanId(String beanId) {
            return (IController) MyApplicationContext.getInstance().getBean(beanId);
        }
    }

}
