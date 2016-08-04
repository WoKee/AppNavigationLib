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
 * com.fph.Query.java
 * 
 * Created by wang on 2016年6月23日下午4:41:17
 * 
 * Tips:
 */

@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Query {
	String value() default "";
	boolean encoded() default false;

}
