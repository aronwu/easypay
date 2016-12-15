package com.wuswoo.easypay.service.platform.wechat_app;

import com.wuswoo.easypay.service.request.BaseRequest;

/**
 * Created by wuxinjun on 16/11/10.
 */
public class WechatResultQueryAppRequest extends BaseRequest {

    public WechatResultQueryAppRequest() {}
    public WechatResultQueryAppRequest(BaseRequest request) {
        super(request);
    }
    @Override
    public String sign() {
        return null;
    }

}
