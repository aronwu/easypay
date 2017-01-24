package com.wuswoo.easypay.common.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuxinjun on 16/8/30.
 */
public class ThreadPoolUtil {
    public final static ThreadPoolExecutor executor =
        new ThreadPoolExecutor(3, 5, 10, TimeUnit.MINUTES,
            new LinkedBlockingDeque<Runnable>(100000));

    public static ThreadPoolExecutor getExecutor() {
        return executor;
    }
}
