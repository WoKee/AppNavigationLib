/**
 * 
 */
package com.fph.appnavigationlib.download;

/**
 *
 * com.fph.DownLoadEntiy.java
 * 
 * Created by wang on 2016年7月1日下午3:17:54 
 * 
 * Tips:下载信息
 */
public class DownLoadEntiy {

	private boolean isDownLoading;
	
	private String filePath;
	
	private String fileName;
	
	private Object tag;
	
	private long totalSize;
	
	private long completeSize;

	private String url;
	
	private DownLoadState downLoadState;
	
	
	
	/**
	 * @return the downLoadState
	 */
	public DownLoadState getDownLoadState() {
		return downLoadState;
	}

	/**
	 * @param downLoadState the downLoadState to set
	 */
	public void setDownLoadState(DownLoadState downLoadState) {
		this.downLoadState = downLoadState;
	}

	/**
	 * @return the totalSize
	 */
	public long getTotalSize() {
		return totalSize;
	}

	/**
	 * @param totalSize the totalSize to set
	 */
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * @return the completeSize
	 */
	public long getCompleteSize() {
		return completeSize;
	}

	/**
	 * @param completeSize the completeSize to set
	 */
	public void setCompleteSize(long completeSize) {
		this.completeSize = completeSize;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the isDownLoading
	 */
	public boolean isDownLoading() {
		return isDownLoading;
	}

	/**
	 * @param isDownLoading the isDownLoading to set
	 */
	public void setDownLoading(boolean isDownLoading) {
		this.isDownLoading = isDownLoading;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the tag
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	
	
	
	
}
