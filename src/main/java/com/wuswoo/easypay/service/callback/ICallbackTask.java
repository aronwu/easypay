package com.wuswoo.easypay.service.callback;

import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.task.ITask;

/**
 * Created by wuxinjun on 16/11/30.
 */
public interface ICallbackTask extends ITask {
    public void setNotifySchedule(NotifySchedule notifySchedule);
}
