package com.fph.appnavigationlib.valuefix;

import android.graphics.Color;

import com.fph.appnavigationlib.displayer.CircleBitmapDisplay;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.HashMap;
import java.util.Map;

public class DemoValueFixer implements ValueFix {

	public static DisplayImageOptions optionsHeadRound;
	public static DisplayImageOptions optionsHeadCircle_border;
	public static DisplayImageOptions optionsHeadCircle;
	public static Map<String, DisplayImageOptions> imageOptions;

	private static int waitDrawable = 0;
	private static int errorDrawable = 0;

	public static int bord_color = Color.WHITE;
	public static int bord_size=2;

	public static String DEFAULT = "default";
	public static String ROUND = "round";
	public static String CIRCLE = "circle";
	public static String CIRCLE_BRODER = "circle_brode";
	public static String FADEIN = "fadein";

	private static class Holder {
		public static DemoValueFixer valueFixer = new DemoValueFixer();
	}

	public static DemoValueFixer getInstance() {
		return Holder.valueFixer;
	}

	public DemoValueFixer() {

		imageOptions = new HashMap<String, DisplayImageOptions>();
		DisplayImageOptions optionsDefault = new DisplayImageOptions.Builder().showImageOnFail(errorDrawable)
				.showImageForEmptyUri(errorDrawable).showImageOnLoading(waitDrawable).cacheInMemory(true)
				.cacheOnDisk(true).build();

		imageOptions.put(DEFAULT, optionsDefault);

		// 倒角的图
		optionsHeadRound = new DisplayImageOptions.Builder().showImageOnFail(errorDrawable)
				.showImageForEmptyUri(errorDrawable).showImageOnLoading(waitDrawable).cacheInMemory(true)
				.cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(5)).build();

		imageOptions.put(ROUND, optionsHeadRound);

		// 带有边框的圆形图
		optionsHeadCircle_border = new DisplayImageOptions.Builder().showImageForEmptyUri(errorDrawable)
				.showImageOnLoading(waitDrawable).cacheInMemory(true).cacheOnDisk(true).showImageOnFail(errorDrawable)
				.displayer(new CircleBitmapDisplay(bord_size, bord_color)).build();
		imageOptions.put(CIRCLE_BRODER, optionsHeadCircle_border);

		// 无边框的圆形
		optionsHeadCircle = new DisplayImageOptions.Builder().showImageOnFail(errorDrawable)
				.showImageForEmptyUri(errorDrawable).showImageOnLoading(waitDrawable).cacheInMemory(true)
				.cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(
						360)/* new CircleBitmapDisplay(1,Color.TRANSPARENT) */)
				.build();

		imageOptions.put(CIRCLE, optionsHeadCircle);

		DisplayImageOptions optionFadeIn = new DisplayImageOptions.Builder().showImageOnFail(errorDrawable)
				.displayer(new FadeInBitmapDisplayer(300)).showImageForEmptyUri(errorDrawable)
				.showImageOnLoading(waitDrawable).cacheInMemory(true).cacheOnDisk(true).build();

		imageOptions.put(FADEIN, optionFadeIn);
	}

	
	public DemoValueFixer setBord(int size , int color){
		bord_color = color;
		bord_size =size;
		return Holder.valueFixer = new DemoValueFixer();
	}
	
	@Override
	public Object fix(Object o, String type) {
		if (o == null)
			return null;
		return o;
	}

	@Override
	public DisplayImageOptions imageOptions(String type) {

		if (imageOptions.containsKey(type)) {
			return imageOptions.get(type);
		}
		return imageOptions.get(DEFAULT);

	}

}
