/**
 * 
 */
package com.fph.appnavigationlib.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.fph.appnavigationlib.adapter.BindChildValue;
import com.fph.appnavigationlib.adapter.BindChildValueImp;

import butterknife.ButterKnife;

/**
 *
 * com.fph.BaseDialogFragment.java
 * 
 * Created by wang on 2016下午4:27:19
 * 
 * Tips:
 */
public abstract class BaseDialogFragment extends DialogFragment {

	
	
	private BindChildValueImp bind;
	
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
	 * 参数传递
	 * 
	 * @param bundle
	 * @return
	 */
	public static BaseDialogFragment getInstance(Bundle bundle) {
		return null;
	};
	// {示例
	// {
	// // TODO Auto-generated method stub
	// BaseDialogFragment testDialog=new BaseDialogFragment();
	// testDialog.setArguments(bundle);
	// return testDialog;
	// }
	// }

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
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		View view = inflater.inflate(initLayoutId(), null);
		setBind(new BindChildValue(view));
		ButterKnife.bind(this, view);
		initView(getBind());
		return view;
	}

	/**
	 * 返回布局的ID
	 * 
	 * @return
	 */
	public abstract int initLayoutId();

	public abstract void initView(BindChildValueImp bind);

	/**
	 * 设置是否点击外部取消
	 * 
	 * @param enable
	 */
	public void setOutSideCancelable(boolean enable) {
		if (getDialog() == null) {
			return;
		}
		getDialog().setCanceledOnTouchOutside(enable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.DialogFragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		ButterKnife.unbind(this);
		super.onDestroyView();
	}

}
