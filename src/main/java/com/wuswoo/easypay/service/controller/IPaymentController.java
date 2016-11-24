package com.wuswoo.easypay.service.controller;


import com.wuswoo.easypay.http.controller.IController;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;

/**
 * 支付接口
 * Created by wuxinjun on 16/9/13.
 */
public interface IPaymentController extends IController {
    /**
     * 下单接口
     * @param request
     * @return
     */
    void postPayment(Request request, Response response) throws Exception;

    /**
     * 查询订单状态接口
     * @param request
     * @return
     */
    void getPayment(Request request, Response response) throws Exception;

    /**
     * 退款接口
     * @param request
     * @return
     */
    void postRefund(Request request, Response response) throws Exception;

    /**
     * 查询退款接口
     * @param request
     * @return
     */
    void getRefund(Request request, Response response) throws Exception;
}
