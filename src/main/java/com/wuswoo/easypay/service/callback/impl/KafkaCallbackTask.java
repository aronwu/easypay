package com.wuswoo.easypay.service.callback.impl;

import com.wuswoo.easypay.service.callback.ICallbackTask;
import com.wuswoo.easypay.service.mapper.NotifyFailedLogMapper;
import com.wuswoo.easypay.service.mapper.NotifyScheduleMapper;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.repository.INotifyScheduleService;
import com.wuswoo.easypay.service.repository.IPaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wuxinjun on 16/11/30.
 */

public class KafkaCallbackTask implements ICallbackTask {
    private static final Logger logger = LogManager.getLogger(KafkaCallbackTask.class);

    private NotifySchedule notifySchedule;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private INotifyScheduleService notifyScheduleService;


    @Override
    public void setNotifySchedule(NotifySchedule notifySchedule) {
        this.notifySchedule = notifySchedule;
    }

    @Override
    public void run() {

    }
}

