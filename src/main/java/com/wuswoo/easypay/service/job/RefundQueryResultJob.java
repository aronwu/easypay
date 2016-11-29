package com.wuswoo.easypay.service.job;

import com.wuswoo.easypay.service.model.Platform;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.model.ResultQuery;
import com.wuswoo.easypay.service.payment.IResultQueryService;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.repository.IResultQueryDBService;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.PaymentServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by wuxinjun on 16/11/28.
 */
public class RefundQueryResultJob {
    private static final Logger logger = LogManager.getLogger(RefundQueryResultJob.class);

    private IResultQueryDBService resultQueryDBService;

    private IRefundDBService refundDBService;

    public RefundQueryResultJob() {}
    public void run() {
        logger.info("start run {}", logger.getName());
        logger.info("======================================================");
        // 15天以前的记录
        Long startTime = System.currentTimeMillis() - 24*3600*15*1000;
        wechatAppPlatformRefundQuery(startTime);
        wechatWapPlatformRefundQuery(startTime);
        logger.info("end run {}", logger.getName());
        logger.info("======================================================");
    }

    public void wechatAppPlatformRefundQuery(Long startTime) {

    }

    public void wechatWapPlatformRefundQuery(Long startTime) {
        List<ResultQuery> resultQueryList =
            resultQueryDBService.getPendingResultQueries(PayConstant.PlatformType.WEIXINWAP.intValue(),
                PayConstant.PaymentResultType.REFUND.byteValue(), startTime);
        logger.info("wechat WAP 等待退款记录处理数目: {}", resultQueryList.size());
        IResultQueryService resultQueryService = PaymentServiceFactory.getQueryService(
            PayConstant.PlatformType.WEIXINAPP.intValue());
        for(ResultQuery resultQuery : resultQueryList) {
            try {
                logger.info("开始退款查询id:", resultQuery.getTradeId());
                RefundResult refundResult = refundDBService.getRefundResultById(resultQuery.getTradeId());
                resultQueryService.queryRefund(refundResult);
                logger.info("结束退款查询id:", resultQuery.getTradeId());
            }catch(Exception ex) {
                logger.info("微信退款失败 {}", ex);

            }
        }
    }

}
