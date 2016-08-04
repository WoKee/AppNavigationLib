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
 * com.fph.Tag.java
 * 
 * Created by wang on 2016年6月24日上午9:55:08 
 * 
 * Tips:
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Tag {
  int value() ;	
}
