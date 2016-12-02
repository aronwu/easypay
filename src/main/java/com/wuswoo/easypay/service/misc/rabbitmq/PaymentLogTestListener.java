package com.wuswoo.easypay.service.misc.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wuxinjun on 16/12/2.
 */
public class PaymentLogTestListener {
    private Logger logger = LoggerFactory.getLogger(PaymentLogTestListener.class);

    public void reply(String json) {
        logger.info("receive rabbitmq queue message {}", json);
    }
}
