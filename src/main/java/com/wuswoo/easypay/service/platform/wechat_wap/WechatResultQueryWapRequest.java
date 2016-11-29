package com.wuswoo.easypay.service.platform.wechat_wap;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.service.request.BaseRequest;
import com.wuswoo.easypay.service.request.PaymentRequest;
import weixin.popular.util.SignatureUtil;

/**
 * Created by wuxinjun on 16/11/10.
 */
public class WechatResultQueryWapRequest extends BaseRequest {

    public WechatResultQueryWapRequest() {}
    public WechatResultQueryWapRequest(BaseRequest request) {
        super(request);
    }
    @Override
    public String sign() {
        return null;
    }

}
