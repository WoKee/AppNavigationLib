package com.fph.appnavigationlib.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.fph.appnavigationlib.utils.WeakReferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * com.fph.BeanPagerAdapter.java
 * 
 * Created by wang on 2016上午11:08:29 
 * 
 * Tips:用bean绑定的ViewPagerAdapter
 */

public abstract  class BeanPagerAdapter<T> extends PagerAdapter {

	public List<T> mVaules = null;

	private final Object mLock = new Object();

	protected int mResource;

	protected boolean mNotifyOnChange = true;

	public LayoutInflater mInflater;


	public Map<Integer, InViewClickListener<T>> canClickItem;

	public boolean isReuse = true;



	public Class<?> jumpClazz;
	public String jumpKey;
	public String jumpAs;
	public Class<?> getJumpClazz() {
		return jumpClazz;
	}

	public String getJumpKey() {
		return jumpKey;
	}

	public String getJumpAs() {
		return jumpAs;
	}

	public void setJump(Class<?> jumpClazz, String jumpkey, String as) {
		this.jumpClazz = jumpClazz;
		this.jumpKey=jumpkey;
		this.jumpAs=as;
	}

	public BeanPagerAdapter(Context context, int mResource) {
		super();
		this.mResource = mResource;
		mInflater = LayoutInflater.from(new WeakReferenceUtil<Context>(context).getWeakT());
		mVaules = new ArrayList<T>();
	}


	@SuppressWarnings("unchecked")
	public <T> List<T> getValues() {
		return (List<T>) mVaules;
	}

	public void add(T one) {
		synchronized (mLock) {
			mVaules.add(one);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void addAll(List<T> ones) {
		synchronized (mLock) {
			mVaules.addAll(ones);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void insert(int index, T one) {
		synchronized (mLock) {
			mVaules.add(index, one);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void remove(int index) {
		synchronized (mLock) {
			mVaules.remove(index);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void clear() {
		synchronized (mLock) {
			mVaules.clear();
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}


	public void setNotifyOnChange(boolean notifyOnChange) {
		mNotifyOnChange = notifyOnChange;
	}
	public void notifyItemOnIndex(int index,T one){
		setNotifyOnChange(false);
		remove(index);
		setNotifyOnChange(true);
		insert(index, one);
	};
	public int getCount() {
		return mVaules.size();
	}

	public Object getItem(int position) {
		return mVaules.get(position);
	}


	public String getTItemId(int position) {

		return position+"";
	}

	View view;
	@Override
	public Object instantiateItem(View collection, int position) {

		view = mInflater.inflate(mResource, null, false);
		bindView(view, position, mVaules.get(position),new BindChildValue(view));
		bindInViewListener(view, position, mVaules.get(position));
		((ViewPager) collection).addView(view);
		return view;
	}

	public abstract void bindView(View itemV, int position, T value, BindChildValueImp bind);

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
	 /**
     * @deprecated  {@link #setOnInViewClickListener(InViewClickListener <T> inViewClickListener, Integer... key) }
     */
    @Deprecated
	public void setOnInViewClickListener(Integer key,
			InViewClickListener<T> inViewClickListener) {
		setOnInViewClickListener(inViewClickListener, key);
	}

    @SuppressLint("UseSparseArrays")
	public void setOnInViewClickListener(
			InViewClickListener<T> inViewClickListener, Integer... key) {
		if (canClickItem == null)
			canClickItem = new HashMap<Integer, InViewClickListener<T>>();
		for (Integer integer : key) {
			canClickItem.put(integer, inViewClickListener);
		}
	}
	
	/**
	 *
	 * viewholder
	 *
	 */
	public class ViewHolder {
		Map<Integer, View> views;

		public ViewHolder() {
			super();
			views = new HashMap<Integer, View>();
		}

		public void put(Integer id, View v) {
			views.put(id, v);
		}

		public View get(Integer id) {
			return views.get(id);
		}

	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		((ViewPager) collection).removeView((View) view);
		//			super.destroyItem(collection, position, view);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == (View)arg1;
	}

}
