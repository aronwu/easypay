package com.wuswoo.easypay.service.repository;

import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundResult;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IRefundDBService {

    public Refund getRefundByRefundCode(String refundCode);

    public Refund getRefundById(Long refundId);

    public Refund insertRefund(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException;

    public void updateRefund(Refund refund) throws EasyPayException;

    public void updateRefundResult(RefundResult refundResult) throws EasyPayException;

    public void updateRefund(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException;

    public RefundResult getRefundResultByRefundIdAndTradeNo(Long refundId, String tradeNo)
        throws EasyPayException;

    public boolean saveRefundResult(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException;

    public List<RefundResult> getRefundResults(Long refundId) throws EasyPayException;

    public RefundResult getRefundResultById(Long id) throws EasyPayException;

    public RefundResult getRefundResultByRefundCodeAndPaltformId(String refundCode,
        Integer platformId);

    public List<Refund> getPendingRefund(Date startTime, Date endTime, Integer platformId, Long id,
        Integer limit);



}
