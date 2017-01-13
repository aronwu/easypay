package com.wuswoo.easypay.service.util;

/**
 * Created by wuxinjun on 16/9/13.
 */

/** 支付常量定义 */
public class PayConstant {

    /** 代码 */
    public short code;
    /** 描述 */
    public String description;

    public PayConstant(Number num)
    {
        this(num, "");
    }

    public PayConstant(Number num, String desc)
    {
        code	= num.shortValue();
        description	= desc;
    }

    public final int intValue() {
        return code;
    }

    public final byte byteValue() {
        return (byte)code;
    }

    /** 支付平台类型 */
    public static class PlatformType
    {
        //微信支付
        public static final PayConstant WEIXINWAP		= new PayConstant(1, "微信WAP支付");
        public static final PayConstant WEIXINAPP		= new PayConstant(2, "微信APP支付");
        public static final PayConstant WEIXINNATIVE		= new PayConstant(3, "微信原生扫码支付");

        //支付支付
        public static final PayConstant ALIPAYAPP		= new PayConstant(7, "支付宝APP支付");
        public static final PayConstant ALIPAYWAP		= new PayConstant(8, "支付宝WAP支付");
    }

    /** 支付状态类型 */
    public static class PaymentStatus
    {
        public static final PayConstant NOTPAY						= new PayConstant(0, "未支付");

        public static final PayConstant PAY_WAITING					= new PayConstant(0, "等待支付");

        public static final PayConstant USERPAYING					= new PayConstant(1, "用户支付中");

        public static final PayConstant PAYERROR					= new PayConstant(2, "失败(其他原因，如银行返回失败)");

        public static final PayConstant FAIL						= new PayConstant(2, "失败");

        public static final PayConstant SUCCESS						= new PayConstant(3, "成功");

        public static final PayConstant TEMP						= new PayConstant(10, "预留 比较字段");

        public static final PayConstant REFUND						= new PayConstant(100, "退款成功");

        public static final PayConstant CLOSED						= new PayConstant(101, "已关闭");

        public static final PayConstant REVOKED						= new PayConstant(102, "已撤销（刷卡支付）");

        public static final PayConstant SUCCESS_SIGN_FAIL			= new PayConstant(-1, "成功但校验签名失败");

    }

    /** 退款状态类型 */
    public static class RefundStatus
    {
        public static final PayConstant NOT_REFUND					= new PayConstant(0, "未退款");

        public static final PayConstant REFUND_IN_PROCESS			= new PayConstant(1, "退款中");

        public static final PayConstant REFUND_FAILED				= new PayConstant(2, "退款失败");

        public static final PayConstant REFUND_SUCCESS				= new PayConstant(3, "退款成功");

        public static final PayConstant TRADE_CLOSED				= new PayConstant(101, "交易关闭");

    }


    /** 支付流水类型 */
    public static class PaymentResultType
    {
        public static final PayConstant ORDER						= new PayConstant(0, "订单");
        public static final PayConstant REFUND						= new PayConstant(1, "退款");
    }

    /** 支付流水类型 */
    public static class NotifyResultType
    {
        public static final PayConstant PAYMENT						= new PayConstant(0, "支付");
        public static final PayConstant REFUND						= new PayConstant(1, "退款");
    }

    /** 支付通知类型 */
    public static class NotifyResultStatus
    {
        public static final PayConstant WAITING						= new PayConstant(0, "未通知");
        public static final PayConstant RECEIVE					    = new PayConstant(1, "已接收");
        public static final PayConstant SUCCESS						= new PayConstant(2, "已确认");
        public static final PayConstant FAIL						= new PayConstant(1, "通知失败");
    }

    /** 交易查询状态 */
    public static class ResultQueryType
    {
        public static final PayConstant PENDING							= new PayConstant(1, "查询中");
        public static final PayConstant FAIL						= new PayConstant(2, "失败");
        public static final PayConstant SUCCESS						= new PayConstant(3, "成功");
    }

    /** 结果查询 */
    public static class ResultQuery
    {
        public static final byte MAX_QUERY							= 50;
    }


    public static class NotifyEvent
    {
        public static final String PAYMENT = "payment.notify.payment";
        public static final String REFUND = "payment.notify.refund";

    }


    /** 微信支付类型定义
     * JSAPI--公众号支付
     * NATIVE--原生扫码支付
     * APP--app支付
     * WAP--手机浏览器H5支付
     * MICROPAY--刷卡支付 （刷卡支付有单独的支付接口，不调用统一下单接口）
     */
    public static class WechatTradeType
    {
        public static final String JSAPI				= "JSAPI";

        public static final String NATIVE				= "NATIVE";

        public static final String APP					= "APP";

        public static final String WAP					= "WAP";

        public static final String MICROPAY				= "MICROPAY";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof PayConstant)
        {
            PayConstant compareObj = (PayConstant)obj;
            return code == compareObj.code;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("{%d, '%s'}", code, description);
    }
}
