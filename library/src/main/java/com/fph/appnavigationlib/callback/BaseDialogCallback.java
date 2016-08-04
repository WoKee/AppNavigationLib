/**
 * 
 */
package com.fph.appnavigationlib.callback;

import android.content.Context;

/**
 *
 * com.fph.appnavigationlib.imp.BaseDialogCallback.java
 * 
 * Created by wang on 2016上午10:48:58 
 * 
 * Tips:
 */
public interface BaseDialogCallback {
	/**
	 * 返回键事件 携带context 判断是否是当前界面引用
	 * @param context
	 */
	void onBackKeyDown(Context context);
}
