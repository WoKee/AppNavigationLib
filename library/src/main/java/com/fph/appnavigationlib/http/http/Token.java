/**
 * 
 */
package com.fph.appnavigationlib.http.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

;


/**
 *
 * com.fph.client.hui.imp.httpbuilder.Token.java
 * 
 * Created by wang on 2016年6月24日下午5:32:02 
 * 
 * Tips:
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Token {
}
