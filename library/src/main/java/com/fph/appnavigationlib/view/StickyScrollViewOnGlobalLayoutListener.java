/**
 * 
 */
package com.fph.appnavigationlib.view;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ScrollView;

import com.fph.appnavigationlib.listener.ObjectListenerImp;

/**
 *
 * com.fph.StickyScrollViewOnGlobalLayoutListener.java
 * 
 * Created by wang on 2016年6月12日上午11:15:56 
 * 
 * Tips:
 */
public class StickyScrollViewOnGlobalLayoutListener implements OnGlobalLayoutListener {

	private StickyScrollView stickyScrollView ;
	
	private int glTotal=0;
	
	private ObjectListenerImp objectListenerImp ;
	
	public StickyScrollViewOnGlobalLayoutListener(StickyScrollView stickyScrollView,ObjectListenerImp objectListenerImp){
		this.stickyScrollView=stickyScrollView;
		this.objectListenerImp=objectListenerImp;
	}
	
	/* (non-Javadoc)
	 * @see android.view.ViewTreeObserver.OnGlobalLayoutListener#onGlobalLayout()
	 */
	@Override
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		if (stickyScrollView!=null && stickyScrollView.getStickyViews()!=null && glTotal <=stickyScrollView.getStickyViews().size()) {
			stickyScrollView.fullScroll(ScrollView.FOCUS_UP);
			if (glTotal ==stickyScrollView.getStickyViews().size() &&objectListenerImp !=null) {
				objectListenerImp.onGlobalLayoutEnd(true);
			}
		}
		glTotal++;
	}

}
