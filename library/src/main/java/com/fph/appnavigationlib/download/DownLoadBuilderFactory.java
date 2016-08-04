/**
 * 
 */
package com.fph.appnavigationlib.download;

/**
 *
 * com.fph.DownLoadBuilderFactory.java
 * 
 * Created by wang on 2016年7月1日下午3:06:59 
 * 
 * Tips:文件下载 http工厂 
 */
public interface DownLoadBuilderFactory {

	void addHeader(String key, String value);
	
	DownLoadResponse create(String url, long completeSize, Object tag);
	
}
