package com.wuswoo.easypay.service.callback.impl;

import com.wuswoo.easypay.service.callback.ICallbackTask;
import com.wuswoo.easypay.service.model.NotifySchedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by wuxinjun on 16/11/30.
 */
public class HttpCallbackTask implements ICallbackTask {
    private static final Logger logger = LogManager.getLogger(HttpCallbackTask.class);

    private NotifySchedule notifySchedule;

    @Override
    public void setNotifySchedule(NotifySchedule notifySchedule) {
        this.notifySchedule = notifySchedule;
    }

    @Override
    public void run() {

    }
}
