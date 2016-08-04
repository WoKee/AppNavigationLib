package com.fph.appnavigationlib.displayer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.utils.L;

/**
 * 
 *
 * com.fph.CircleBitmapDisplay.java
 * 
 * Created by wang on 2016上午11:35:00 
 * 
 * Tips:修改兼容1.9.3
 */
public class CircleBitmapDisplay implements BitmapDisplayer{

	private int mBorderThickness = 0;
	private int mBorderColor = 0x80fed5ae;
	public CircleBitmapDisplay() {
		// TODO Auto-generated constructor stub
	}

	public CircleBitmapDisplay(int thickness, int color)
	{
		this.mBorderThickness = thickness;
		this.mBorderColor = color;
	}

//	@Override
//	public Bitmap display(Bitmap bitmap, ImageView imageView) {
//		// TODO Auto-generated method stub
//		Bitmap roundedBitmap =circle(bitmap, imageView);
//		imageView.setImageBitmap(roundedBitmap);
//		return roundedBitmap;
//	}
	/**
	 * Process incoming {@linkplain Bitmap} to make rounded corners according to target {@link ImageView}.<br />
	 * This method <b>doesn't display</b> result bitmap in {@link ImageView}
	 *
	 * @param bitmap      Incoming Bitmap to process
	 * @param imageView   Target {@link ImageView} to display bitmap in
	 * @return Result bitmap with rounded corners
	 */
	public Bitmap circle(Bitmap bitmap, ImageView imageView) {
		Bitmap roundBitmap;

		/**
		 * 图片的宽
		 */
		int bw = bitmap.getWidth();
		/**
		 * 图片的高
		 */
		int bh = bitmap.getHeight();
		/**
		 * ImageView的宽
		 */
		int vw = imageView.getWidth();
		/**
		 * ImageView的高
		 */
		int vh = imageView.getHeight();
		if (vw <= 0) vw = bw;
		if (vh <= 0) vh = bh;

		int width, height;
		Rect srcRect;
		Rect destRect;
		switch (imageView.getScaleType()) {
		case CENTER_INSIDE:
			float vRation = (float) vw / vh;
			float bRation = (float) bw / bh;
			int destWidth;
			int destHeight;
			if (vRation > bRation) {
				destHeight = Math.min(vh, bh);
				destWidth = (int) (bw / ((float) bh / destHeight));
			} else {
				destWidth = Math.min(vw, bw);
				destHeight = (int) (bh / ((float) bw / destWidth));
			}
			int x = (vw - destWidth) / 2;
			int y = (vh - destHeight) / 2;
			srcRect = new Rect(0, 0, bw, bh);
			destRect = new Rect(x, y, x + destWidth, y + destHeight);
			width = vw;
			height = vh;
			break;
		case FIT_CENTER:
		case FIT_START:
		case FIT_END:
		default:
			vRation = (float) vw / vh;
			bRation = (float) bw / bh;
			if (vRation > bRation) {
				width = (int) (bw / ((float) bh / vh));
				height = vh;
			} else {
				width = vw;
				height = (int) (bh / ((float) bw / vw));
			}
			srcRect = new Rect(0, 0, bw, bh);
			destRect = new Rect(0, 0, width, height);
			break;
		case CENTER_CROP:
			vRation = (float) vw / vh;
			bRation = (float) bw / bh;
			int srcWidth;
			int srcHeight;
			if (vRation > bRation) {
				srcWidth = bw;
				srcHeight = (int) (vh * ((float) bw / vw));
				x = 0;
				y = (bh - srcHeight) / 2;
			} else {
				srcWidth = (int) (vw * ((float) bh / vh));
				srcHeight = bh;
				x = (bw - srcWidth) / 2;
				y = 0;
			}
			width = srcWidth;// Math.min(vw, bw);
			height = srcHeight;//Math.min(vh, bh);
			srcRect = new Rect(x, y, x + srcWidth, y + srcHeight);
			destRect = new Rect(0, 0, width, height);
			break;
		case FIT_XY:
			width = vw;
			height = vh;
			srcRect = new Rect(0, 0, bw, bh);
			destRect = new Rect(0, 0, width, height);
			break;
		case CENTER:
		case MATRIX:
			width = Math.min(vw, bw);
			height = Math.min(vh, bh);
			x = (bw - width) / 2;
			y = (bh - height) / 2;
			srcRect = new Rect(x, y, x + width, y + height);
			destRect = new Rect(0, 0, width, height);
			break;
		}

		try {
			roundBitmap = getCircleBitmap(bitmap, srcRect, destRect, width, height);
		} catch (OutOfMemoryError e) {
			L.e(e, "Can't create bitmap with rounded corners. Not enough memory.");
			roundBitmap = bitmap;
		}

		return roundBitmap;
	}

	private Bitmap getCircleBitmap(Bitmap bitmap, Rect srcRect, Rect destRect, int width, int height) {
		//		final RectF destRectF = new RectF(destRect);

		int w = destRect.width() / 2;
		int h = destRect.height() / 2;

		int radius = Math.min(w, h)- mBorderThickness;

		Bitmap roundBitmap = getCroppedBitmap(bitmap, radius);

		//		canvas.drawBitmap(bitmap, srcRect, destRectF, paint);
		//		canvas.drawCircle(w, h, radius, paint);
		////		canvas.drawRoundRect(destRectF, roundPixels, roundPixels, paint);
		//
		//		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		////		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		//		canvas.drawBitmap(bitmap, srcRect, destRectF, paint);

//		if(mBorderThickness  > 0)
		{
			Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setFilterBitmap(true);
			paint.setDither(true);
			paint.setAlpha(0);
			paint.setColor(mBorderColor);
			canvas.drawCircle(w, h, radius + mBorderThickness, paint);
			canvas.drawBitmap(roundBitmap, w  - radius, h  - radius, null);
			return output;
		}

//		return roundBitmap;
	}

	public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap scaledSrcBmp;
		int diameter = radius * 2;
		if (bmp.getWidth() != diameter || bmp.getHeight() != diameter)
			scaledSrcBmp = Bitmap.createScaledBitmap(bmp, diameter, diameter, false);
		else
			scaledSrcBmp = bmp;
		Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(), scaledSrcBmp.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(), scaledSrcBmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
				scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);

		return output;
	}

	/* (non-Javadoc)
	 * @see com.nostra13.universalimageloader.core.display.BitmapDisplayer#display(android.graphics.Bitmap, com.nostra13.universalimageloader.core.imageaware.ImageAware, com.nostra13.universalimageloader.core.assist.LoadedFrom)
	 */
	@Override
	public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
		// TODO Auto-generated method stub
		if (!(imageAware instanceof ImageViewAware)) {
			throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
		}
		Bitmap roundedBitmap =circle(bitmap, ((ImageViewAware)imageAware).getWrappedView());
		imageAware.setImageBitmap(roundedBitmap);
//		imageAware.setImageDrawable(new RoundedDrawable(bitmap, cornerRadius, margin));
	}
}
