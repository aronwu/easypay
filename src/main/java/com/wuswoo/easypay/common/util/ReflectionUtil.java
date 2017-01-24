package com.wuswoo.easypay.common.util;

import org.apache.kafka.common.cache.LRUCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by wuxinjun on 16/9/23.
 */
public class ReflectionUtil {

    private static final LRUCache<String, HashMap<String, Field>> classFieldsCache =
        new LRUCache<String, HashMap<String, Field>>(50);


    public static Method getJoinPointMethod(ProceedingJoinPoint joinPoint)
        throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            method = joinPoint.getTarget().getClass()
                .getDeclaredMethod(method.getName(), method.getParameterTypes());
        }
        return method;
    }

    private static HashMap<String, Field> getClassFields(Class<?> clazz) {
        HashMap<String, Field> classFields = classFieldsCache.get(clazz.getName());
        if (classFields == null) {
            classFields = new HashMap<String, Field>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                classFields.put(field.getName(), field);
            }
            classFieldsCache.put(clazz.getName(), classFields);
        }
        return classFields;
    }

    public static <T> T convert2Clazz(Object object, Class<T> clazz)
        throws IllegalAccessException, InstantiationException {
        Field[] fields = object.getClass().getDeclaredFields();
        T newObject = clazz.newInstance();
        HashMap<String, Field> classFields = getClassFields(clazz);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                Field classField = classFields.get(field.getName());
                if (classField != null) {
                    classField.setAccessible(true);
                    classField.set(newObject, value);
                }
            } catch (Exception e) {
            }
        }
        return newObject;
    }

    public static void convert2Object(Object from, Object to)
        throws IllegalAccessException, InstantiationException {
        Field[] fields = from.getClass().getDeclaredFields();
        Class clazz = to.getClass();
        HashMap<String, Field> classFields = getClassFields(clazz);
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(from);
                Field classField = classFields.get(field.getName());
                if (classField != null) {
                    classField.setAccessible(true);
                    classField.set(to, value);
                }
            } catch (Exception e) {

            }
        }
    }

}
