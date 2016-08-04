/**
 * 
 */
package com.fph.appnavigationlib.callback;

import android.os.Handler;

import com.fph.appnavigationlib.bean.RootInfo;
import com.fph.appnavigationlib.conts.HttpStatusConts;
import com.fph.appnavigationlib.utils.GsonUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.net.SocketException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * com.fph.appnavigationlib.imp.RootInfoCallBack.java
 * 
 * Created by wang on 2016下午5:40:06
 * 
 * Tips:
 */
public class RootInfoCallBack extends StringCallback {

	protected Handler mHandler;
	
	protected  int mWhat;
	
	protected Request request ;
	
	protected Response response; 

	/**
	 * 
	 */
	public RootInfoCallBack(Handler handler, int what) {
		// TODO Auto-generated constructor stub
		mHandler = handler;
		mWhat = what;
	}
	
	/* (non-Javadoc)
	 * @see com.zhy.http.okhttp.callback.StringCallback#parseNetworkResponse(okhttp3.Response, int)
	 */
	@Override
	public String parseNetworkResponse(Response response, int id) throws IOException {
		// TODO Auto-generated method stub
		this.response =response ;
		return super.parseNetworkResponse(response, id);
	}
	
	/* (non-Javadoc)
	 * @see com.zhy.http.okhttp.callback.Callback#onBefore(okhttp3.Request, int)
	 */
	@Override
	public void onBefore(Request request, int id) {
		// TODO Auto-generated method stub
		super.onBefore(request, id);
		this.request=request;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#onError(okhttp3.Call,
	 * java.lang.Exception)
	 */
	@Override
	public void onError(Call arg0, Exception arg1, int a) {
		// TODO Auto-generated method stub
		
		if (mHandler==null) {
			return;
		}
		// 网络错误 伪装成服务器错误
		RootInfo rootInfo = GsonUtil.fromJson(arg1.getMessage(),RootInfo.class);
		if (rootInfo == null) {
			rootInfo = new RootInfo();
			rootInfo.setMsg("网络异常");
		} else {
			rootInfo.setMsg("服务器开小差了，请稍后重试");
		}
		rootInfo.setRequest(request);
		rootInfo.setResponse(response);
		rootInfo.setServerCode(0);
		rootInfo.setWhat(mWhat);
		if (arg1 instanceof SocketException || (arg1 instanceof IOException && arg1.getMessage().equals("Canceled"))) {
			mHandler.sendMessage(mHandler.obtainMessage(HttpStatusConts.CANCEL, rootInfo));
		}else
		mHandler.sendMessage(mHandler.obtainMessage(HttpStatusConts.ERROR, rootInfo));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zhy.http.okhttp.callback.Callback#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(String arg0, int a) {
		// TODO Auto-generated method stub
		if (mHandler==null) {
			return;
		}
		
		RootInfo rootInfo= GsonUtil.fromJson(arg0, RootInfo.class);
		if (rootInfo == null) {
			// 解析异常 伪装成服务器异常
			rootInfo = new RootInfo();
			rootInfo.setServerCode(0);
			rootInfo.setMsg("解析异常");
			rootInfo.setRequest(request);
			rootInfo.setResponse(response);
		}
		rootInfo.setOriginResult(arg0);//兼容老版本 不解析 returnObject
		if (rootInfo.getServerCode() == 0 || rootInfo.getStatus() != 200) {
			// 服务器错误 伪装ERROR
			if (rootInfo.getServerCode() == 0 && rootInfo.getCode() == 0) {
				rootInfo.setMsg("服务器开小差了，请稍后重试");
			}
			rootInfo.setRequest(request);
			rootInfo.setResponse(response);
			rootInfo.setServerCode(0);
			rootInfo.setWhat(mWhat);
			mHandler.sendMessage(mHandler.obtainMessage(HttpStatusConts.ERROR, rootInfo));
			return;
		}
		
		mHandler.sendMessage(mHandler.obtainMessage(mWhat, rootInfo));
	}



}
