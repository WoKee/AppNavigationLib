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
 *
 * com.fph.POST.java
 * 
 * Created by wang on 2016年6月28日上午10:31:18 
 * 
 * Tips:
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface POST {
	//项目拓展
//	String path() default ""; 
//	String method() default "";
	String value()default "";
}
