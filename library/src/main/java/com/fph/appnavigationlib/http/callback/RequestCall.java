/**
 * 
 */
package com.fph.appnavigationlib.http.callback;

import android.os.Handler;

import com.fph.appnavigationlib.callback.UploadFileCallBack;

/**
 *
 * com.fph.RequestCall.java
 * 
 * Created by wang on 2016年6月23日下午6:27:05 
 * 
 * Tips:
 */
public interface RequestCall<T> {
	Object send(ResponseCallBack<T> responseCallBack);
	Object send(Handler handler);
	Object send(Handler handler, boolean isRequest);
	Object uploadFile(UploadFileCallBack callBack);
}
