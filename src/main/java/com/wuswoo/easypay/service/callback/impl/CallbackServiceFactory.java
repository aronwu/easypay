package com.wuswoo.easypay.service.callback.impl;

import com.wuswoo.easypay.common.util.ThreadPoolUtil;
import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.callback.ICallbackTask;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.task.ITask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuxinjun on 16/11/3.
 */
@Service("callbackServiceFactory")
public class CallbackServiceFactory implements ICallbackService {
    private static final Logger logger = LogManager.getLogger(CallbackServiceFactory.class);

    @Value("${easypay.callback.task.classname}")
    private String callbackTaskClassName;

    @Override
    public boolean updatePaymentStatus(NotifySchedule notifySchedule) {
        try {
            ICallbackTask task = getCallbackTask(notifySchedule);
            ThreadPoolUtil.getExecutor().execute(task);
            return true;
        } catch (ClassNotFoundException ex) {
            logger.error("callback task classname: {} is not found", callbackTaskClassName);
        } catch(Exception ex){
            logger.error("callback task error", ex);
        }
        return false;
    }

    private ICallbackTask getCallbackTask(NotifySchedule notifySchedule) throws Exception {
        ICallbackTask task = (ICallbackTask) Class.forName(callbackTaskClassName).newInstance();
        task.setNotifySchedule(notifySchedule);
        return task;
    }
}
