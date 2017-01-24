package com.wuswoo.easypay.service.util;

import com.wuswoo.easypay.common.util.MyApplicationContext;
import com.wuswoo.easypay.service.payment.IEasyPaymentService;
import com.wuswoo.easypay.service.payment.IRefundService;
import com.wuswoo.easypay.service.payment.IResultQueryService;

/**
 * Created by wuxinjun on 16/9/13.
 */
public class PaymentServiceFactory {
    public static IEasyPaymentService getPaymentService(Integer platform) {
        return MyApplicationContext.getInstance()
            .getBean("paymentService" + platform, IEasyPaymentService.class);
    }

    public static IRefundService getRefundService(Integer platform) {
        return MyApplicationContext.getInstance()
            .getBean("refundService" + platform, IRefundService.class);

    }

    public static IResultQueryService getQueryService(Integer platform) {
        return MyApplicationContext.getInstance()
            .getBean("queryService" + platform, IResultQueryService.class);
    }

}
