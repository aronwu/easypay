package com.wuswoo.easypay.http.exception;

/**
 * Created by wuxinjun on 16/9/23.
 */
public class HttpExceptionResponse {

    private String code;
    private String message;

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

    public HttpExceptionResponse() {}

    public HttpExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
