package com.wuswoo.easypay.service.exception;

import com.wuswoo.easypay.http.exception.HttpExceptionResponse;
import com.wuswoo.easypay.http.exception.IExceptionToHttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/8.
 */
public class EasyPayException extends RuntimeException implements IExceptionToHttpResponse {

    /**
     * 成功
     */
    public static final String API_OK = "200";
    /**
     * 部分成功
     */
    public static final String API_PARTIAL_OK = "206";
    /**
     * 未知错误
     **/
    public static final String UNKNOWN_ERROR = "400";
    /**
     * 对象不存在
     */
    public static final String NOT_EXIST = "404";
    /**
     * 服务器内部错误
     */
    public static final String GENERAL_ERROR = "500";
    /**
     * 参数验证错误
     */
    public static final String VALIDATE_ERROR = "501";
    /**
     * 接口已关闭
     */
    public static final String NOT_IMPLEMENTED = "502";
    /**
     * 用户验证错误
     */
    public static final String AUTHEN_ERROR = "1000";

    /** 一般错误代码1000-2000 **/
    /**
     * 用户授权错误
     */
    public static final String AUTHOR_ERROR = "1001";
    /**
     * 网络错误
     */
    public static final String NETWORK_ERROR = "1002";
    /**
     * 签名验证失败
     */
    public static final String SIGN_VERIFY_ERROR = "1003";
    /**
     * 输入参数错误
     */
    public static final String INPUT_CONTRACT_ERROR = "1004";
    /**
     * 微信错误定义 2000-3000
     */
    public static final String WECHAT_PAYMENT_ERROR = "2000";
    public static final String WECHAT_NOTIFY_ERROR = "20001";
    public static final String WECHAT_SIGN_INVALIDATE = "2002";
    public static final String WECHAT_BLANK_OPENID = "2003";
    public static final String WECHAT_PAY_NOTIFY_NOT_SUPPORT = "20004";
    public static final String WECHAT_UNKNOWN_ERROR = "3000";
    public static final EasyPayException NOT_EXIST_EXCEPTION =
        new EasyPayException("对象不存在", NOT_EXIST);
    public static final EasyPayException GENERAL_EXCEPTION =
        new EasyPayException("服务器内部错误", GENERAL_ERROR);
    public static final EasyPayException VALIDATE_EXCEPTION =
        new EasyPayException("参数验证错误", VALIDATE_ERROR);
    public static final EasyPayException NOT_IMPLEMENTED_EXCEPTION =
        new EasyPayException("接口未实现", NOT_IMPLEMENTED);
    public static final EasyPayException AUTHEN_EXCEPTION =
        new EasyPayException("用户认证失败", AUTHEN_ERROR);
    public static final EasyPayException AUTHOR_EXCEPTION =
        new EasyPayException("授权验证失败", AUTHOR_ERROR);
    public static final EasyPayException NETWORK_EXCEPTION =
        new EasyPayException("网络错误", NETWORK_ERROR);
    public static final EasyPayException SIGN_VERIFY_EXCEPTION =
        new EasyPayException("签名验证失败", SIGN_VERIFY_ERROR);
    public static final EasyPayException INPUT_CONTRACT_EXCEPTION =
        new EasyPayException("输入信息有误", INPUT_CONTRACT_ERROR);
    private static final long serialVersionUID = 2L;
    private String code;

    private List<String> errors = new ArrayList<String>();

    public EasyPayException() {
    }

    public EasyPayException(String message) {
        super(message);
    }

    public EasyPayException(String message, String code) {
        super(message);
        setCode(code);
    }

    public EasyPayException(String message, String code, List<String> errors) {
        super(message);
        setCode(code);
        setErrors(errors);
    }

    public EasyPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyPayException(String message, String code, Throwable cause) {
        super(message, cause);
        setCode(code);
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

    @Override
    public HttpExceptionResponse getHttpResponse() {
        HttpExceptionResponse httpExceptionResponse = new HttpExceptionResponse();
        httpExceptionResponse.setCode(this.getCode());
        httpExceptionResponse.setMessage(this.getMessage());
        httpExceptionResponse.setErrors(this.getErrors());
        return httpExceptionResponse;
    }
}
