/**
 * 
 */
package com.fph.appnavigationlib.download;

/**
 *
 * com.fph.DownLoadState.java
 * 
 * Created by wang on 2016年7月1日下午3:10:12 
 * 
 * Tips:
 */
public enum DownLoadState {
	INIT,//初始化
	PREPARED,//准备下载
	START,//开始下载
	DOWNDING,//下载中
	PAUSE,//暂停下载
	CANCEL,//取消下载
	RESUME,//恢复下载
	FAIL,//下载失败
	SUCCESS;//下载成功
	
}
