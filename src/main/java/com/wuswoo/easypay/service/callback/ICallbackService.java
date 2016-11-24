package com.wuswoo.easypay.service.callback;

import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.RefundResult;

import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface ICallbackService {
    /**
     * 支付回调业务系统
     * @param notifySchedule
     * @return
     */
    boolean updatePaymentStatus(NotifySchedule notifySchedule);

    /**
     * 退款回调业务系统
     * @param refundResults
     * @return
     */
    boolean updateRefundStatus(List<RefundResult> refundResults);

}
