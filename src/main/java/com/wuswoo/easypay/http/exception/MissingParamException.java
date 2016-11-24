package com.wuswoo.easypay.http.exception;

/**
 * Created by wuxinjun on 16/9/22.
 */
public class MissingParamException extends RuntimeException {
    private static final long serialVersionUID = 363262906609529387L;
    private final String param;

    public MissingParamException(String param) {
        super(String.format("param:%s is not existed", param));
        this.param = param;
    }

    public MissingParamException(String param, Throwable cause) {
        super(String.format("param:%s is not existed", param), cause);
        this.param = param;
    }

    public String param() {
        return this.param;
    }
}
