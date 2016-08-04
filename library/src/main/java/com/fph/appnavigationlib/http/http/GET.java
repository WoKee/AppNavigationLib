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
 * com.fph.GET.java
 * 
 * Created by wang on 2016年6月23日下午4:21:58 
 * 
 * Tips:
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface GET {
	String path() default "";
	boolean  isEncrypt() default true;
	String requsetTag() default "";

}
