/**
 * 
 */
package com.fph.appnavigationlib.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * com.wokee.rxbus.BeanFliperAdapter.java
 * 
 * Created by wang on 2016年6月16日下午4:53:18 
 * 
 * Tips:
 */
public abstract class BeanFliperAdapter <T>{

	
	public BeanFliperAdapter(Context context , int mRescoures){
		this.mRescoures =mRescoures ;
		this.mContext=context;
		
	}
	
	private Context mContext;
	
	private List<T> mValues;
	
	private int mRescoures;
	
	private View mView;
	
	public Map<Integer, InViewClickListener<T>> canClickItem;

	
	/**
	 * @return the mValues
	 */
	public List<T> getmValues() {
		return mValues;
	}

	/**
	 * @param mValues the mValues to set
	 */
	public void setmValues(List<T> mValues) {
		this.mValues = mValues;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return setInterval();
	}

	/**
	 * @param interval the interval to set
	 */
	public abstract int setInterval() ;

	public int getCount(){
		return mValues == null?0:mValues.size();
	}
	
	public List<View> getView(){
		List<View> views =new ArrayList<View>();
		if (getCount() >0) {
			for (int i = 0; i < mValues.size(); i++) {
				View view  =getView(i);
				views.add(view);
			}
		}
		return views;
	}
	
	
	@SuppressWarnings("unchecked")
	public View getView(int postion){
		ViewHolder viewHolder = null ;
//		if (mView == null ) 
		{
			mView = LayoutInflater.from(mContext).inflate(mRescoures, null);
			viewHolder= new ViewHolder(mView);
			mView.setTag(viewHolder);
		}
//		else{
//			viewHolder = (ViewHolder) mView.getTag();
//		}
		bindView(postion, mValues.get(postion), viewHolder.bindChildValueImp);
		bindInViewListener(mView, postion, mValues.get(postion));
		return mView;
	};
	
	public abstract void bindView(int postion , T value , BindChildValueImp bindChildValueImp);
	
	private void bindInViewListener(final View itemV, final Integer position,
									final T valuesMap) {
		if (canClickItem != null) {
			for (Integer key : canClickItem.keySet()) {
				View inView = itemV.findViewById(key);
				final InViewClickListener<T> inviewListener = canClickItem
						.get(key);
				if (inView != null && inviewListener != null) {
					inView.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							inviewListener.OnClickListener(itemV, v, position,
									valuesMap);
						}
					});
				}
			}
		}
	}
	
	
	
	@SuppressLint("UseSparseArrays")
	public void setOnInViewClickListener(InViewClickListener<T> inViewClickListener, Integer... key) {
		for (Integer k:key) {
			if (canClickItem == null)
				canClickItem = new HashMap<Integer, InViewClickListener<T>>();
			canClickItem.put(k, inViewClickListener);
		}
	}
	public class ViewHolder {
		
		BindChildValueImp bindChildValueImp;
		
		public ViewHolder(View view){
			bindChildValueImp =new BindChildValue(view);
		}
	}
	
	
}
