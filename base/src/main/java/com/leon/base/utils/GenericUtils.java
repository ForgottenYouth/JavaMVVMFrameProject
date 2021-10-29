/**
 * FileName: GenericUtils
 * Author: shiwenliang
 * Date: 2021/10/25 10:56
 * Description:
 */
package com.leon.base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class GenericUtils {

    //获取泛型参数的第一个类型class 对象
    public static Class<?> getGenericType(Object obj) {
        Class<?> genericType = null;
        Type gnrcType = obj.getClass().getGenericSuperclass();
        if (gnrcType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) gnrcType;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length > 0) {
                Type type = types[0];
                if (type instanceof Class) {
                    genericType = (Class<?>) type;
                }
            }
        }
        return genericType;
    }
}
