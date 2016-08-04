/**
 * 
 */
package com.fph.appnavigationlib.download;

import java.io.File;

/**
 *
 * com.fph.DownLoadCallBack.java
 * 
 * Created by wang on 2016年7月1日下午3:09:05 
 * 
 * Tips:
 */
public interface  DownLoadCallBack extends DownLoadStateChangeListener {
	/**
	 * 准备下载
	 * @param tag
	 */
	void onPrepare(Object tag);
	/**
	 * 下载成功
	 * @param tag
	 * @param file
	 */
	void onSuccess(Object tag, File file);
	
	/**
	 * 开始下载
	 * @param tag
	 */
	void onStart(Object tag);
	/**
	 * 下载失败
	 * @param tag
	 * @param mString
	 * @param errorCode
	 */
	void onError(Object tag, String mString, int errorCode);
	
	/**
	 * 正在下载
	 * @param completeSize
	 * @param totalSize
	 * @param tag
	 * @return 
	 */
	void onDownLoadProgress(double completeSize, double totalSize, Object tag);
	
}
