package com.wuswoo.easypay.service.payment;

import java.util.Map;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface ISignature {
    public String getKey();
    public void setKey(String key);
    public String sign(Map<String, String> map, String key);
}
