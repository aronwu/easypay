package com.wuswoo.easypay.service.repository;

import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.NotifyFailedLog;
import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.model.PaymentResult;

import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IPaymentDBService {

    /**
     * 添加支付
     * @param payment
     * @throws EasyPayException
     */
    public void insertPayment(Payment payment) throws EasyPayException;

    /**
     * 更新支付
     * @param payment
     * @throws EasyPayException
     */
    public void updatePayment(Payment payment) throws EasyPayException;

    /**
     * 根据支付id获取支付
     * @param paymentId
     * @return
     * @throws EasyPayException
     */
    public Payment getPaymentByPaymentId(Long paymentId) throws EasyPayException;

    /**
     * 根据支付编号获取支付
     * @param paymentCode
     * @return
     * @throws EasyPayException
     */
    public Payment getPaymentByPaymentCode(String paymentCode) throws EasyPayException;

    /**
     * 根据支付编号获取支付结果
     * @param paymentCode
     * @return
     * @throws EasyPayException
     */
    public PaymentResult getPaymentResult(String paymentCode) throws EasyPayException;

    /**
     * 保存支付结果
     * @param paymentResult
     * @return
     * @throws EasyPayException
     */
    public int savePaymentResult(PaymentResult paymentResult) throws EasyPayException;


    /**
     * 根据一组支付编号获取相对应支付结果
     * @param paymentCodes
     * @param status
     * @return
     * @throws EasyPayException
     */
    public List<PaymentResult> getPaymentResultsByPaymentCodes(List<String> paymentCodes, Byte status) throws EasyPayException;

    /**
     * 获取一组待支付记录
     * @param times
     * @return
     */
    public List<Payment> getPendingPayments(Integer times);

    /**
     * 保存Notify
     * @param notifyFailedLog
     * @throws EasyPayException
     */
    public void saveNotifyFailedLog(NotifyFailedLog notifyFailedLog) throws EasyPayException;


}
