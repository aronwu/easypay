package com.wuswoo.easypay.http.controller;

/*
 * Created by wuxinjun on 16/9/21.
 */
public interface IControllerAdapter {

    /**
     * 根据beanid 获取对应controller实例
     * @param beanId
     * @return
     */
    public IController getControllerByBeanId(String beanId);
}
