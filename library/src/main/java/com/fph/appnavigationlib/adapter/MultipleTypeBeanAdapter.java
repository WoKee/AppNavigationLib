/**
 * 
 */
package com.fph.appnavigationlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fph.appnavigationlib.bean.MultipleTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * com.fph.MultipleTypeBeanAdapter.java
 * 
 * Created by wang on 2016年6月12日下午3:54:55 
 * 
 * Tips:
 */
public abstract class MultipleTypeBeanAdapter<T extends MultipleTypeBean> extends BeanAdapter<T> {

	
	private List<Integer> mResources =new ArrayList<Integer>();
	
	/**
	 * @param context
	 * @param mResource
	 */
	public MultipleTypeBeanAdapter(Context context, int mResource) {
		super(context, mResource);
		// TODO Auto-generated constructor stub
		mResources.add(mResource);
	}

	/**
	 * @param context
	 * @param mResource
	 * Tips:  重写是一定要为该形式的参数  Integer... mResource  不能为Integer[] mResource
	 */
	public MultipleTypeBeanAdapter(Context context, Integer... mResource) {
		super(context, mResource[0]);//只是兼容，不做任何处理
		// TODO Auto-generated constructor stub
		mResources.clear();
		for (int i : mResource) {
			mResources.add(i);
		}
	}
	
	/**
	 * 根据布局得到对应的type
	 * @param mResources
	 * @return
	 */
	public int getTypeWithResources(Object mResources){
		if (getmResources().contains(mResources)) {
			return getmResources().indexOf(mResources);
		}
		return -1;
	}
	
	/**
	 * @return the mResources
	 */
	public List<Integer> getmResources() {
		return mResources;
	}

	/**
	 * @param mResources the mResources to set
	 */
	public void setmResources(List<Integer> mResources) {
		this.mResources = mResources;
	}

	/* (non-Javadoc)
	 * @see com.fph.BeanAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView,mResources.get(getItemViewType(position)), parent);
	}
	
	
	/* (non-Javadoc)
	 * @see com.fph.BeanAdapter#bindView(android.view.View, int, java.lang.Object, com.fph.BindChildValueImp)
	 */
	@Override
	public void bindView(View itemV, int position, T value, BindChildValueImp bind) {
		// TODO Auto-generated method stub
		bindView(itemV, position, value.getValue(), value.getType(), bind);
	}
	public abstract void bindView(View itemV, int position, Object value, int type, BindChildValueImp bind);
	
	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#getItemViewType(int)
	 */
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return mVaules.get(position).getType();
	}
	
	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#getViewTypeCount()
	 */
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return mResources==null?0:mResources.size();
	}
	

}
