package com.wuswoo.easypay.common.util;

/**
 * Created by wuxinjun on 16/9/8.
 */
public class TimeCost {

    private transient long start;

    public TimeCost() {
    }

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public long cost() {
        return System.currentTimeMillis() - start;
    }
}
