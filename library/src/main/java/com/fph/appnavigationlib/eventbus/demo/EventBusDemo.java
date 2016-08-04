/**
 * 
 */
package com.fph.appnavigationlib.eventbus.demo;

import android.os.Message;

import com.fph.appnavigationlib.base.BaseActivity;
import com.fph.appnavigationlib.eventbus.EventType;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

/**
 *
 * com.fph.EventBusDemo.java
 * 
 * Created by wang on 20162016年5月24日上午10:09:22 
 * 
 * Tips:
 */
public class EventBusDemo extends BaseActivity{

	
	TestEvent testEvent;//消息事件
	
	
	/* (non-Javadoc)
	 * @see com.fph.BaseActivity#initView()
	 */
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		/**
		 * 二选一
		 */
		initBus();//如果需要注册全局接收器  ,只能注册一个
		initBus(testEvent);//如果需要注册单个事件接收器 ,可注册多个
//		initBus(testEvent);//如果需要注册单个事件接收器 ,可注册多个
//		initBus(testEvent);//如果需要注册单个事件接收器 ,可注册多个
	}
	
	/**
	 * 事件接收器方法  ，需要先注册全局接收器initBus();或者注册自定义事件接收器initBus(testEvent);
	 * caught(TestEvent testEvent) 方法名自定义
	 * @param thread 常用有三中
	 * EventThread.MAIN_THREAD  通知方法在主线程中回调（常用，普通事件传递）
	 * EventThread.NEW_THREAD  通知方法在新线程中回调,防止回调处理事物 卡UI
	 * EventThread.IO  通知方法在新线程中回调,通常用于处理文件读取 存储等耗时操作
	 * @param mouse 参数，自定义事件参数
	 */
	@Subscribe(thread = EventThread.MAIN_THREAD, tags = { @Tag(EventType.TEST_TYPE) })
	public void caught(TestEvent testEvent) {
		Log(testEvent.getMsg());
	}
	
	/* (non-Javadoc)
	 * @see com.fph.BaseActivity#initEvent()
	 */
	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.fph.BaseActivity#handlerMessage(android.os.Message)
	 */
	@Override
	public void handlerMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see com.fph.BaseActivity#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
