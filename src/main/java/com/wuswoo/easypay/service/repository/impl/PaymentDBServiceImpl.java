package com.wuswoo.easypay.service.repository.impl;

import com.wuswoo.easypay.common.util.DateTimeUtil;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.mapper.NotifyFailedLogMapper;
import com.wuswoo.easypay.service.mapper.PaymentMapper;
import com.wuswoo.easypay.service.mapper.PaymentResultMapper;
import com.wuswoo.easypay.service.model.*;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.util.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/14.
 */
@Service("paymentDBService")
public class PaymentDBServiceImpl implements IPaymentDBService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PaymentResultMapper paymentResultMapper;
    @Autowired
    private NotifyFailedLogMapper notifyFailedLogMapper;


    @Override
    public void insertPayment(Payment payment) throws EasyPayException {
        Date current = new Date();
        if (payment.getCreatedTime() == null)
            payment.setCreatedTime(current);
        paymentMapper.insertSelective(payment);
    }

    @Override
    public void updatePayment(Payment payment) throws EasyPayException {
        paymentMapper.updateByPrimaryKeySelective(payment);
    }

    @Override
    public Payment getPaymentByPaymentId(Long paymentId) throws EasyPayException {
        return paymentMapper.selectByPrimaryKey(paymentId);
    }

    @Override
    public Payment getPaymentByPaymentCode(String paymentCode) throws EasyPayException {
        PaymentExample paymentExample = new PaymentExample();
        paymentExample.createCriteria().andPaymentCodeEqualTo(paymentCode);
        paymentExample.setOrderByClause("ID DESC");
        List<Payment> payments = paymentMapper.selectByExample(paymentExample);
        if (payments == null || payments.size() == 0)
            throw new EasyPayException(String.format("没有找到对应%s的订单", paymentCode),
                EasyPayException.UNKNOWN_ERROR);
        return payments.get(0);
    }

    @Override
    public PaymentResult getPaymentResult(String paymentCode) throws EasyPayException {
        PaymentResultExample paymentResultExample = new PaymentResultExample();
        paymentResultExample.createCriteria().andPaymentCodeEqualTo(paymentCode);
        List<PaymentResult> paymentResults =
            paymentResultMapper.selectByExample(paymentResultExample);
        if (paymentResults != null && paymentResults.size() > 0) {
            return paymentResults.get(0);
        }
        return null;
    }

    @Override
    public int savePaymentResult(PaymentResult paymentResult) throws EasyPayException {

        if (paymentResult.getStatus().byteValue() == PayConstant.PaymentStatus.SUCCESS
            .byteValue()) {
            paymentResult.setNotifyStatus(PayConstant.NotifyResultStatus.SUCCESS.byteValue());
        } else {
            paymentResult.setNotifyStatus(PayConstant.NotifyResultStatus.RECEIVE.byteValue());
        }
        PaymentResult existedPaymentResult = this.getPaymentResult(paymentResult.getPaymentCode());
        //已经接到支付回调通知
        if (existedPaymentResult != null) {
            if (existedPaymentResult.getStatus().byteValue() == PayConstant.PaymentStatus.SUCCESS
                .byteValue()) {
                return 0;
            }
            paymentResult.setId(existedPaymentResult.getId());
            paymentResult.setCreatedTime(new Date());
            paymentResult.setUpdatedTime(new Date());
            paymentResultMapper.updateByPrimaryKeySelective(paymentResult);

        } else {
            paymentResultMapper.insertSelective(paymentResult);
        }
        if (paymentResult.getStatus().byteValue() == PayConstant.PaymentStatus.SUCCESS
            .byteValue()) {
            return 1;
        }
        return 2;
    }

    @Override
    public List<PaymentResult> getPaymentResultsByPaymentCodes(List<String> paymentCodes,
        Byte status) throws EasyPayException {
        if (paymentCodes.size() == 0) {
            throw new EasyPayException("paymentCode数目小于1", EasyPayException.UNKNOWN_ERROR);
        }
        PaymentResultExample paymentResultExample = new PaymentResultExample();
        PaymentResultExample.Criteria criteria = new PaymentResultExample().createCriteria();
        criteria.andPaymentCodeIn(paymentCodes);
        if (status != null) {
            criteria.andStatusEqualTo(status.byteValue());
        }
        return paymentResultMapper.selectByExample(paymentResultExample);
    }

    @Override
    public List<Payment> getPendingPayments(Integer times) {
        PaymentExample paymentExample = new PaymentExample();
        Date startDate = DateTimeUtil.addForNow(Calendar.SECOND, -times);
        Date endDate = DateTimeUtil.addForNow(Calendar.SECOND, -5);
        paymentExample.createCriteria().andUpdatedTimeBetween(startDate, endDate)
            .andStatusEqualTo(PayConstant.PaymentStatus.PAY_WAITING.byteValue());
        return paymentMapper.selectByExample(paymentExample);
    }

    @Override
    public void saveNotifyFailedLog(NotifyFailedLog notifyFailedLog) throws EasyPayException {
        notifyFailedLogMapper.insertSelective(notifyFailedLog);
    }
}
