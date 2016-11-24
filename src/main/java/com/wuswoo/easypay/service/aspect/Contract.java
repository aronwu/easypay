package com.wuswoo.easypay.service.aspect;

import java.lang.annotation.*;

/**
 * Created by wuxinjun on 16/9/23.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Contract {
    Class<?> value();
}
