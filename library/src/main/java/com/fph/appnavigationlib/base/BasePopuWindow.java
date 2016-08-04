/**
 * 
 */
package com.fph.appnavigationlib.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.fph.appnavigationlib.adapter.BindChildValue;
import com.fph.appnavigationlib.adapter.BindChildValueImp;

import butterknife.ButterKnife;

/**
 *
 * com.fph.BasePopuWindow.java
 * 
 * Created by wang on 2016下午3:48:09 
 * 
 * Tips:
 */
public abstract class BasePopuWindow extends PopupWindow {

	private BindChildValueImp bind;
	
	private Bundle bundle ;//参数传递
	
	
	
	/**
	 * @return the bundle
	 */
	public Bundle getBundle() {
		return bundle;
	}


	/**
	 * @param bundle the bundle to set
	 */
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
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

	public BasePopuWindow( ) {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public BasePopuWindow(BaseActivity activity) {
		// TODO Auto-generated constructor stub
		this(activity, null);
	}
	
	public BasePopuWindow(BaseActivity activity, Bundle bundle) {
		// TODO Auto-generated constructor stub
		super(activity);
		this.bundle=bundle;
		View contentView = LayoutInflater.from(activity).inflate(setLayoutId(), null);
		setBind(new BindChildValue(contentView));
		ButterKnife.bind(this,contentView);
		setContentView(contentView);
		initPop(getBind());
	}
	
	
	public BasePopuWindow showAsDropDownWithOther(View anchor) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor);
		return this;
	}
	
	public BasePopuWindow showAsDropDownWithOther(View anchor, int xoff, int yoff) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, xoff, yoff);
		return this;
	}
	
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public BasePopuWindow showAsDropDownWithOther(View anchor, int xoff, int yoff, int gravity) {
		// TODO Auto-generated method stub
		super.showAsDropDown(anchor, xoff, yoff, gravity);
		return this;
	}
	
	public BasePopuWindow showAtLocationWithOther(View parent, int gravity, int x, int y) {
		// TODO Auto-generated method stub
		super.showAtLocation(parent, gravity, x, y);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.PopupWindow#dismiss()
	 */
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Finish();
	}
	/**
	 * 手动释放
	 */
	protected void  Finish() {
		ButterKnife.unbind(this);
	}

	/**
	 * 返回layout
	 * @return
	 */
	protected abstract int setLayoutId();
	
	/**
	 * pop设置
	 * @param activity
	 * @param view
	 */
	protected abstract void initPop(BindChildValueImp bind );
	
	
	
}
