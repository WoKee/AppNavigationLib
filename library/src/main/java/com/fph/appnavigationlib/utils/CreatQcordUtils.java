package com.fph.appnavigationlib.utils;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

public class CreatQcordUtils {

	
	/**
	 * 返回不带logo的二维码
	 * @param url
	 * @param QR_WIDTH
	 * @param QR_HEIGHT
	 * @return
	 * @throws WriterException 
	 */
	public static final Bitmap create2DCoderBitmap(String url, int QR_WIDTH,
												   int QR_HEIGHT) throws WriterException {
		return create2DCoderBitmapAndLogo(url, QR_WIDTH, QR_HEIGHT, null);
	}
	
	/**
{}	 * 生成一个二维码图像带logo
	 *
	 * @param url
	 *            传入的字符串，通常是一个URL
	 * @param QR_WIDTH
	 *            宽度（像素值px）
	 * @param QR_HEIGHT
	 *            高度（像素值px）
	 *  @param logo 
	 *  		logo图
	 * @return
	 * @throws WriterException 
	 */
	public static final Bitmap create2DCoderBitmapAndLogo(String url, int QR_WIDTH,
														  int QR_HEIGHT, Bitmap logo) throws WriterException {
			// 判断URL合法性
			if (url == null || "".equals(url) || url.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hints.put(EncodeHintType.MARGIN, 0); 
			// 图像数据转换，使用了矩阵转换
			BitMatrix bitMatrix = new QRCodeWriter().encode(url,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			// 下面这里按照二维码的算法，逐个生成二维码的图片，
			// 两个for循环是图片横列扫描的结果
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			// 生成二维码图片的格式，使用ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			// 显示到一个ImageView上面
			// sweepIV.setImageBitmap(bitmap);
			
//			addLogo(bitmap, logo);
			return logo==null?bitmap:addLogo(bitmap, logo);
	}

	private static final int BLACK = 0xff000000;

	/**
	 * 生成一个二维码图像
	 *
	 * @param url
	 *            传入的字符串，通常是一个URL
	 * @param widthAndHeight
	 *           图像的宽高
	 * @return
	 * @throws WriterException 
	 */
	public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException
			 {
		return create2DCoderBitmap(str, widthAndHeight, widthAndHeight);
	}
	
	/**
	 * 带logo
	 * @param str
	 * @param widthAndHeight
	 * @param logo
	 * @return
	 * @throws WriterException 
	 */
	public static Bitmap createQRCodeAndLogo(String str, int widthAndHeight, Bitmap logo) throws WriterException{
		
		return create2DCoderBitmapAndLogo(str, widthAndHeight, widthAndHeight, logo);

	}
	
	
	 /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

	
}
