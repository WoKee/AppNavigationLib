/**
 * 
 */
package com.fph.appnavigationlib.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fph.appnavigationlib.adapter.BindChildValue;
import com.fph.appnavigationlib.adapter.BindChildValueImp;
import com.fph.appnavigationlib.eventbus.BusProvider;
import com.fph.appnavigationlib.utils.WeakReferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 *
 * com.fph.BaseFragment.java
 * 
 * Created by wang on 2016下午5:51:03
 * 
 * Tips:
 */
public abstract class BaseFragment extends Fragment {

	private int layoutId;

	protected BaseHandler<BaseFragment> handler = new BaseHandler<BaseFragment>(this);

	private View fView;

	private BindChildValueImp bind;
	
	private List<Object> eventList =new ArrayList<>();

	/**
	 * @return the bind
	 */
	public BindChildValueImp getBind() {
		return bind;
	}

	/**
	 * @param bind
	 *            the bind to set
	 */
	public void setBind(BindChildValueImp bind) {
		this.bind = bind;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();
		fView = inflater.inflate(layoutId, container, false);
		setBind(new BindChildValue(fView));
		ButterKnife.bind(this, fView);
		initView();
		return fView;
	}
	/**
	 * 全局事件通知注册
	 * Tips: 如果只是发送事件通知 ，不需要执行此方法
	 */
	public void initBus(){
		initBus(this);
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
	
	/*
	 * 处理事件冲突 (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 * android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		this.onViewCreated(view, savedInstanceState, false);
	}
	/**
	 * 
	 * @param view
	 * @param savedInstanceState
	 * @param isTouched 底层view是否支持事件
	 */
	public void onViewCreated(View view, Bundle savedInstanceState, boolean isTouched) {
		if (!isTouched) {
//			view.setOnTouchListener(new View.OnTouchListener() {
//				@SuppressLint("ClickableViewAccessibility")
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					return true;
//				}
//			});
		}
		super.onViewCreated(view, savedInstanceState);
		initEvent();
	}
	/**
	 * Context弱引用 用于外部引用
	 * 
	 * @return
	 */
	protected Context getWeakActivity() {
		return new WeakReferenceUtil<FragmentActivity>(getActivity()).getWeakT();
	}

	/*
	 * 初始化操作 主要用于setLayout
	 */
	protected abstract void init();

	/**
	 * 初始化用于初始化界面
	 */
	protected abstract void initView();

	/**
	 * 初始化用于 事件注册
	 */
	protected abstract void initEvent();

	/**
	 * 简化findViewById
	 * 
	 * @param id
	 * @return
	 */
	protected View findViewById(int id) {
		if (getView() != null) {
			return getView().findViewById(id);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#getView()
	 */
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return super.getView() == null ? fView : super.getView();
	}

	/*
	 * 设置layout
	 */
	protected void setLayout(int layout) {
		this.layoutId = layout;
	}

	public abstract void handlerMessage(Message msg);

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
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
		ButterKnife.unbind(this);
		fView = null;
		System.gc();
		super.onDestroyView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		System.gc();
		super.onSaveInstanceState(outState);

	}
}
