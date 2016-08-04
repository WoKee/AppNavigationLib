/**
 * 
 */
package com.fph.appnavigationlib.utils;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 *
 * com.example.libdemo.CountDownObservable.java
 * 
 * Created by wang on 2016下午1:06:27 
 * 
 * Tips: 倒计时类 
 */
public class CountDownObservable /*extends Observable<Long> */{

	
	private int time;//总时间
	
	private boolean isCountDownTime;//是否需要倒序记时间
	
	private TimeUnit timeUnit;//间隔频率
	
	private Observable<Long> observable;
	
	private CountDownListener countDownListener;//监听
	
	private Subscription subscription;
	
	private int currTime;//剩余时间
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private CountDownObservable() {
		// TODO Auto-generated constructor stub
	}

	public CountDownObservable(int time, TimeUnit timeUnit, Boolean isCountDownTime, CountDownListener countDownListener){
//		super(f);
		this.time=time;
		this.isCountDownTime=isCountDownTime;
		this.timeUnit=timeUnit;
		this.countDownListener=countDownListener;
	}
	
	public CountDownObservable(int time,boolean isCountDownTime,CountDownListener countDownListener){
//		super(f);
		this(time, TimeUnit.SECONDS,isCountDownTime,countDownListener);
	}
	public CountDownObservable(int time,CountDownListener countDownListener){
//		super(f);
		this(time, TimeUnit.SECONDS,false,countDownListener);
	}
	public void initObservable(int time){
		currTime=time;
		observable= Utils.countDownTimer(time, timeUnit,isCountDownTime);
	}
	
	public void setLog(boolean isLog){
		if (countDownListener!=null&& countDownListener instanceof CountDownListenerImp) {
			((CountDownListenerImp)countDownListener).setLog(isLog);
		}else{
			KLog.e("countDownListener  is null or is not instanceof CountDownListenerImp");
		}
	}
	
	/**
	 * 获取时间
	 * @return
	 */
	
	public int getTime(){
		return time;
	}
	/**
	 * 获取剩余时间
	 * @return
	 */
	public int getCurrTime(){
		return currTime;
	}
	
	/**
	 * 开始||重新开始
	 */
	public void start(){
		initObservable(time);
		if (observable==null||countDownListener==null) {
			return;
		}
		subscription =observable.subscribe(new Subscriber<Long>() {

			/* (non-Javadoc)
			 * @see rx.Subscriber#onStart()
			 */
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				countDownListener.onStart();
			}
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				countDownListener.onCompleted();
				if (!this.isUnsubscribed()) {
					this.unsubscribe();
				}
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				countDownListener.onError(arg0);
				if (!this.isUnsubscribed()) {
					this.unsubscribe();
				}
			}

			@Override
			public void onNext(Long arg0) {
				// TODO Auto-generated method stub
				countDownListener.onNext(arg0);
				currTime--;
			}
		});
		
	}
	/**
	 * 继续倒计时
	 */
	public void reStart(){
		if (currTime<=0) {
			start();
			return;
		}
		initObservable(currTime);
		
		if (observable==null||countDownListener==null) {
			return;
		}
		
		final int cTime=time-currTime;
		subscription =observable.subscribe(new Subscriber<Long>() {

			/* (non-Javadoc)
			 * @see rx.Subscriber#onStart()
			 */
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				countDownListener.onReStart();
			}
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				countDownListener.onCompleted();
				currTime=0;
				if (!this.isUnsubscribed()) {
					this.unsubscribe();
				}
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				countDownListener.onError(arg0);
				if (!this.isUnsubscribed()) {
					this.unsubscribe();
				}
			}

			@Override
			public void onNext(Long arg0) {
				// TODO Auto-generated method stub
				countDownListener.onNext(arg0+cTime);
				currTime--;
			}
		});
		

		
	}
	
	/**
	 * 暂停||停止
	 */
	public void stop(){
		if (observable==null||countDownListener==null||subscription==null) {
			return;
		}
		if (!subscription.isUnsubscribed()) {
			subscription.unsubscribe();
			countDownListener.onStop();
		}
		observable=null;
	}


	/**
	 * 是否在运行
	 * @return the isStart
	 */
	public boolean isRun() {
		return subscription==null?false:!subscription.isUnsubscribed();
	}


	/**
	 * 监听事件
	 *
	 * com.example.libdemo.CountDownObservable.java
	 * 
	 * Created by wang on 2016下午2:35:43 
	 * 
	 * Tips:
	 */
	public static interface CountDownListener{
		
		 void onCompleted();//完成倒计时

		 void onError(Throwable arg0) ;//错误

		 void onNext(Long arg0) ;//倒计时

		 void onStart() ;//开始
		 
		 void onReStart();//继续
		
		void onStop();//手动停止||暂停
	}
	
	
	public static class CountDownListenerImp implements CountDownListener{

		//倒计时输出日志开关
		private boolean isLog=false;
		
		
		/**
		 * @return the isLog
		 */
		public boolean isLog() {
			return isLog;
		}

		/**
		 * @param isLog the isLog to set
		 */
		public void setLog(boolean isLog) {
			this.isLog = isLog;
		}

		private void Log(Object object){
			if (isLog()) {
				KLog.e(object);
			}
		}
		
		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onCompleted()
		 */
		@Override
		public void onCompleted() {
			// TODO Auto-generated method stub
			Log("onCompleted()");
		}

		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onError(java.lang.Throwable)
		 */
		@Override
		public void onError(Throwable arg0) {
			// TODO Auto-generated method stub
			Log("onError(Throwable arg0)"+"----"+arg0.getMessage());
		}
		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onNext(java.lang.Long)
		 */
		@Override
		public void onNext(Long arg0) {
			// TODO Auto-generated method stub
			Log("onNext(Long arg0)"+"----"+arg0);

		}

		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onStart()
		 */
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			Log("onStart()");
		}

		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onReStart()
		 */
		@Override
		public void onReStart() {
			// TODO Auto-generated method stub
			Log("onReStart()");

		}

		/* (non-Javadoc)
		 * @see com.fph.CountDownObservable.CountDownListener#onStop()
		 */
		@Override
		public void onStop() {
			// TODO Auto-generated method stub

		}
		
	}
	
}
