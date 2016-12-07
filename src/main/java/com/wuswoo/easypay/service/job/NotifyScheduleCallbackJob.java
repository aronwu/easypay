package com.wuswoo.easypay.service.job;

import com.alibaba.fastjson.JSON;
import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.model.NotifySchedule;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.model.ResultQuery;
import com.wuswoo.easypay.service.payment.IResultQueryService;
import com.wuswoo.easypay.service.repository.INotifyScheduleService;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.PaymentServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by wuxinjun on 16/12/7.
 */
public class NotifyScheduleCallbackJob {
    private static final Logger logger = LogManager.getLogger(NotifyScheduleCallbackJob.class);
    @Value("${easypay.callback.job_max_notify_count}")
    private byte maxNotifyCount;
    @Value("${easypay.callback.job_limit}")
    private Integer limit;

    @Autowired
    private INotifyScheduleService notifyScheduleService;

    @Autowired
    @Qualifier("callbackServiceFactory")
    private ICallbackService callbackService;

    public void run() {
        logger.info("start run {}", logger.getName());
        logger.info("======================================================");
        List<NotifySchedule> notifySchedules = notifyScheduleService.getFailedNotifySchedules(maxNotifyCount, limit);
        logger.info("失败等待重新通知数目: {}", notifySchedules.size());
        for(NotifySchedule notifySchedule : notifySchedules) {
            logger.info(" 重新发送通知: {}", JSON.toJSONString(notifySchedule));
            callbackService.updatePaymentStatus(notifySchedule);
        }
        logger.info("end run {}", logger.getName());
        logger.info("======================================================");
    }
}
