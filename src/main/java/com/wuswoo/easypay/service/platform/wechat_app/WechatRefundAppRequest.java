package com.wuswoo.easypay.service.platform.wechat_app;

import com.wuswoo.easypay.service.request.BaseRequest;
import com.wuswoo.easypay.service.request.RefundRequest;

/**
 * Created by wuxinjun on 16/11/14.
 */
public class WechatRefundAppRequest extends RefundRequest {

    public WechatRefundAppRequest() {

    }

    public WechatRefundAppRequest(BaseRequest request) {
        super(request);
        this.signature = request.getSignature();
    }

    @Override
    public String sign() {
        return null;
    }

}
