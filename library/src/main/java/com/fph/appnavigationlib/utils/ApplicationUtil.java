/**
 * 
 */
package com.fph.appnavigationlib.utils;

import android.content.Context;

/**
 *
 * com.fph.ApplicationUtil.java
 * 
 * Created by wang on 2016下午4:06:51 
 * 
 * Tips: utils context注入,控制内存泄漏 保证applicationContext持有者只有一个
 */
public class ApplicationUtil {

	public static Context applicationContext;
	
	private ApplicationUtil(){
		
	}
	
	public static void initContext(Context context){
		applicationContext=context;
	}
	
	public static Context getApplicationContext(){
		return applicationContext;
	}
	
}
