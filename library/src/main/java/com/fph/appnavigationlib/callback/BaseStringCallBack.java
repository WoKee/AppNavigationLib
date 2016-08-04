/**
 * 
 */
package com.fph.appnavigationlib.callback;

import android.os.Handler;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 *
 * com.fph.appnavigationlib.imp.BaseStringCallBack.java
 * 
 * Created by wang on 2016下午3:21:46 
 * 
 * Tips:
 */
public class BaseStringCallBack extends StringCallback{

	protected Handler mHandler;
	
	protected int mWhat;
	
	/**
	 * 
	 */
	public BaseStringCallBack(Handler handler, int what) {
		// TODO Auto-generated constructor stub
		mHandler=handler;
		mWhat=what;
	}
	

	/* (non-Javadoc)
	 * @see com.zhy.http.okhttp.callback.Callback#onError(okhttp3.Call, java.lang.Exception, int)
	 */
	@Override
	public void onError(Call arg0, Exception arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.zhy.http.okhttp.callback.Callback#onResponse(java.lang.Object, int)
	 */
	@Override
	public void onResponse(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
