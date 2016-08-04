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
 * com.fph.Header.java
 * 
 * Created by wang on 2016年6月23日下午4:26:53 
 * 
 * Tips:
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Header {
	String value() default "";
}
