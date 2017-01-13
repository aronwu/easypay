package com.wuswoo.easypay.service.controller.impl;

import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;
import com.wuswoo.easypay.service.aspect.Contract;
import com.wuswoo.easypay.service.controller.IStatusController;
import org.springframework.stereotype.Controller;

/**
 * Created by wuxinjun on 17/1/12.
 */
@Controller("statusController")
public class StatusController implements IStatusController {
    @Override
    public void ok(Request request, Response response) throws Exception {
        response.respondText("ok");
    }
}
