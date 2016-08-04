/**
 * 
 */
package com.fph.appnavigationlib.listener;


import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ScrollView;

import com.fph.appnavigationlib.download.DownLoadCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.Call;

/**
 *
 * com.fph.ObjectListener.java
 * 
 * Created by wang on 2016年6月12日下午3:37:23
 * 
 * Tips:
 */
public class ObjectListener implements ObjectListenerImp, OnScrollListener,DownLoadCallBack {

	public ObjectListener(){}
	
	/**
	 * AbsListView Scroll With ImageLoader
	 */
	
	private boolean pauseOnScroll;//触摸滚动时 是否加载图片
	
	private boolean pauseOnFling;//惯性滚动时 是否加载图片
	
	public ObjectListener(boolean pauseOnScroll, boolean pauseOnFling){
		this.pauseOnFling = pauseOnFling;
		this.pauseOnScroll =pauseOnScroll;
	}
	// ======================  end =============
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.ObjectListenerImp#onScrollChanged(
	 * android.widget.ScrollView, int, int, int, int)
	 */
	@Override
	public void onScrollChanged(ScrollView scrollView, int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fph.ObjectListenerImp#onGlobalLayoutEnd(
	 * boolean)
	 */
	@Override
	public void onGlobalLayoutEnd(boolean isEnd) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android.
	 * widget.AbsListView, int)
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			if (pauseOnFling  ||  pauseOnScroll) {
				ImageLoader.getInstance().resume();
			}
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			if (pauseOnScroll) {
				ImageLoader.getInstance().pause();
			}
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			if (pauseOnFling) {
				ImageLoader.getInstance().pause();
			}
			break;
	}
		

	}

	
	private boolean isAbsListViewZero;//onScroll前置条件
	private Map<Integer, Integer> heightMap= new ArrayMap<Integer, Integer>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.
	 * AbsListView, int, int, int)
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
//		View c = view.getChildAt(0);
//		if (c == null) {
//			onSrollChanged(view, firstVisibleItem, visibleItemCount, totalItemCount, -1);
//			return;
//		}
//		if (view instanceof ListView ) {
//			
//			View view2 =((ListView) view).addHeaderView(v);
//			
//		}
		
		View v= view.getChildAt(0);//当前屏幕第一条view
    	if (v== null ) {
			onSrollChanged(view, firstVisibleItem, visibleItemCount, totalItemCount, -1);
			return;
		}
		
		if (firstVisibleItem == 0) {
			heightMap.clear();
		}
		heightMap.put(firstVisibleItem, 0);
		int height = 0;
		Iterator<Entry<Integer, Integer>> iterator = heightMap.entrySet().iterator();
        while(iterator.hasNext()){  
            Entry<Integer, Integer> entry = (Entry<Integer, Integer>)iterator.next();
            if (entry.getKey()==firstVisibleItem) {
            	break;
			}
            height+=entry.getValue();
//            KLog.e(entry.getValue()+"----"+entry.getKey()+"----"+height+"---"+firstVisibleItem);
		}
		heightMap.put(firstVisibleItem, v.getHeight());
		
		
		
//	
//		for (int i = 0; i <= firstVisibleItem; i++) {
//			View v=view.getChildAt(i);
//			if (v== null ) {
//				onSrollChanged(view, firstVisibleItem, visibleItemCount, totalItemCount, -1);
//				return;
//			}
//			if (i== firstVisibleItem) {
//				height -=v.getTop();
//				break ;
//			}
//			height+=v.getHeight();
//		}
		
//		int firstVisiblePosition = view.getFirstVisiblePosition();
//		int top = c.getTop();
//		
//		int height=-top + firstVisiblePosition * c.getHeight();
		
		if (!isAbsListViewZero) {
			onSrollChanged(view, firstVisibleItem, visibleItemCount, totalItemCount,
					height-v.getTop());
		}
		if (height-v.getTop()==0) {
			isAbsListViewZero=true;
		}else{
			isAbsListViewZero=false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.ObjectListenerImp#onSrollChanged(
	 * android.widget.AbsListView, int, int, int, int)
	 */
	@Override
	public void onSrollChanged(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount,
							   int scrollY) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.fph.ObjectListenerImp#onSoftKeyBoardChange(int, boolean)
	 */
	@Override
	public void onSoftKeyBoardChange(int softKeybardHeight, boolean visible) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadStateChangeListener#onResume(java.lang.Object)
	 */
	@Override
	public void onResume(Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadStateChangeListener#onPause(java.lang.Object)
	 */
	@Override
	public void onPause(Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadStateChangeListener#onCancel(java.lang.Object)
	 */
	@Override
	public void onCancel(Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadCallBack#onPrepare(java.lang.Object)
	 */
	@Override
	public void onPrepare(Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadCallBack#onSuccess(java.lang.Object, java.io.File)
	 */
	@Override
	public void onSuccess(Object tag, File file) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadCallBack#onStart(java.lang.Object)
	 */
	@Override
	public void onStart(Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadCallBack#onError(java.lang.Object, java.lang.String, int)
	 */
	@Override
	public void onError(Object tag, String mString, int errorCode) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.DownLoadCallBack#onDownLoadProgress(double, double, java.lang.Object)
	 */
	@Override
	public void onDownLoadProgress(double completeSize, double totalSize, Object tag) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.ObjectListenerImp#onDownLoadError(okhttp3.Call, java.lang.Exception, int)
	 */
	@Override
	public void onDownLoadError(Call arg0, Exception arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.ObjectListenerImp#onDownLoadResponse(java.io.File, int)
	 */
	@Override
	public void onDownLoadResponse(File arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.fph.ObjectListenerImp#onDownLoadProgress(float, long, int)
	 */
	@Override
	public void onDownLoadProgress(float progress, long total, int id) {
		// TODO Auto-generated method stub
		
	}





}
