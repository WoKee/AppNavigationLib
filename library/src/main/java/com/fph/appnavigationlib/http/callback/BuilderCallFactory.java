/**
 * 
 */
package com.fph.appnavigationlib.http.callback;


import android.os.Handler;

import com.fph.appnavigationlib.callback.UploadFileCallBack;
import com.fph.appnavigationlib.http.HttpRequest;
import com.fph.appnavigationlib.http.HttpUrl;

import java.util.List;

/**
 *
 * com.fph.BuilderCallFactory.java
 * 
 * Created by wang on 2016年6月23日下午6:11:03 
 * 
 * Tips:请求创建工厂
 */
public interface BuilderCallFactory<T> {
	void addUrl(HttpUrl httpUrl);
	void addHeader(String key, String value);
	void addBody(String key, String value);
	void addFile(String key, List<String> value);
	void addFile(String key, String value);
	void addQuery(String key, String value);
	T creat(HttpRequest<T> httpRequest, ResponseCallBack<T> responseCallBack, Handler handler);
	T creat(HttpRequest<T> httpRequest, UploadFileCallBack fileCallBack);

//	 void cancelByTag(Object o);
}
