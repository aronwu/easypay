package com.wuswoo.easypay.service.repository.impl;

import com.wuswoo.easypay.service.mapper.NotifyScheduleMapper;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.NotifyScheduleExample;
import com.wuswoo.easypay.service.repository.INotifyScheduleService;
import com.wuswoo.easypay.service.util.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

/**
 * Created by wuxinjun on 16/12/1.
 */
@Service("notifyScheduleService")
public class NotifyScheduleServiceImpl implements INotifyScheduleService {

    @Autowired
    private NotifyScheduleMapper notifyScheduleMapper;
    @Override
    public List<NotifySchedule> getFailedNotifySchedules(Byte tradeType, Integer platformId) {
        NotifyScheduleExample example = new NotifyScheduleExample();
        example.createCriteria().andNotifyStatusEqualTo(PayConstant.NotifyResultStatus.FAIL.byteValue())
            .andTradeTypeEqualTo(tradeType)
            .andPlatformIdEqualTo(platformId);
        List<NotifySchedule> notifySchedules = notifyScheduleMapper.selectByExample(example);
        return notifySchedules;
    }

    @Override
    public NotifySchedule getNotifySchedule(Long tradeId, Byte tradeType, String paymentCode,
        Integer platformId) {
        NotifySchedule notifySchedule = null;
        NotifyScheduleExample example = new NotifyScheduleExample();
        example.createCriteria().andTradeIdEqualTo(tradeId)
            .andPaymentCodeEqualTo(paymentCode)
            .andTradeTypeEqualTo(tradeType)
            .andPlatformIdEqualTo(platformId);
        List<NotifySchedule> notifySchedules = notifyScheduleMapper.selectByExample(example);
        if (notifySchedules != null && notifySchedules.size() > 0) {
            notifySchedule = notifySchedules.get(0);
        }
        return notifySchedule;
    }
}
