package com.wuswoo.easypay.service.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.common.util.ReflectionUtil;
import com.wuswoo.easypay.common.util.ValidationUtil;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxinjun on 16/9/23.
 */

@Aspect
public class ContractAspect {
    private static final Logger logger = LogManager.getLogger(ContractAspect.class);

    @Pointcut("@annotation (com.wuswoo.easypay.service.aspect.Contract)")
    private void contract() {
    }

    ;

    @Around("contract()")
    public Object contract(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 1 && args[0] instanceof Request) {
            Request request = (Request) args[0];
            Method method = ReflectionUtil.getJoinPointMethod(joinPoint);
            Contract contract = method.getAnnotation(Contract.class);
            if (contract != null) {
                Class<?> clazz = contract.value();
                Object object = JSONObject.toJavaObject(request.json(), clazz);
                Map<String, List<String>> validationResult = ValidationUtil.validator(object, null);
                if (validationResult == null || validationResult.size() == 0) {
                    request.setContract(object);
                    return joinPoint.proceed();
                } else {
                    //throw validation errors exception
                    List<String> errors = new ArrayList<String>();
                    for (Map.Entry<String, List<String>> entry : validationResult.entrySet()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(entry.getKey() + ":");
                        for (String er : entry.getValue()) {
                            sb.append(er + "\n");
                        }
                        errors.add(sb.toString());
                    }
                    //格式化Exception内容
                    throw new EasyPayException("输入请求参数错误", EasyPayException.INPUT_CONTRACT_ERROR,
                        errors);
                }
            } else {
                //throw no contract class value  exception
                throw new EasyPayException("输入协议错误", EasyPayException.INPUT_CONTRACT_ERROR);
            }
        } else {
            return joinPoint.proceed();
        }
    }

}
