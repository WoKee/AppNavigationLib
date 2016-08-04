/**
 * 
 */
package com.fph.appnavigationlib.download;

import java.util.List;

/**
 *
 * com.fph.ORMFactory.java
 * 
 * Created by wang on 2016年7月4日下午4:47:17 
 * 
 * Tips:数据库工厂接口
 */
public interface ORMFactory {

	/**
	 * 创建下载实例
	 * @param downLoadEntiy
	 */
	void create(DownLoadEntiy downLoadEntiy);
	
	/**
	 * 删除下载实例
	 * @param downLoadEntiy
	 */
	void delete(DownLoadEntiy downLoadEntiy);
	/**
	 * 更新数据库
	 * @param downLoadEntiy
	 */
	void update(DownLoadEntiy downLoadEntiy);
	
	/**
	 * 查询数据库
	 * @param tag
	 */
	void search(Object tag);
	
	/**
	 * 获取下载列表
	 * @param isDownloaded 是否包含已下载完成
	 * @return
	 */
	List<DownLoadEntiy> getDownLoadEntiys(boolean isDownloaded);
	/**
	 * 获取指定tag下载信息
	 * @param tag
	 * @return
	 */
	DownLoadEntiy getDownLoadEntiyWithTag(Object tag);
}
