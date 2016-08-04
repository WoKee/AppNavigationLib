/**
 * 
 */
package com.fph.appnavigationlib.download;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * com.fph.DownLoadResponse.java
 * 
 * Created by wang on 2016年7月1日下午4:52:43 
 * 
 * Tips:
 */
public class DownLoadResponse {

	
	private long contentLenth;
	
	private InputStream inputStream ;
	
	private String errorMsg;
	
	private int errorCode ;
	
	private Map<String, List<String>> headerMap;
	
	
	

	/**
	 * @return the contentLenth
	 */
	public long getContentLenth() {
		return contentLenth;
	}

	/**
	 * @param contentLenth the contentLenth to set
	 */
	public void setContentLenth(long contentLenth) {
		this.contentLenth = contentLenth;
	}

	/**
	 * @return the headerMap
	 */
	public Map<String, List<String>> getHeaderMap() {
		return headerMap;
	}

	/**
	 * @param headerMap the headerMap to set
	 */
	public void setHeaderMap(Map<String, List<String>> headerMap) {
		this.headerMap = headerMap;
	}


	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
	
	
	
}
