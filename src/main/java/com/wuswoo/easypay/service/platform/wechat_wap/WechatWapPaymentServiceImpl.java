package com.wuswoo.easypay.service.platform.wechat_wap;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.common.util.DateTimeUtil;
import com.wuswoo.easypay.common.util.TimeCost;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.payment.AbstractPaymentService;
import com.wuswoo.easypay.service.platform.wechat.util.WechatAppUtil;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.request.PaymentRequest;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.util.MapUtil;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

/**
 * Created by wuxinjun on 16/11/10.
 */
public class WechatWapPaymentServiceImpl extends AbstractPaymentService {

    private final static Logger logger = LogManager.getLogger(WechatWapPaymentServiceImpl.class);

    @Autowired
    private IPaymentDBService paymentDBService;

    public WechatWapPaymentServiceImpl(PaymentRequest paymentRequest) {
        super(paymentRequest);
    }

    @Override
    protected PaymentResult makePaymentResult(Request request, Integer platformId)
        throws EasyPayException {
        return null;
    }

    @Override
    public PaymentRequest newPayment(Payment payment) throws EasyPayException {
        if (StringUtils.isBlank(payment.getOpenId())) {
            throw new EasyPayException("微信openId不能为空", EasyPayException.WECHAT_BLANK_OPENID);
        }
        paymentDBService.insertPayment(payment);
        Unifiedorder wechatOrder = new Unifiedorder();
        wechatOrder.setAppid(this.paymentRequest.getQueryParam("appid"));
        wechatOrder.setMch_id(this.paymentRequest.getQueryParam("mch_id"));
        wechatOrder.setTrade_type(this.paymentRequest.getQueryParam("trade_type"));
        //微信回调url添加paymentcode,便于服务器访问日志区分不同的订单的回调
        String notifiyUrl = this.paymentRequest.getQueryParam("notify_url") + "/" + payment.getPaymentCode();
        wechatOrder.setNotify_url(notifiyUrl);
        wechatOrder.setAttach(payment.getAttach());
        wechatOrder.setBody(payment.getItemDesc());
        wechatOrder.setNonce_str(RandomStringUtils.randomStringByLength(30));
        wechatOrder.setOut_trade_no(payment.getPaymentCode());
        wechatOrder.setSpbill_create_ip(payment.getClientIp());
        wechatOrder.setTotal_fee(String.valueOf(payment.getAmount()));
        wechatOrder.setOpenid(payment.getOpenId());
        logger.info("Wechat Unifiedorder: {}", JSONObject.toJSONString(wechatOrder, true));
        TimeCost timeCostUtil = new TimeCost();
        timeCostUtil.start();
        UnifiedorderResult wechatResult = PayMchAPI.payUnifiedorder(wechatOrder, this.paymentRequest.getQueryParam("paterner_key"));
        logger.info("Wechat Unifiedorder Spend time: {0}", java.lang.String.valueOf(timeCostUtil.cost()));
        logger.info("Wechat Unifiedorder Result:{0}", JSONObject.toJSONString(wechatResult));
        //start to generate payment request
        PaymentRequest wechatRequest = new WechatPaymentWapRequest();
        if ("SUCCESS".equalsIgnoreCase(wechatResult.getResult_code())) {
            //请求成功验证签名
            if (!WechatAppUtil.validateUnifiedorderResultSign(wechatResult,  this.paymentRequest.getQueryParam("paterner_key"))) {
                Map<String, String> map = MapUtil.objectToMap(wechatResult, "sign");
                String sign = SignatureUtil.generateSign(map, this.paymentRequest.getQueryParam("paterner_key"));
                logger.error("Wechat Unifiedorder Result Map: {0}", JSONObject.toJSONString(map, true));
                logger.error("WeChat Payment Signature validate error: {0}", sign);
                throw new EasyPayException("微信支付请求签名验证失败", EasyPayException.WECHAT_SIGN_INVALIDATE);
            }
            wechatRequest.addParam("appId", wechatResult.getAppid());
            wechatRequest.addParam("package","prepay_id=" + wechatResult.getPrepay_id());
            wechatRequest.addParam("nonceStr", wechatResult.getNonce_str());
            wechatRequest.addParam("timeStamp", String.valueOf(System.currentTimeMillis()/1000));
            wechatRequest.addParam("signType", this.paymentRequest.getQueryParam("sign_type"));
            String sign = SignatureUtil.generateSign(wechatRequest.getQueryParams(), this.paymentRequest.getQueryParam("paterner_key"));
            wechatRequest.addParam("paySign", sign);
        } else {
            wechatRequest.addParam("code", EasyPayException.WECHAT_PAYMENT_ERROR);
            wechatRequest.addParam("message", wechatResult.getReturn_msg());
        }

        return wechatRequest;
    }

    @Override
    public PaymentResult getPaymentResult(Request request, Integer platformId)
        throws EasyPayException {
        return null;
    }

    @Override
    public String returnPaymentPlatform(PaymentResult paymentResult, Boolean resultStatus) {
        return resultStatus ? "<xml>"
            + "<return_code><![CDATA[SUCCESS]]></return_code>"
            + "<return_msg><![CDATA[OK]]></return_msg>"
            + "</xml>"
            : "<xml>"
            + "<return_code><![CDATA[FAIL]]></return_code>"
            + "<return_msg><![CDATA[处理失败]]></return_msg>"
            + "</xml>";
    }

    @Override
    public PaymentResult getNotifyPaymentResult(Request request, Integer platformId)
        throws EasyPayException {
        PaymentResult paymentResult = new PaymentResult();
        try {
            String contentXml = request.content().toString(Charset.forName("UTF-8"));
            MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class, contentXml);
            if (payNotify != null && "SUCCESS".equalsIgnoreCase(payNotify.getReturn_code())) {
                if (!WechatAppUtil.validateMchPayNotifySign(payNotify, this.paymentRequest.getQueryParam("paterner_key"))) {
                    //验证失败
                    logger.error("微信支付回调签名验证失败:{0}", payNotify.getSign());
                    throw new EasyPayException("微信支付回调签名验证失败", EasyPayException.WECHAT_SIGN_INVALIDATE);
                }
                Date current = new Date();
                paymentResult.setCreatedTime(current);
                paymentResult.setUpdatedTime(current);
                paymentResult.setNotifyTime(current);
                paymentResult.setPaymentTime(DateTimeUtil.parseStringTo99BillDateTime(payNotify.getTime_end()));
                paymentResult.setPlatformId(platformId);
                paymentResult.setPayAmount(Integer.valueOf(payNotify.getTotal_fee()));
                paymentResult.setTradeNo(payNotify.getTransaction_id());
                paymentResult.setPaymentCode(payNotify.getOut_trade_no());
                paymentResult.setReturnContent(contentXml);
                paymentResult.setPlatformReturnCode(payNotify.getResult_code());
                paymentResult.setStatus(
                    "SUCCESS".equalsIgnoreCase(payNotify.getResult_code())
                    ? PayConstant.PaymentStatus.SUCCESS.byteValue() : PayConstant.PaymentStatus.FAIL.byteValue()
                );
            } else {
                logger.error("微信支付回调失败code: {0} message: {1}", payNotify.getReturn_code(), payNotify.getReturn_msg());
                throw new EasyPayException("微信支付回调失败", EasyPayException.WECHAT_NOTIFY_ERROR);
            }
        } catch (Exception e) {
            logger.error("获取微信支付回调结果失败", e);

        }
        return paymentResult;
    }
}
