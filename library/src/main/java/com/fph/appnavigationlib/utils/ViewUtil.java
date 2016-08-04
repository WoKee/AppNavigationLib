package com.fph.appnavigationlib.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fph.appnavigationlib.listener.AnimateFirstDisplayListener;
import com.fph.appnavigationlib.valuefix.DemoValueFixer;
import com.fph.appnavigationlib.valuefix.ValueFix;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


/**
 * 
 *
 * com.fph.ViewUtil.java
 * 
 * Created by wang on 2016上午11:07:23 
 * 
 * Tips:
 */
public class ViewUtil {

    public static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	
	/**
	 * 对象绑定视图
	 *"file://"+
	 * @param v
	 * @param value
	 */
	public static void bindView(Object value, View v) {
		bindView(v, value, DemoValueFixer.DEFAULT);
	}
    /**
	 * 对象绑定视图
	 *"file://"+
	 * @param v
	 * @param value
	 */
	public static void bindView(View v, Object value) {
		bindView(v, value, DemoValueFixer.DEFAULT);
	}

	/**
	 * 对象绑定视图,同时通过全局的数据修复
	 *
	 * @param v
	 * @param value
	 */
	public static void bindView(View v, Object value, String type) {
		bindView(v, value, type, -1,-1);
	}
	/**
	 * 对象绑定视图,同时通过全局的数据修复
	 * @param v
	 * @param value
	 * @param type
	 * @param waitID
	 * @param errorID
	 */
	public static void bindView(View v, Object value, int waitID, int errorID) {
		bindView(v, value, DemoValueFixer.DEFAULT, waitID, errorID, animateFirstListener);
	}
	/**
	 * 对象绑定视图,同时通过全局的数据修复
	 * @param v
	 * @param value
	 * @param type
	 * @param waitID
	 * @param errorID
	 */
	public static void bindView(View v, Object value, String type, int waitID, int errorID) {
		bindView(v, value, type, waitID, errorID, animateFirstListener);
	}


	/**
	 * 对象绑定视图,同时通过全局的数据修复
	 *
	 * @param v
	 * @param value
	 */
	public static void bindView(View v, Object value, String type, ImageLoadingListener imageLoadingListener) {
		bindView(v, value, type, -1, -1, imageLoadingListener);
	}
	

	/**
	 * 	 * 对象绑定视图,同时通过全局的数据修复抽象
	 * @param v
	 * @param value
	 * @param type
	 * @param waitID
	 * @param errorID
	 * @param imageLoadingListener
	 */
	public static void bindView(View v, Object value, String type, int waitID, int errorID,
								ImageLoadingListener imageLoadingListener){
		
		if (v == null/* || value == null*/)
		return;
	if (v instanceof TextView) {
		ValueFix fix = DemoValueFixer.getInstance();
		if (fix != null) {
			value = fix.fix(value, type);
		}
		if (value==null) {
			value="";
		}
		if (value instanceof CharSequence) {
			((TextView) v).setText((CharSequence) value);
		} else if (value instanceof SpannableString) {
			((TextView) v).append((SpannableString)value);
		}else{
			((TextView) v).setText(value.toString());
		}
	}
	if (v instanceof ImageView) {
		
		ValueFix fix = DemoValueFixer.getInstance();
		DisplayImageOptions options = null;
		if (fix != null) {
			if (waitID!=-1&&errorID!=-1)
//			options = fix.imageOptions(type);
			options= new DisplayImageOptions.Builder().cloneFrom(fix.imageOptions(type))
					.showImageOnFail(errorID)
					.showImageForEmptyUri(errorID)
					.showImageOnLoading(waitID)
					.build();
			else
				options=fix.imageOptions(type);
		}else{
//			options=new DisplayImageOptions.Builder().cloneFrom(ImageLoader.getInstance().)
//					.showImageOnFail(errorID)
//					.showStubImage(waitID).build();
		}
		

		if (value instanceof String) {
			ImageLoader.getInstance().displayImage((String) value,
					(ImageView) v, options,imageLoadingListener);
		} 
		if (value==null) {
		return;
		}
		if (value instanceof Drawable) {
			((ImageView) v).setImageDrawable((Drawable) value);
		} else if (value instanceof Bitmap) {
			((ImageView) v).setImageBitmap((Bitmap) value);
		} else if (value instanceof Integer) {
			ImageLoader.getInstance().displayImage("drawable://" + value,
					(ImageView) v, options, imageLoadingListener);
//			((ImageView) v).setImageResource((Integer) value);
		}
	}}
	
	/**
	 * 对象绑定视图,同时通过全局的数据修复,DisplayImageOptions没有抽象其他方法 暂不用
	 *
	 * @param v
	 * @param value
	 */
	public static void bindView(View v, Object value, DisplayImageOptions type) {
		if (v instanceof ImageView) {
//			ValueFix fix = IocContainer.getShare().get(ValueFix.class);
			DisplayImageOptions options = type;
//			if (fix != null) {
//				options = fix.imageOptions("default");
//			}
			if (value instanceof String) {
				ImageLoader.getInstance().displayImage((String) value,
						(ImageView) v, options,animateFirstListener);
			} else if (value instanceof Drawable) {
				((ImageView) v).setImageDrawable((Drawable) value);
			} else if (value instanceof Bitmap) {
				((ImageView) v).setImageBitmap((Bitmap) value);
			} else if (value instanceof Integer) {
				ImageLoader.getInstance().displayImage("drawable://" + value,
						(ImageView) v, options, animateFirstListener);
//				((ImageView) v).setImageResource((Integer) value);
			}
		}

	}
	
	
}
