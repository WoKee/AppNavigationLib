package com.fph.appnavigationlib.utils;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author HeTao
 * Date 2016-06-16
 * Time 13:34
 * Class
 */
public class TypeUtils {

    public static Type getTypeParameter(Class<?> subclass) {
        for (int i = 0; i <subclass.getGenericInterfaces().length ; i++) {
            Type type=subclass.getGenericInterfaces()[i];
            if (!(type instanceof Class)){
                ParameterizedType parameterized = (ParameterizedType) type;
                return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
            }

        }
        return null;
    }
}
