/**
 * 
 */
package com.fph.appnavigationlib.callback;

import android.system.ErrnoException;

import com.fph.appnavigationlib.bean.UploadFileRootInfo;
import com.fph.appnavigationlib.utils.GsonUtil;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * com.fph.BaseUploadFileCallback.java
 * 
 * Created by wang on 2016年7月13日下午3:20:32
 * 
 * Tips:文件上传 带进度
 */
public class BaseUploadFileCallback extends Callback<String> {

	private UploadFileCallBack uploadFileCallBack;
	private UploadFileRootInfo uploadFileRootInfo;

	public BaseUploadFileCallback(UploadFileCallBack uploadFileCallBack) {
		this.uploadFileCallBack = uploadFileCallBack;
		uploadFileRootInfo = new UploadFileRootInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#inProgress(float, long, int)
	 */
	@Override
	public void inProgress(float progress, long total, int id) {
		// TODO Auto-generated method stub
		super.inProgress(progress, total, id);
		if (uploadFileCallBack != null) {
			uploadFileCallBack.inProgress(progress, total);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#onError(okhttp3.Call,
	 * java.lang.Exception, int)
	 */
	@Override
	public void onError(Call arg0, Exception arg1, int arg2) {
		// TODO Auto-generated method stub
		if (arg1 == null) {
			if (arg1 instanceof UnknownHostException || arg1 instanceof UnknownServiceException) {
				uploadFileRootInfo.setCode(-1);
				uploadFileRootInfo.setMsg("网络异常");
			} else if (arg1 instanceof IOException && arg1.getMessage().equals("Canceled")) {
				uploadFileRootInfo.setCode(-2);
				uploadFileRootInfo.setMsg("取消上传");
				if (uploadFileCallBack != null) {
					uploadFileCallBack.onCancel(uploadFileRootInfo);
				}
				return;
//				  java.net.SocketTimeoutException: timeout
//				   android.system.ErrnoException: recvfrom failed: ETIMEDOUT (Connection timed out)
			} else if ((arg1 instanceof java.net.SocketTimeoutException && arg1.getMessage().equals("timeout"))
					||(arg1 instanceof ErrnoException && arg1.getMessage().equals("recvfrom failed: ETIMEDOUT (Connection timed out)"))) {
				uploadFileRootInfo.setCode(-4);
				uploadFileRootInfo.setMsg("连接超时");
			}else {
				uploadFileRootInfo.setCode(-3);
				uploadFileRootInfo.setMsg("未知错误");
			}

		}else{
			uploadFileRootInfo.setCode(-3);
			uploadFileRootInfo.setMsg("未知错误");
		}
		
		if (uploadFileCallBack != null) {
			uploadFileCallBack.onError(uploadFileRootInfo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#onResponse(java.lang.Object,
	 * int)
	 */
	@Override
	public void onResponse(String arg0, int arg1) {
		// TODO Auto-generated method stub

		uploadFileRootInfo = GsonUtil.fromJson(arg0, UploadFileRootInfo.class);

		if (uploadFileRootInfo == null) {
			uploadFileRootInfo = new UploadFileRootInfo();
			uploadFileRootInfo.setMsg("未知错误");
		}
		uploadFileRootInfo.setOriginResult(arg0);
//		if (uploadFileRootInfo.getCode() == 200) 
		{
			if (uploadFileCallBack != null) {
				uploadFileCallBack.onSuccess(uploadFileRootInfo);
			}
		} 
//		else {
//			if (uploadFileCallBack != null) {
//				uploadFileCallBack.onError(uploadFileRootInfo);
//			}
//		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#onBefore(okhttp3.Request, int)
	 */
	@Override
	public void onBefore(Request request, int id) {
		// TODO Auto-generated method stub
		uploadFileRootInfo.setTag(request.tag());
		super.onBefore(request, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zhy.http.okhttp.callback.Callback#validateReponse(okhttp3.Response,
	 * int)
	 */
	@Override
	public boolean validateReponse(Response response, int id) {
		// TODO Auto-generated method stub
		return super.validateReponse(response, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#parseNetworkResponse(okhttp3.
	 * Response, int)
	 */
	@Override
	public String parseNetworkResponse(Response arg0, int arg1) throws Exception {
		// TODO Auto-generated method stub
		Response copyResponse = arg0;
		return copyResponse.body().string();
	}

}
