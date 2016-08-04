/**
 * 
 */
package com.fph.appnavigationlib.http;

import android.os.Handler;

import com.fph.appnavigationlib.callback.UploadFileCallBack;
import com.fph.appnavigationlib.http.callback.BuilderCallFactory;
import com.fph.appnavigationlib.http.callback.RequestCall;
import com.fph.appnavigationlib.http.callback.ResponseCallBack;

/**
 *
 * com.fph.appnavigationlib.http.HttpResponse.java
 * 
 * Created by wang on 2016年6月23日下午6:30:52 
 * 
 * Tips:
 */
public class HttpRequest<T> implements RequestCall<T> {

	
	private HttpUrl httpUrl;
	
	private ResponseCallBack<T> callBack;
	private BuilderCallFactory<T> buildCallFactory;
	/* (non-Javadoc)
	 * @see com.fph.RequestCall#send(com.fph.ResponseCallBack, com.fph.HttpUrl)
	 */
	@Override
	public Object send(ResponseCallBack<T> responseCallBack ) {
		// TODO Auto-generated method stub
		callBack = responseCallBack;
		if (buildCallFactory != null) {
			return buildCallFactory.creat(this, callBack,null );
		}
		return null;
	}
	/**
	 * @return the httpUrl
	 */
	public HttpUrl getHttpUrl() {
		return httpUrl;
	}
	/**
	 * @param httpUrl the httpUrl to set
	 */
	public void setHttpUrl(HttpUrl httpUrl) {
		this.httpUrl = httpUrl;
	}
	/**
	 * @return the callBack
	 */
	public ResponseCallBack<T> getCallBack() {
		return callBack;
	}
	/**
	 * @param callBack the callBack to set
	 */
	public void setCallBack(ResponseCallBack<T> callBack) {
		this.callBack = callBack;
	}
	/**
	 * @return the buildCallFactory
	 */
	public BuilderCallFactory<T> getBuildCallFactory() {
		return buildCallFactory;
	}
	/**
	 * @param buildCallFactory the buildCallFactory to set
	 */
	public void setBuildCallFactory(BuilderCallFactory<T> buildCallFactory) {
		this.buildCallFactory = buildCallFactory;
	}
	/* (non-Javadoc)
	 * @see com.fph.RequestCall#send(android.os.Handler, long)
	 */
	@Override
	public Object send(Handler handler ) {
		// TODO Auto-generated method stub
//		if (buildCallFactory != null) {
//			return buildCallFactory.creat(this, null,handler );
//		}
		send(handler,true);
		return null;
	}
	/* (non-Javadoc)
	 * @see com.fph.RequestCall#uploadFile(com.zhy.http.okhttp.callback.FileCallBack)
	 */
	@Override
	public Object uploadFile(UploadFileCallBack callBack) {
		// TODO Auto-generated method stub
		if (buildCallFactory != null) {
			return buildCallFactory.creat(this, callBack );
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.fph.RequestCall#send(android.os.Handler, boolean)
	 */
	@Override
	public Object send(Handler handler, boolean isRequest) {
		// TODO Auto-generated method stub
		if (buildCallFactory != null) {
			return buildCallFactory.creat(this, null,isRequest?handler:null);
		}
		return null;
	}
	

}
