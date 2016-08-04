/**
 * 
 */
package com.fph.appnavigationlib.bean;

import java.io.File;
import java.util.Map;

/**
 *
 * com.fph.UploadFileForm.java
 * 
 * Created by wang on 2016年7月13日下午2:55:16 
 * 
 * Tips:文件上传bean
 */
public class UploadFileForm {

	
	private String key;
	
	private Map<String , File> file;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the file
	 */
	public Map<String, File> getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(Map<String, File> file) {
		this.file = file;
	}
	
	
	
	
}
