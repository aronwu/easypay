package com.wuswoo.easypay.service.request;

import java.util.Map;

/**
 * Created by wuxinjun on 16/9/23.
 */
public abstract class PaymentRequest extends BaseRequest {
    public PaymentRequest() {
        super();
    }

    public PaymentRequest(BaseRequest request) {
        super(request);
    }

    public String generateSignFormHiddenFields() {
        sign();
        StringBuilder stb = new StringBuilder();
        for (Map.Entry<String, String> entry : getQueryParams().entrySet()) {
            if (entry.getValue() != null)
                stb.append(String
                    .format("<input type=\"hidden\" value=\"%s\" name=\"%s\" />", entry.getValue(),
                        entry.getKey()));

        }
        return stb.toString();
    }

}
