/**
 * 
 */
package com.fph.appnavigationlib.http;

/**
 *
 * com.fph.HttpUrl.java
 * 
 * Created by wang on 2016年6月22日下午2:41:21 
 * 
 * Tips:网址封装类
 */
public class HttpUrl {

	
	private String baseUrl="";
	
	private String path="";
	
	private String method="";
	
	private HttpMethod httpMethod ;
	
	private boolean isMulit = false;
	
	private boolean isEncrypt =true;
	
	private int requestTag ;
	
	private long currentTimeTag;
	
	
	private boolean token = false;

	private boolean _token =false;
	
	private boolean oldToken =false;
	
	private boolean old_Token=false;
	
	private boolean url = false;

	private boolean url2 =false;
	
	private boolean urls =false;
	
	private boolean urlh5=false;
	
	
	
	

	/**
	 * @return the url
	 */
	public boolean isUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(boolean url) {
		this.url = url;
	}



	/**
	 * @return the url2
	 */
	public boolean isUrl2() {
		return url2;
	}



	/**
	 * @param url2 the url2 to set
	 */
	public void setUrl2(boolean url2) {
		this.url2 = url2;
	}



	/**
	 * @return the urls
	 */
	public boolean isUrls() {
		return urls;
	}



	/**
	 * @param urls the urls to set
	 */
	public void setUrls(boolean urls) {
		this.urls = urls;
	}






	/**
	 * @return the urlh5
	 */
	public boolean isUrlh5() {
		return urlh5;
	}



	/**
	 * @param urlh5 the urlh5 to set
	 */
	public void setUrlh5(boolean urlh5) {
		this.urlh5 = urlh5;
	}



	/**
	 * @return the token
	 */
	public boolean isToken() {
		return token;
	}



	/**
	 * @param token the token to set
	 */
	public void setToken(boolean token) {
		this.token = token;
	}



	/**
	 * @return the _token
	 */
	public boolean is_token() {
		return _token;
	}



	/**
	 * @param _token the _token to set
	 */
	public void set_token(boolean _token) {
		this._token = _token;
	}



	/**
	 * @return the oldToken
	 */
	public boolean isOldToken() {
		return oldToken;
	}



	/**
	 * @param oldToken the oldToken to set
	 */
	public void setOldToken(boolean oldToken) {
		this.oldToken = oldToken;
	}



	/**
	 * @return the old_Token
	 */
	public boolean isOld_Token() {
		return old_Token;
	}



	/**
	 * @param old_Token the old_Token to set
	 */
	public void setOld_Token(boolean old_Token) {
		this.old_Token = old_Token;
	}



	/**
	 * @param currentTimeTag the currentTimeTag to set
	 */
	public void setCurrentTimeTag(long currentTimeTag) {
		this.currentTimeTag = currentTimeTag;
	}



	/**
	 * @return the requestTag
	 */
	public int getRequestTag() {
		return requestTag;
	}



	/**
	 * @param requestTag the requestTag to set
	 */
	public void setRequestTag(int requestTag) {
		this.requestTag = requestTag;
	}



	/**
	 * @return the currentTimeTag
	 */
	public long getCurrentTimeTag() {
		return currentTimeTag;
	}



	/**
	 * @return the httpMethod
	 */
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the isEncrypt
	 */
	public boolean isEncrypt() {
		return isEncrypt;
	}

	/**
	 * @param isEncrypt the isEncrypt to set
	 */
	public void setEncrypt(boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the isMulit
	 */
	public boolean isMulit() {
		return isMulit;
	}

	/**
	 * @param isMulit the isMulit to set
	 */
	public void setMulit(boolean isMulit) {
		this.isMulit = isMulit;
	}
	
}
