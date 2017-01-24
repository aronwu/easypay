package com.wuswoo.easypay.service.platform.wechat_wap;

import com.wuswoo.easypay.service.request.BaseRequest;
import com.wuswoo.easypay.service.request.RefundRequest;

/**
 * Created by wuxinjun on 16/11/14.
 */
public class WechatRefundWapRequest extends RefundRequest {

    public WechatRefundWapRequest() {

    }

    public WechatRefundWapRequest(BaseRequest request) {
        super(request);
        this.signature = request.getSignature();
    }

    @Override
    public String sign() {
        return null;
    }

}
