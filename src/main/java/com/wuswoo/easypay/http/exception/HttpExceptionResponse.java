package com.wuswoo.easypay.http.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/23.
 */
public class HttpExceptionResponse {

    private String code;
    private String message;
    private List<String> errors = new ArrayList<String>();

    public HttpExceptionResponse() {
    }

    public HttpExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(String error) {
        if (errors == null)
            errors = new ArrayList<String>();
        errors.add(error);
    }


}
