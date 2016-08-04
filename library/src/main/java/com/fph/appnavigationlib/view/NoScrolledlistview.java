package com.fph.appnavigationlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 * com.fph.NoScrolledlistview.java
 * 
 * Created by wang on 2016上午11:19:48 
 * 
 * Tips: 用于scrollview嵌套  
 */
public class NoScrolledlistview extends ListView {

	public NoScrolledlistview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NoScrolledlistview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrolledlistview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置不滚动
	 */
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
