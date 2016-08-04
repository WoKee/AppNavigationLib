/**
 * 
 */
package com.fph.appnavigationlib.bean;

import com.fph.appnavigationlib.utils.GsonUtil;

import java.io.Serializable;

import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * com.fph.RootInfo.java
 * 
 * Created by wang on 2016下午4:30:06
 * 
 * Tips:全局网络请求回掉bean
 */
public class RootInfo<V,D,O> extends RootConvert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3005744891481213293L;

	private int serverCode;

	private int errorCode;

	private int status;

	private int  msgType;

	private String msg;

	private V returnValue;

	private O returnObject;
	
	private int what;//错误伪装时的what
	
	private D data;
	
	private int code;

	private Request request;//请求头部
	
	private Response response;//请求结果
	
	private BaseRequestBean baseRequestBean ;// 自定义请求数据头部
	
	
	
	




	/**
	 * @return the returnValue
	 */
	public V getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(V returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * @return the returnObject
	 */
	public O getReturnObject() {
		return returnObject;
	}

	/**
	 * @param returnObject the returnObject to set
	 */
	public void setReturnObject(O returnObject) {
		this.returnObject = returnObject;
	}

	/**
	 * @return the data
	 */
	public D getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(D data) {
		this.data = data;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the baseRequestBean
	 */
	public BaseRequestBean getBaseRequestBean() {
		
		if (baseRequestBean == null || baseRequestBean.getBody() == null || baseRequestBean.getBody().getDelegate() == null 
				|| baseRequestBean.getBody().getDelegate().getEncodeMap()==null || baseRequestBean.getBody().getDelegate().getEncodeMap().size() ==0) {
			
			baseRequestBean = GsonUtil.fromJson(GsonUtil.toJson(request),BaseRequestBean.class);
		
		}
		
		return baseRequestBean;
	}

	/**
	 * @param baseRequestBean the baseRequestBean to set
	 */
	public void setBaseRequestBean(BaseRequestBean baseRequestBean) {
		this.baseRequestBean = baseRequestBean;
	}

	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	


	/**返回请求是否成功
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return getServerCode()!=0&&getStatus()==200;
	}

	/**
	 * @return the serverCode
	 */
	public int getServerCode() {
		return serverCode;
	}

	/**
	 * @param serverCode the serverCode to set
	 */
	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the msgType
	 */
	public int getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}





	/**
	 * @return the what
	 */
	public int getWhat() {
		return what;
	}

	/**
	 * @param what the what to set
	 */
	public void setWhat(int what) {
		this.what = what;
	}




	/*
	 * serverCode Init 是 服务返回code 0: 服务端异常 1: 服务端正常 errorCode Init 是 业务返回code
	 * 200: 业务端正常 404: not found http exception in application 405: method not
	 * allowed http exception in application 其它：业务型说明或返回message status Init 是
	 * 返回状态 200、404、405、500 msgType String 是 弹出框类型 1：一个按钮 2:2个按钮 msg String 是
	 * 弹出框提示 业务型说明或返回弹出框提示 returnValue String 否 返回非object 返回数据是非数组 returnObject
	 * Object 否 返回object 返回数据数组
	 * 
	 * 200 ok 接口正常 404 not found http exception in application 405 method not
	 * allowed http exception in application 服务器拒绝 500 service is gone 服务器无法访问
	 * 10000 invalid parameter 参数缺省
	 * 
	 */
	
	

}
