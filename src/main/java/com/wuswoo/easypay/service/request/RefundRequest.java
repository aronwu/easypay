package com.wuswoo.easypay.service.request;

import io.netty.handler.codec.http.QueryStringEncoder;

import java.util.Map;

/**
 * Created by wuxinjun on 16/9/23.
 */
public abstract class RefundRequest extends BaseRequest {
    public RefundRequest() {
        super();
    }

    public RefundRequest(BaseRequest request) {
        super(request);
    }

    public String getSignQueryString() {
        sign();
        QueryStringEncoder encoder = new QueryStringEncoder("?");
        for(Map.Entry<String, String> entry:getQueryParams().entrySet()) {
            encoder.addParam(entry.getKey(), entry.getValue());
        }
        String qsd = encoder.toString();
        return qsd.replaceFirst("?", "");
    }
}
