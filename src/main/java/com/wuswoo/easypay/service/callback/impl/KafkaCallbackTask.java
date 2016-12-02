package com.wuswoo.easypay.service.callback.impl;

import com.alibaba.fastjson.JSON;
import com.wuswoo.easypay.common.util.MyApplicationContext;
import com.wuswoo.easypay.service.callback.ICallbackTask;
import com.wuswoo.easypay.service.mapper.NotifyFailedLogMapper;
import com.wuswoo.easypay.service.mapper.NotifyScheduleMapper;
import com.wuswoo.easypay.service.misc.PaymentEvent;
import com.wuswoo.easypay.service.misc.rabbitmq.MQSender;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.repository.INotifyScheduleService;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.repository.IPaymentService;
import com.wuswoo.easypay.service.util.PayConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * kafka消息队列,支持kafka版本0.9.x
 * Created by wuxinjun on 16/11/30.
 */
public class KafkaCallbackTask implements ICallbackTask {
    private static final Logger logger = LogManager.getLogger(KafkaCallbackTask.class);

    private NotifySchedule notifySchedule;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private INotifyScheduleService notifyScheduleService;

    @Autowired
    private IPaymentDBService paymentDBService;

    @Autowired
    @Qualifier("kafkaProducer")
    private KafkaProducer<String, String> kafkaProducer;

    @Value("${kafka.topic}")
    String kafkaTopic;

    @Value("${easypay.callback.appId}")
    String appId;

    @Override
    public void setNotifySchedule(NotifySchedule notifySchedule) {
        this.notifySchedule = notifySchedule;
    }

    @Override
    public void run() {
        logger.info("start to notify kafka server: {}", JSON.toJSONString(notifySchedule));
        //check paymentCode existed or not
        try {
            Payment payment = paymentDBService.getPaymentByPaymentCode(notifySchedule.getPaymentCode());
        } catch (Exception ex) {
            logger.warn("找不到paymentCode: {}", notifySchedule.getPaymentCode());
            return;
        }
        //check notifyschedule existed status
        NotifySchedule existedNotifySchedule = notifyScheduleService.getNotifySchedule(
            notifySchedule.getTradeId(),
            notifySchedule.getTradeType(),
            notifySchedule.getPaymentCode(),
            notifySchedule.getPlatformId()
        );
        //notifySchedule.setNotifyContent(JSON.toJSONString(notifySchedule));
        if (existedNotifySchedule != null) {
            notifySchedule.setId(existedNotifySchedule.getId());
            //如果已经通知成功,直接返回
            if (notifySchedule.getNotifyStatus() != null &&
                notifySchedule.getNotifyStatus().byteValue() == existedNotifySchedule.getNotifyStatus().byteValue()) {
                return;
            }
        }
        //回调处理
        try {
            PaymentEvent event = new PaymentEvent();
            Integer appCode = 1;
            if (!StringUtils.isBlank(appId)){
                appCode = Integer.parseInt(appId);
            }
            if (notifySchedule.getTradeType().byteValue() == PayConstant.NotifyResultType.PAYMENT.byteValue()) {
                event.setEventType(PayConstant.NotifyEvent.PAYMENT);
            }
            if (notifySchedule.getTradeType().byteValue() == PayConstant.NotifyResultType.REFUND.byteValue()) {
                event.setEventType(PayConstant.NotifyEvent.REFUND);
            }
            event.setAppCode(appCode);
            event.setContent((JSON)JSON.toJSON(notifySchedule));
            sendNotify(event);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("回调业务系统出现错误", ex);
            updateNotifySchedule(PayConstant.NotifyResultStatus.FAIL.byteValue(), ex.getMessage());
        }
    }

    private void sendNotify(PaymentEvent event)
    {
        ProducerRecord<String, String> data = new ProducerRecord<String, String>(kafkaTopic, JSON.toJSONString(event));
        kafkaProducer.send(data, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    //发送成功
                    logger.info("send message to kafka success.");
                    updateNotifySchedule(PayConstant.NotifyResultStatus.SUCCESS.byteValue(), "");
                } else {
                    //发送失败
                    logger.error("发送消息到kafka出现错误", e);
                    updateNotifySchedule(PayConstant.NotifyResultStatus.FAIL.byteValue(), e.getMessage());
                }
            }
        });
    }

    private void updateNotifySchedule(Byte status, String message) {
        //更新数据库
        if (notifySchedule.getId() != null && notifySchedule.getId() > 0) {
            NotifySchedule newNs = new NotifySchedule();
            newNs.setId(notifySchedule.getId());
            newNs.setNotifyStatus(status);
            newNs.setUpdatedTime(new Date());
            newNs.setNotifyReturn(message);
            paymentService.updateNotifySchedule(newNs);
        } else {
            notifySchedule.setNotifyStatus(status);
            notifySchedule.setUpdatedTime(new Date());
            notifySchedule.setNotifyReturn(message);
            paymentService.saveNotifySchedule(notifySchedule);
        }
    }
}


