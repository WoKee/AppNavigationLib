/**
 * 
 */
package com.fph.appnavigationlib.http.callback;

/**
 *
 * com.fph.ResponseCallBack.java
 * 
 * Created by wang on 2016年6月23日下午6:24:27 
 * 
 * Tips:
 */
public interface ResponseCallBack <T>{
	
	void onSuccess(T value, String tag);
	
	void onError(int errorCode, String errorMsg, String tag);
	
}
