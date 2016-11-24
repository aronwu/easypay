package com.wuswoo.easypay.service.controller;

import com.wuswoo.easypay.http.controller.IController;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;

/**
 * 第三方支付回调接口
 * Created by wuxinjun on 16/9/13.
 */
public interface INotifyController extends IController {

    void notifyPayment(Request request, Response response) throws Exception;

    void notifyRefund(Request request, Response response) throws Exception;

}
