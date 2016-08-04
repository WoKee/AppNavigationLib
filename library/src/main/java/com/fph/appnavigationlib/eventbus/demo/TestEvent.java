/**
 * 
 */
package com.fph.appnavigationlib.eventbus.demo;

/**
 *
 * com.fph.TestEvent.java
 * 
 * Created by wang on 20162016年5月24日上午10:16:15 
 * 
 * Tips:消息事件 不需要序列化
 */
public class TestEvent {
	
	private String id;
	
	private String msg;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	
	
}
