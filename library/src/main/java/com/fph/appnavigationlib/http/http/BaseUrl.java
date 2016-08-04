/**
 * 
 */
package com.fph.appnavigationlib.http.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * com.fph.BaseUrl.java
 * 
 * Created by wang on 2016年6月23日下午4:28:15
 * 
 * Tips:
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface BaseUrl {
	String value() default "";
}
