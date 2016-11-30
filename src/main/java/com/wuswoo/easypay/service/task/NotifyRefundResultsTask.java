package com.wuswoo.easypay.service.task;

import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.util.PayConstant;

import java.util.List;

/**
 * Created by wuxinjun on 16/11/30.
 */
public class NotifyRefundResultsTask implements ITask {

    private ICallbackService callbackService;
    private List<RefundResult> refundResults;

    public NotifyRefundResultsTask(){
        super();
    }

    public NotifyRefundResultsTask(ICallbackService callbackService, List<RefundResult> refundResults) {
        this.callbackService = callbackService;
        this.refundResults = refundResults;
    }
    @Override
    public void run() {
        if (refundResults == null || refundResults.size() == 0){
            return;
        }
        for(RefundResult refundResult : refundResults) {
            NotifySchedule notifySchedule = new NotifySchedule();
            notifySchedule.setPaymentCode(refundResult.getPaymentCode());
            notifySchedule.setTradeId(refundResult.getId());
            notifySchedule.setTradeType(PayConstant.NotifyResultType.REFUND.byteValue());
            notifySchedule.setPlatformId(refundResult.getPlatformId());
            notifySchedule.setStatus(refundResult.getStatus());
            notifySchedule.setOrderId(refundResult.getOrderId());
            notifySchedule.setRefundType(refundResult.getRefundType().byteValue());
            notifySchedule.setRefundCode(refundResult.getRefundCode());
            notifySchedule.setMessage(refundResult.getRefundError());
            callbackService.updatePaymentStatus(notifySchedule);
        }

    }
}
