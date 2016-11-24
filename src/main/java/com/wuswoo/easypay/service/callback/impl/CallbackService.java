package com.wuswoo.easypay.service.callback.impl;

import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.RefundResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuxinjun on 16/11/3.
 */
@Service("callbackService")
public class CallbackService implements ICallbackService {
    @Override
    public boolean updatePaymentStatus(NotifySchedule notifySchedule) {
        return false;
    }

    @Override
    public boolean updateRefundStatus(List<RefundResult> refundResults) {
        return false;
    }
}
