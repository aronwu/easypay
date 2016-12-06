package com.wuswoo.easypay.common.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.*;
import java.util.Properties;

/**
 * Created by wuxinjun on 16/9/2.
 */

@SuppressWarnings("static-access")
public class MyApplicationContext {
    private Logger logger = LogManager.getLogger(MyApplicationContext.class);

    private static ApplicationContext ctx;
    private static MyApplicationContext instance = null;

    private static Properties properties;

    static
    {
        /* 加载 log4j2 配置文件 */
        File file = new File("conf/log4j2.xml");
        System.out.println("log4j2 file location:" + file.getAbsolutePath());
        System.setProperty("log4j.configurationFile", file.getAbsolutePath());
        /* load properties */
        properties = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(new File("conf/config.properties")));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyApplicationContext getInstance(){
        if(instance==null){
            synchronized(MyApplicationContext.class){
                if(instance==null){
                    instance=new MyApplicationContext();
                }
            }
        }

        return instance;
    }

    public static Properties getProperties() {
        return properties;
    }


    private MyApplicationContext() {
        initCtx();
    }

    private void initCtx(){
        try{
            if(ctx == null) {
                String applicationContextFileLocation = "conf/spring-context.xml";
                File file = new File(applicationContextFileLocation);
                logger.info("applicationContext: [{}]", file.getAbsolutePath() );
                ctx = new FileSystemXmlApplicationContext(applicationContextFileLocation);
                javax.sql.DataSource dataSource =
                    this.getBean("dataSource", javax.sql.DataSource.class);
                java.sql.Connection connection = dataSource.getConnection();
                connection.close();
            }
        }catch(Exception e){
            logger.error("InitCtx Error:", e);
            System.exit(1);
        }
    }

    public ApplicationContext getApplicationContext(){
        return this.ctx;
    }

    public void destroy()
    {
        if(ctx != null)
            ((FileSystemXmlApplicationContext)ctx).destroy();
    }

    public <T> T  getBean(String beanId, Class<T> clazz){
        return ctx.getBean(beanId, clazz);
    }

    public Object getBean(String beanId){
        return ctx.getBean(beanId);
    }

    public <T> T  getBean(Class<T> clazz){
        return ctx.getBean(clazz);
    }

    public static void main(String[] args) {
        getInstance();
    }

}
