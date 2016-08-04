package com.fph.appnavigationlib.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

import com.fph.appnavigationlib.adapter.BindChildValue;
import com.fph.appnavigationlib.adapter.BindChildValueImp;
import com.fph.appnavigationlib.eventbus.BusProvider;
import com.fph.appnavigationlib.utils.ActivityTask;
import com.fph.appnavigationlib.utils.Utils;
import com.fph.appnavigationlib.utils.WeakReferenceUtil;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 *
 * com.fph.BaseActivity.java
 * 
 * Created by wang on 2016下午4:41:58 
 * 
 * 
 * Tips:baseActivity 
 */
public abstract class BaseActivity extends FragmentActivity {

	// TODO 
	public  BaseHandler<BaseActivity> handler=new BaseHandler<BaseActivity>(this);
	// TODO
	public DisplayMetrics dm=new DisplayMetrics();
	// TODO
	private List<Object> eventList=new ArrayList<Object>();
	
	private BindChildValueImp bind;
	
	
	/* (non-Javadoc)
	 * @see android.support.v7.app.AppCompatActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		//
		ButterKnife.bind(this);
		//
		ActivityTask.getInstanse().addActivity(this);
		//
		
		setBind(new BindChildValue(getWindow().getDecorView()));
		
		//
		getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		initView();
		
		initEvent();
	}
	
	
	/**
	 * @return the bind
	 */
	public BindChildValueImp getBind() {
		return bind;
	}


	/**
	 * @param bind the bind to set
	 */
	public void setBind(BindChildValueImp bind) {
		this.bind = bind;
	}


	/**
	 * 全局事件通知注册
	 * Tips: 如果只是发送事件通知 ，不需要执行此方法
	 */
	public void initBus(){
		initBus(getWeakContext());
	}
	
	/**
	 * 单个事件通知注册
	 * Tips: 如果只是发送事件通知 ，不需要执行此方法
	 * @param obj 可以注册为你想要接收的event事件类型
	 */
	public void initBus(Object obj){
		BusProvider.getInstance().register(obj);
		if (!eventList.contains(obj)) {
			eventList.add(obj);
		}
	}
	/**
	 * 发送事件通知
	 *	postEvent(Constants.EventType.TAG_STORY, mouseMam.birth());
	 * @param tag
	 * @param obj
	 */
	public void postEvent(String tag, Object obj){
		try {
			BusProvider.getInstance().post(tag, obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
//	/**
//	 * 事件接收器方法  ，需要先注册全局接收器initBus();或者注册自定义事件接收器initBus(testEvent);
//	 * caught(TestEvent testEvent) 方法名自定义
//	 * @param thread 常用有三中
//	 * EventThread.MAIN_THREAD  通知方法在主线程中回调（常用，普通事件传递）
//	 * EventThread.NEW_THREAD  通知方法在新线程中回调,防止回调处理事物 卡UI
//	 * EventThread.IO  通知方法在新线程中回调,通常用于处理文件读取 存储等耗时操作
//	 * @param mouse 参数，自定义事件参数
//	 */
//	@Subscribe(thread = EventThread.IO, tags = { @Tag(EventType.TEST_TYPE) })
//	public void caught(TestEvent testEvent) {
//		Utils.Log(testEvent.getMsg());
//	}
	
	/**
	 * context弱引用 用于外部引用控件
	 * @return
	 */
	protected Context getWeakContext(){
		return new WeakReferenceUtil<BaseActivity>(this).getWeakT();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		EventBus.getDefault().register(this);

	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
//		EventBus.getDefault().unregister(this);
		super.onStop();
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (handler!=null) {
			handler.removeCallbacksAndMessages(null);
			handler=null;
		}
		for (Object object :eventList) {
			try {
				BusProvider.getInstance().unregister(object);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		ActivityTask.getInstanse().removeActivity(this);
		System.gc();
		super.onDestroy();
	}
	
	/**
	 * 输出log日志
	 * @param object
	 */
	public static void Log(Object object){
		KLog.e(object);
	}
	
	/**
	 * 
	 * @param string
	 */
	public void showToast(String string){
		Utils.showToast(string);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
	 */
	protected void onSaveInstanceState(Bundle outState) {
		System.gc();
		super.onSaveInstanceState(outState);
	};
	
	/**
	 * setContentView(layoutResID);
	 */
	public abstract void init();
	
	/**
	 * 设置视图内容
	 */
	public abstract void initView();
	
	 
	/*
	 * 注册事件
	 */
	public abstract void initEvent();
	
	
	
	/*
	 * handler回调事件		
	 */
	public abstract void handlerMessage(Message msg);
	
 
 
	
	
}
