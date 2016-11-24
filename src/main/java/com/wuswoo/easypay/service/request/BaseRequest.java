package com.wuswoo.easypay.service.request;

import com.wuswoo.easypay.service.payment.ISignature;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名请求URL和请求参数
 * Created by wuxinjun on 16/9/23.
 */
public abstract class BaseRequest {
    private Map<String, String> queryParams;
    private String url;
    protected ISignature signature;

    public BaseRequest() {
        this.queryParams = new HashMap<String, String>();
    }

    public BaseRequest(BaseRequest request) {
        this.queryParams = new HashMap<String, String>();
        this.queryParams.putAll(request.getQueryParams());
        this.setUrl(request.getUrl());
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ISignature getSignature() {
        return signature;
    }

    public void setSignature(ISignature signature) {
        this.signature = signature;
    }

    public String getQueryParam(String name) {
        return queryParams.get(name);
    }

    public String removeQueryParam(String name) {
        return queryParams.remove(name);
    }

    public void addParam(String name, String value) {
        this.queryParams.put(name, value);
    }

    public abstract String sign();
}
