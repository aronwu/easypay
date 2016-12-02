package com.wuswoo.easypay.service.controller.impl;

import com.wuswoo.easypay.common.util.TimeCost;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;
import com.wuswoo.easypay.service.controller.INotifyController;

import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.model.RefundAndResults;
import com.wuswoo.easypay.service.payment.IEasyPaymentService;
import com.wuswoo.easypay.service.payment.IRefundService;
import com.wuswoo.easypay.service.repository.IPaymentService;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.PaymentServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by wuxinjun on 16/11/14.
 */
@Controller("notifyController")
public class NotifyController implements INotifyController {
    private static final Logger logger = LogManager.getLogger(NotifyController.class);

    @Autowired
    private IPaymentService zhifuService;

    @Override
    public void notifyPayment(Request request, Response response) throws Exception {
        Integer platformId = Integer.parseInt(request.pathParam("platformId"));
        if (platformId == 0) {
            platformId = PayConstant.PlatformType.WEIXINWAP.intValue();
        }
        boolean result = false;
        IEasyPaymentService easyPaymentService = PaymentServiceFactory.getPaymentService(platformId);
        PaymentResult  paymentResult = null;
        try {
            paymentResult = easyPaymentService.getNotifyPaymentResult(request, platformId);
            result = zhifuService.savePaymentResult(paymentResult, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = false;
        }
        String responseContent = easyPaymentService.returnPaymentPlatform(paymentResult, result);
        response.respondText(responseContent);
    }

    @Override
    public void notifyRefund(Request request, Response response) throws Exception {
        Integer platformId = Integer.parseInt(request.pathParam("platformId"));
        if (platformId == 0) {
            platformId = PayConstant.PlatformType.WEIXINWAP.intValue();
        }
        boolean result = false;
        IRefundService refundService = PaymentServiceFactory.getRefundService(platformId);
        try {
            RefundAndResults  refundAndResults = refundService.getRefundAndResults(request, platformId);
            result = zhifuService.saveRefundAndResults(refundAndResults.getRefund(), refundAndResults.getRefundResults());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = false;
        }
        String responseContent = refundService.responseToPaymentPlatform(result);
        response.respondText(responseContent);
    }
}
