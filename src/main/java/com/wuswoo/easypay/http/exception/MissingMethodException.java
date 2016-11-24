package com.wuswoo.easypay.http.exception;

/**
 * Created by wuxinjun on 16/9/22.
 */
public class MissingMethodException extends RuntimeException {
    private static final long serialVersionUID = 363262906609524387L;
    private final String method;

    public MissingMethodException(String method) {
        super(String.format("method:%s is not existed", method));
        this.method = method;
    }

    public MissingMethodException(String method, Throwable cause) {
        super(String.format("method:%s is not existed", method), cause);
        this.method = method;
    }

    public String method() {
        return this.method;
    }
}
