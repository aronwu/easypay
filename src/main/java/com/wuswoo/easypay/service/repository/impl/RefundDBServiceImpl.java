package com.wuswoo.easypay.service.repository.impl;

import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.mapper.PaymentExtMapper;
import com.wuswoo.easypay.service.mapper.RefundMapper;
import com.wuswoo.easypay.service.mapper.RefundResultMapper;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundExample;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.model.RefundResultExample;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/14.
 */
@Service("refundDBService")
public class RefundDBServiceImpl implements IRefundDBService {
    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private RefundResultMapper refundResultMapper;

    @Autowired
    private PaymentExtMapper paymentExtMapper;

    @Value("${production}")
    private boolean production;

    @Override
    public Refund getRefundByRefundCode(String refundCode) {
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andRefundBatchCodeEqualTo(refundCode);
        List<Refund> refunds = refundMapper.selectByExample(refundExample);
        return refunds != null && refunds.size() > 0 ? refunds.get(0) : null;
    }

    @Override
    public Refund getRefundById(Long refundId) {
        return refundMapper.selectByPrimaryKey(refundId);
    }

    @Override
    public Refund insertRefund(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException {
        Date current = new Date();
        refund.setCreatedTime(current);
        refund.setUpdatedTime(current);
        refund.setSuccessNum((short) 0);
        refund.setBatchNum((short) refundResults.size());
        refundMapper.insertSelective(refund);
        String refundBatchCode =
            Utilities.getRefundCode(this.production, refund.getPlatformId(), refund.getId(), 0);
        Refund newRefund = new Refund();
        newRefund.setId(refund.getId());
        newRefund.setRefundBatchCode(refundBatchCode);
        this.updateRefund(newRefund);

        for (RefundResult refundResult : refundResults) {
            refundResult.setCreatedTime(current);
            refundResult.setUpdatedTime(current);
            refundResult.setRefundId(refund.getId());
            if (PayConstant.PlatformType.ALIPAYAPP.byteValue() == refund.getPlatformId()
                .byteValue()) {
                refundResult.setRefundCode(refundBatchCode);
            } else {
                refundResult.setRefundCode(Utilities
                    .getRefundCode(this.production, refund.getPlatformId(),
                        refundResult.getOrderId(), 8));
            }
            refundResultMapper.insertSelective(refundResult);
        }
        return refund;
    }

    @Override
    public void updateRefund(Refund refund) throws EasyPayException {
        refund.setUpdatedTime(new Date());
        refundMapper.updateByPrimaryKeySelective(refund);

    }

    @Override
    public void updateRefund(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException {
        refund.setUpdatedTime(new Date());
        refundMapper.updateByPrimaryKeySelective(refund);

    }

    @Override
    public RefundResult getRefundResultByRefundIdAndTradeNo(Long refundId, String tradeNo)
        throws EasyPayException {
        RefundResultExample example = new RefundResultExample();
        example.createCriteria().andRefundIdEqualTo(refundId).andTradeNoEqualTo(tradeNo);
        List<RefundResult> refundResults = refundResultMapper.selectByExample(example);
        return (refundResults != null && refundResults.size() > 0) ? refundResults.get(0) : null;
    }

    @Override
    public boolean saveRefundResult(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException {
        boolean needNofity = false;
        //只有新增的退款记录或者更新的退款成功记录,需要更新通知.
        for (RefundResult refundResult : refundResults) {
            RefundResult existedRefundResult = null;
            if (refundResult.getId() == null) {
                existedRefundResult =
                    getRefundResultByRefundIdAndTradeNo(refund.getId(), refundResult.getTradeNo());
            } else {
                existedRefundResult = getRefundResultById(refundResult.getId());
            }
            if (existedRefundResult != null) {
                //已经退款成功不再保存记录
                if (PayConstant.RefundStatus.REFUND_SUCCESS.byteValue() == existedRefundResult
                    .getStatus().byteValue()) {
                    continue;
                } else {
                    needNofity = true;
                    refundResultMapper.updateByPrimaryKeySelective(refundResult);
                }

            } else {
                needNofity = true;
                refundResultMapper.insertSelective(refundResult);
            }

        }
        return needNofity;
    }

    @Override
    public List<RefundResult> getRefundResults(Long refundId) throws EasyPayException {
        RefundResultExample refundResultExample = new RefundResultExample();
        refundResultExample.createCriteria().andRefundIdEqualTo(refundId);
        return refundResultMapper.selectByExample(refundResultExample);
    }

    @Override
    public RefundResult getRefundResultById(Long id) throws EasyPayException {
        return refundResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public RefundResult getRefundResultByRefundCodeAndPaltformId(String refundCode,
        Integer platformId) {
        RefundResultExample refundResultExample = new RefundResultExample();
        refundResultExample.createCriteria().andRefundCodeEqualTo(refundCode)
            .andPlatformIdEqualTo(platformId);
        List<RefundResult> refundResults = refundResultMapper.selectByExample(refundResultExample);
        return refundResults != null && refundResults.size() > 0 ? refundResults.get(0) : null;

    }

    @Override
    public List<Refund> getPendingRefund(Date startTime, Date endTime, Integer platformId, Long id,
        Integer limit) {
        return null;
    }

    @Override
    public void updateRefundResult(RefundResult refundResult) throws EasyPayException {
        refundResult.setUpdatedTime(new Date());
        refundResultMapper.updateByPrimaryKeySelective(refundResult);
    }
}
