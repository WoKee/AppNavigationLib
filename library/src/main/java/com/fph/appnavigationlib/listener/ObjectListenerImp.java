/**
 * 
 */
package com.fph.appnavigationlib.listener;

import android.widget.AbsListView;
import android.widget.ScrollView;

import java.io.File;
import java.io.Serializable;

import okhttp3.Call;

/**
 *
 * com.fph.client.hui.lis.ObjectListenerImp.java
 * 
 * Created by wang on 2016上午11:09:07
 * 
 * Tips:所有的事件回调接口抽象
 */
public interface ObjectListenerImp extends Serializable {

	/**
	 * scrollview 滚动事件
	 * 
	 * @param scrollView
	 * @param l
	 * @param t
	 * @param oldl
	 * @param oldt
	 */
	void onScrollChanged(ScrollView scrollView, int l, int t, int oldl, int oldt);

	/**
	 * scrollview 绘制完成
	 * 
	 * @param isEnd
	 */
	void onGlobalLayoutEnd(boolean isEnd);

	/**
	 * AbsListView 滚动事件
	 * 
	 * @param view
	 * @param firstVisibleItem
	 * @param visibleItemCount
	 * @param totalItemCount
	 * @param scrollY
	 */
	void onSrollChanged(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int scrollY);

	/**
	 * 键盘监听事件
	 * 
	 * @param softKeybardHeight
	 * @param visible
	 */
	void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);

	/**
	 * 下载回调错误
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	void onDownLoadError(Call arg0, Exception arg1, int arg2);

	/**
	 * 下载请求头
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public void onDownLoadResponse(File arg0, int arg1);

	/**
	 * 下载回调进度条
	 * 
	 * @param progress
	 * @param total
	 * @param id
	 */
	public void onDownLoadProgress(float progress, long total, int id);

}
