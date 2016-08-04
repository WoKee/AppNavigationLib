/**
 * 
 */
package com.fph.appnavigationlib.download;

import android.os.Environment;

/**
 *
 * com.fph.DownLoadCallConfig.java
 * 
 * Created by wang on 2016年7月1日下午3:09:20 
 * 
 * Tips:
 */
public class DownLoadCallConfig {

	private DownLoadCallConfig(){};
	
	
	private final static class Holder {
		private final static DownLoadCallConfig INSTANCE= new DownLoadCallConfig();
	}
	
	public static DownLoadCallConfig getInstance(){
		return Holder.INSTANCE;
	}
	
	private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
	
	private int poplSize = 3;
	
	private Class<? extends DownLoadBuilderFactory> builderFactory ;
	
	private long updateCurrTime = 1000;
	
	private Class<? extends ORMFactory> ormFactory;
	
	
	
	
	

	

	/**
	 * @return the ormFactory
	 */
	public Class<? extends ORMFactory> getOrmFactory() {
		return ormFactory;
	}

	/**
	 * @param ormFactory the ormFactory to set
	 */
	public DownLoadCallConfig setOrmFactory(Class<? extends ORMFactory> ormFactory) {
		this.ormFactory = ormFactory;
		return this;
	}

	/**
	 * @return the updateCurrTime
	 */
	public long getUpdateCurrTime() {
		return updateCurrTime;
	}

	/**
	 * @param updateCurrTime the updateCurrTime to set
	 */
	public DownLoadCallConfig setUpdateCurrTime(long updateCurrTime) {
		this.updateCurrTime = updateCurrTime;
		return this;
	}

	/**
	 * @return the builderFactory
	 */
	public Class<? extends DownLoadBuilderFactory> getBuilderFactory() {
		return builderFactory;
	}

	/**
	 * @param builderFactory the builderFactory to set
	 */
	public DownLoadCallConfig setBuilderFactory(Class<? extends DownLoadBuilderFactory> builderFactory) {
		this.builderFactory = builderFactory;
		return this;
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
	public DownLoadCallConfig setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	/**
	 * @return the poplSize
	 */
	public int getPoplSize() {
		return poplSize;
	}

	/**
	 * @param poplSize the poplSize to set
	 */
	public DownLoadCallConfig setPoplSize(int poplSize) {
		this.poplSize = poplSize;
		return this;
	}
	
	
	
}
