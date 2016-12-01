package com.wuswoo.easypay.service.repository;

import com.wuswoo.easypay.service.model.NotifySchedule;

import java.util.List;

/**
 * Created by wuxinjun on 16/12/1.
 */
public interface INotifyScheduleService {

    /**
     * 获取通知业务系统回调失败
     * @param tradeType
     * @param platformId
     * @return
     */
    public List<NotifySchedule> getFailedNotifySchedules(Byte tradeType, Integer platformId);

    /**
     * 获取通知
     * @param tradeId
     * @param tradeType
     * @param paymentCode
     * @param platformId
     * @return
     */
    NotifySchedule getNotifySchedule(Long tradeId, Byte tradeType, String paymentCode, Integer platformId);

}
