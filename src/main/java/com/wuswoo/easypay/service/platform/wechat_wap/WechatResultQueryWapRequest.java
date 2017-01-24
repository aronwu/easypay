package com.wuswoo.easypay.service.platform.wechat_wap;

import com.wuswoo.easypay.service.request.BaseRequest;

/**
 * Created by wuxinjun on 16/11/10.
 */
public class WechatResultQueryWapRequest extends BaseRequest {

    public WechatResultQueryWapRequest() {
    }

    public WechatResultQueryWapRequest(BaseRequest request) {
        super(request);
    }

    @Override
    public String sign() {
        return null;
    }

}
