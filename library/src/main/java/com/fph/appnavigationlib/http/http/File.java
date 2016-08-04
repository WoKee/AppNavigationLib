/**
 * 
 */
package com.fph.appnavigationlib.http.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * com.fph.File.java
 * 
 * Created by wang on 2016年7月13日下午2:29:18 
 * 
 * Tips:
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface File {
	String value() default "";
}