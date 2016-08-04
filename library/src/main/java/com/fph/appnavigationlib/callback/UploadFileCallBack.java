/**
 * 
 */
package com.fph.appnavigationlib.callback;

import com.fph.appnavigationlib.bean.UploadFileRootInfo;

/**
 *
 * com.fph.UploadFileCallBack.java
 * 
 * Created by wang on 2016年7月13日下午3:16:29
 * 
 * Tips:
 */
public interface UploadFileCallBack {

	void inProgress(float progress, long total);

	void onError(UploadFileRootInfo rootInfo);

	void onSuccess(UploadFileRootInfo rootInfo);

	void onCancel(UploadFileRootInfo rootInfo);
}
