package com.wuswoo.easypay.service.controller;


import com.wuswoo.easypay.http.controller.IController;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;

/**
 * 状态接口
 * Created by wuxinjun on 16/9/13.
 */
public interface IStatusController extends IController {

    /**
     * 测试接口
     * @param request
     * @return
     */
    void ok(Request request, Response response) throws Exception;

}
