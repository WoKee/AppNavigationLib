/**
 * 
 */
package com.fph.appnavigationlib.bean;

import com.fph.appnavigationlib.utils.GsonUtil;

/**
 *
 * com.fph.RootConvert.java
 * 
 * Created by wang on 2016年7月13日上午10:27:26 
 * 
 * Tips:
 */
public class RootConvert {

	private String originResult;//原始数据

	/**
	 * @return the originResult
	 */
	public String getResult() {
		return originResult;
	}

	/**
	 * @param originResult the originResult to set
	 */
	public void setOriginResult(String originResult) {
		this.originResult = originResult;
	}
 
	public <V extends RootConvert> V convert(Class<V> class1) {
		V v = GsonUtil.fromJson(getResult(),  class1);
		if (v == null ) {
			try {
				v= class1.newInstance();
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return v;
		
	}
}
