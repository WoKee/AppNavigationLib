/**
 * 
 */
package com.fph.appnavigationlib.base;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 *
 * com.fph.BaseHandler.java
 * 
 * Created by wang on 2016下午5:43:47 
 * 
 * Tips:防止内存泄漏
 */
@SuppressLint("NewApi")
public class BaseHandler<T> extends Handler {

	WeakReference<T> wrf;
	
	public BaseHandler(T base) {
		// TODO Auto-generated constructor stub
		wrf=new WeakReference<T>(base);
	}
	
	/* (non-Javadoc)
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		
		T base=this.wrf.get();
		if (base==null) {
			return;
		}
		
		if (base instanceof BaseActivity) {
			if (((BaseActivity)base).isFinishing() &&((BaseActivity)base).isDestroyed()) {
				return;
			}
			((BaseActivity)base).handlerMessage(msg);
		}
		
		if (base instanceof BaseFragment) {
			if (((BaseFragment)base).isRemoving() &&((BaseFragment)base).isDetached()) {
				return;
			}
			((BaseFragment)base).handlerMessage(msg);
		}
		
	}
	
}
