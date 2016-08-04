/**
 * 
 */
package com.fph.appnavigationlib.download;

/**
 *
 * com.fph.DownLoadStateChangeListener.java
 * 
 * Created by wang on 2016年7月1日下午4:32:37 
 * 
 * Tips:
 */
public interface DownLoadStateChangeListener {

	
	
	void onResume(Object tag);
	
	void onPause(Object tag);
	
	void onCancel(Object tag);
	
}
