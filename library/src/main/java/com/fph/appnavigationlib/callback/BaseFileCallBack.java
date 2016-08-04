/**
 * 
 */
package com.fph.appnavigationlib.callback;

import android.os.Handler;

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 *
 * com.fph.appnavigationlib.imp.BaseFileCallBack.java
 * 
 * Created by wang on 2016下午3:36:09 
 * 
 * Tips:
 */
public class BaseFileCallBack extends FileCallBack{
	
	
	protected Handler mHandler;
	
	protected int mWhat;
	/**
	 * @param destFileDir
	 * @param destFileName
	 */
	public BaseFileCallBack(String destFileDir, String destFileName, Handler handler , int what) {
		super(destFileDir, destFileName);
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
	public void onResponse(File arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	/* (non-Javadoc)
	 * @see com.zhy.http.okhttp.callback.Callback#inProgress(float, long, int)
	 */
	@Override
	public void inProgress(float progress, long total, int id) {
		// TODO Auto-generated method stub
		super.inProgress(progress, total, id);
	}
}
