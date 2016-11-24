package com.wuswoo.easypay.service.platform.wechat_wap;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.service.request.BaseRequest;
import com.wuswoo.easypay.service.request.PaymentRequest;
import weixin.popular.util.SignatureUtil;

/**
 * Created by wuxinjun on 16/11/10.
 */
public class WechatPaymentWapRequest extends PaymentRequest {

    public WechatPaymentWapRequest() {

    }
    public WechatPaymentWapRequest(BaseRequest request) {
        super(request);
    }
    @Override
    public String sign() {
        String paternerKey = this.getQueryParam("paterner_key");
        this.getQueryParams().remove("paterner_key");
        this.getQueryParams().remove("notify_url");
        this.getQueryParams().remove("trade_type");
        String signString = SignatureUtil.generateSign(this.getQueryParams(), paternerKey);
        return this.getQueryParams().put("sign", signString);
    }

    @Override
    public String generateSignFormHiddenFields() {
        sign();
        return JSONObject.toJSONString(this.getQueryParams(), true);
    }
}
