/**
 * 
 */
package com.fph.appnavigationlib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.fph.appnavigationlib.listener.ObjectListenerImp;

/**
 *
 * com.fph.SoftKeyboardUtil.java
 * 
 * Created by wang on 2016年6月13日下午5:08:08
 * 
 * Tips:键盘监听事件
 */
public class SoftKeyboardUtil {
	public static void observeSoftKeyboard(Activity activity, final ObjectListenerImp listener) {
		final View decorView = activity.getWindow().getDecorView();
		decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			int previousKeyboardHeight = -1;

			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				decorView.getWindowVisibleDisplayFrame(rect);
				int displayHeight = rect.bottom - rect.top;
				int height = decorView.getHeight();
				int keyboardHeight = height - displayHeight;
				if (previousKeyboardHeight != keyboardHeight) {
					boolean hide = (double) displayHeight / height > 0.8;
					listener.onSoftKeyBoardChange(keyboardHeight, !hide);
				}

				previousKeyboardHeight = height;

			}
		});
	}

	public static void hideKeybroad(Activity activity) {
		final View decorView = activity.getWindow().getDecorView();
		getInputMethodManager(activity).hideSoftInputFromWindow(decorView.getWindowToken(), 0);
	}

	public static void showKeyBroad(Activity activity) {
		final View decorView = activity.getWindow().getDecorView();
		getInputMethodManager(activity).showSoftInput(decorView, InputMethodManager.SHOW_FORCED);

	}
	public static void showOrHideKeyBroad(Activity activity){
		getInputMethodManager(activity).toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static InputMethodManager getInputMethodManager(Context context) {
		return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

}