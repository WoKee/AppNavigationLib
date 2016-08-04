/**
 * 
 */
package com.fph.appnavigationlib.utils;

import java.lang.ref.SoftReference;

/**
 *
 * com.fph.SoftReferenceUtil.java
 * 
 * Created by wang on 2016年6月1日下午3:56:25 
 * 
 * Tips:
 */
public class SoftReferenceUtil<T> {
	private SoftReference<T> softReference;
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private SoftReferenceUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public SoftReferenceUtil(T t){
		softReference =new SoftReference<T>(t);
	}
	
	
	public T getWeakT(){
		return softReference.get();
	}
}
