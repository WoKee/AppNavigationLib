/**
 * 
 */
package com.fph.appnavigationlib.http.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * com.fph.BaseMethod.java
 * 
 * Created by wang on 2016年6月23日下午6:18:34 
 * 
 * Tips:
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface BaseMethod {

	boolean  isEncrypt() default true;

	String requsetTag() default "";
}
