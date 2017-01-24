package com.wuswoo.easypay.http.exception;

/**
 * Created by wuxinjun on 16/9/22.
 */
public class MissingUriException extends RuntimeException {
    private static final long serialVersionUID = 363262906609529379L;
    private final String uri;

    public MissingUriException(String uri) {
        super(String.format("uri:%s is not match", uri));
        this.uri = uri;
    }

    public MissingUriException(String uri, Throwable cause) {
        super(String.format("uri:%s is not match", uri), cause);
        this.uri = uri;
    }

    public String uri() {
        return uri;
    }
}
