/**
 * 
 */
package com.fph.appnavigationlib.utils;

import okhttp3.OkHttpClient;

/**
 *
 * com.fph.OkHttpInstance.java
 * 
 * Created by wang on 2016下午2:18:16 
 * 
 * Tips:
 */
public class OkHttpInstance {

	
	private  OkHttpInstance(){}
	
	
	private static class Holder{
		public static OkHttpClient okHttpClient=new OkHttpClient().newBuilder().build();
	}
	
	public static OkHttpClient getInstance(){
			return Holder.okHttpClient;
	}
	
}
