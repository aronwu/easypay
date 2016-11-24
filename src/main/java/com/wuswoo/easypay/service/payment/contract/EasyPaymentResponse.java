package com.wuswoo.easypay.service.payment.contract;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxinjun on 16/11/3.
 */
public class EasyPaymentResponse implements Serializable{

    private static final long serialVersionUID = 46335569005225785L;

    private String code = "200";

    private String message = "";
    @JSONField(name = "data")
    private Object data;

    @JSONField(name = "errors")
    private Map<String, ArrayList<String>> errors;

    private long costTime;

    public EasyPaymentResponse() {
        errors = new HashMap<String, ArrayList<String>>();
    }

    public EasyPaymentResponse(Object data) {
        this.data = data;
    }

    public EasyPaymentResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, ArrayList<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, ArrayList<String>> errors) {
        this.errors = errors;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }
}
