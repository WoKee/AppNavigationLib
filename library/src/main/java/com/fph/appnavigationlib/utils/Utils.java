package com.fph.appnavigationlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fph.appnavigationlib.base.BaseApplication;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * 
 *
 * com.fph.Utils.java
 * 
 * Created by wang on 2016上午11:02:33
 * 
 * Tips: 公共utils 主项目集成实现更多
 */
public class Utils {

	/**
	 * 
	 * @param msg
	 */
	private static void Log(int msg) {
		Log(msg + "");
	}

	/**
	 * 不需要tag的log 默认当前活动界面为tag
	 * 
	 * @param msg
	 */
	private static void Log(String msg) {
		if (BaseApplication.isDebug) {
			KLog.d(msg);
		}
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || TextUtils.isEmpty(string) || string.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * toast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showToast(String string) {
		showToast(ApplicationUtil.getApplicationContext(), string);
	}

	/**
	 * 数字格式化 int 2.0直接可转2 
	 * @param string
	 * @return
	 */
	public static int parseInt(String string) {
		if (string.contains(".")) {
			return ((Double) parseDouble(string)).intValue();
		}
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
		}
		return -1;
	}
	/**
	 * 数字格式化 double  2直接可转2.0
	 * @param string
	 * @return
	 */
	public static double parseDouble(String string){
		if (!string.contains(".")) {
			return parseInt(string);
		}
		try {
			return parseDouble(string);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1.0;
	}
	

	/**
	 * toast
	 * 
	 * @param context
	 * @param string
	 */
	public static void showToast(Context context, String string) {
		if (context == null) {
			return;
		}
		try {
			Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
			Log(e.getMessage());
		}
	}

	/**
	 * 日期倒计时解析 仅支持以下符号解析(不支持月份) 并可随意组合 yyyy年 dd日 HH时 mm分 ss秒
	 * 
	 * @param data
	 * @param format
	 * @return
	 */
	public static String simpleCountDownDate(long date, String format) {

		long yyyy = 60 * 60 * 24 * 365;
		long dd = 60 * 60 * 24;
		long HH = 60 * 60;
		long mm = 60;

		try {
			if (format.contains("yyyy")) {
				if (date / yyyy > 0) {
					format = format.replaceAll("yyyy", date / yyyy + "");
				} else {
					format = format.replaceAll("yyyy", "0");
				}
			}
			date = date - (date / yyyy) * yyyy;
			if (format.contains("dd")) {
				if (date / dd > 0) {
					format = format.replaceAll("dd",
							date / dd > 99 ? date / dd + "" : (date / dd > 9 ? "0" + date / dd : "00" + date / dd));
				} else {
					format = format.replaceAll("dd", "000");
				}
			}
			date = date - (date / dd) * dd;
			if (format.contains("HH")) {
				if (date / HH > 0) {
					format = format.replaceAll("HH", date / HH > 9 ? date / HH + "" : "0" + date / HH);
				} else {
					format = format.replaceAll("HH", "00");
				}
			}
			date = date - (date / HH) * HH;
			if (format.contains("mm")) {
				if (date / mm > 0) {
					format = format.replaceAll("mm", date / 60 > 9 ? date / mm + "" : "0" + date / mm);
				} else {
					format = format.replaceAll("mm", "00");
				}
			}
			date = date - (date / mm) * mm;
			if (format.contains("ss")) {
				if (date > 0) {
					format = format.replaceAll("ss", date > 9 ? date + "" : "0" + date);
				} else {
					format = format.replaceAll("ss", "00");
				}
			}

			return format;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "格式化出错";
	}

	/**
	 * 日期格式化抽象 Tips: simpleDate(date,"yyyy-MM-dd")
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String simpleDate(long date, String format) {
		try {
			return new SimpleDateFormat(format, Locale.CHINA).format(new Date(date));
		} catch (Exception e) {

		}
		return "格式化出错";
	}

	/**
	 * 日期解析抽象 Tips
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Long parseDate(String date, String format) {

		try {
			return new SimpleDateFormat(format, Locale.CHINA).parse(date).getTime();
		} catch (Exception e) {
		}
		return 0l;
	}

	/**
	 * dip2px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		// Log.i("TEST", "==px:"+(int) (dipValue * scale + 0.5f));
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px2dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		// Log.i("TEST", "==dip:"+(int) (pxValue / scale + 0.5f));
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * getVersion
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersion(Context context) {
		String versionString = null;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			versionString = version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionString;
	}

	/**
	 * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
	 * 
	 * @param context
	 * @return true 表示开启
	 */
	public static final boolean isOPen(final Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		// 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
		boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (gps || network) {
			return true;
		}

		return false;
	}

	// 生成UUId或随机数字
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		// String temp = str.substring(0, 8) + str.substring(9, 13) +
		// str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return str;
	}

	/**
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}
	/**
	 * 返回当前程序版本code
	 */
	public static int getAppVersionCode(Context context) {
	    try {  
	        PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return pi.versionCode;  
	    } catch (Exception e) {
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	        return 0;  
	    } 
	}

	/**
	 * * 填写表单时判断整体
	 * 
	 * @param texts
	 * @return
	 */
	public static boolean isEmpty(TextView... texts) {
		if (texts == null)
			return false;
		for (int i = 0; i < texts.length; i++) {
			boolean empty = TextUtils.isEmpty(texts[i].getText().toString().trim());
			if (empty) {
				return empty;
			}
		}
		return false;
	}

	/**
	 * 填写表单时判断整体
	 * 
	 * @param editTexts
	 * @return
	 */
	public static boolean isEmpty(EditText... editTexts) {

		if (editTexts == null)
			return false;
		for (int i = 0; i < editTexts.length; i++) {
			boolean empty = TextUtils.isEmpty(editTexts[i].getText().toString().trim());
			if (empty) {
				return empty;
			}
		}
		return false;
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	// 中文符号
	public static String[] ChineseInterpunction = new String[] { "“", "”", "‘", "’", "。", "，", "；", "：", "？", "！", "……",
			"—", "～", "（", "）", "《", "》" };
	// 英文符号
	public static String[] EnglishInterpunction = new String[] { "\"", "\"", "'", "'", ".", ",", ";", ":", " ", "!",
			"…", "-", "~", "(", ")", "<", ">" };

	/**
	 * 英文符号转中文符号
	 * 
	 * @param string
	 * @return
	 */
	public static String EnglishToChinese(String string) {
		for (int i = 0; i < EnglishInterpunction.length; i++) {
			string = string.replaceAll(EnglishInterpunction[i], ChineseInterpunction[i]);
		}
		return string;
	}

	/**
	 * 中文符号转英文符号
	 * 
	 * @param string
	 * @return
	 */
	public static String ChineseToEnglish(String string) {
		for (int i = 0; i < ChineseInterpunction.length; i++) {
			string = string.replaceAll(ChineseInterpunction[i], EnglishInterpunction[i]);
		}
		return string;
	}

	/**
	 * 判断str1中包含str2的个数
	 * 
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	public static int countStr(String str1, String str2) {
		int counter = 0;
		if (str1.indexOf(str2) == -1) {
			return 0;
		} else if (str1.indexOf(str2) != -1) {
			counter++;
			countStr(str1.substring(str1.indexOf(str2) + str2.length()), str2);
			return counter;
		}
		return 0;
	}

	/**
	 * {@link public static SpannableString getCustomString(String text, String
	 * color,int start ,int end , int spannable) { }
	 * 
	 * @param text
	 * @param color
	 * @return
	 */
	public static SpannableString getCustomString(String text, String color) {
		return getCustomString(text, color, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	/**
	 * 拼接不同颜色的字符串
	 * 
	 * @param text
	 * @param color
	 * @param start
	 * @param end
	 * @param spannable
	 * @return
	 */
	public static SpannableString getCustomString(String text, String color, int start, int end, int spannable) {

		if (isEmpty(text)) {
			return new SpannableString("");
		}

		if (text.length() < end) {
			return new SpannableString(text);
		}

		SpannableString spannableString = new SpannableString(text);

		ForegroundColorSpan spanColor = new ForegroundColorSpan(Color.parseColor(color));

		spannableString.setSpan(spanColor, start, end,
				spannable/* Spannable.SPAN_EXCLUSIVE_EXCLUSIVE */);
		return spannableString;

	}

	/**
	 * 
	 * 倒计时封装类 按秒倒计时
	 * 
	 * @param timer
	 *            倒计时时长
	 * @param isCountTimer返回值是否为倒序
	 * @return
	 * 
	 * 		Tips:
	 * 
	 *         Utils.countDownTimer(10, true).subscribe(new Subscriber
	 *         <Integer>() {
	 * @Override public void onCompleted() {
	 *           System.out.println("倒计时结束"+"-----"+Utils.simpleDate(System.
	 *           currentTimeMillis(), "H:mm:ss")); }
	 * @Override public void onError(Throwable arg0) { }
	 * @Override public void onNext(Integer arg0) {
	 *           System.out.println("倒计时----"+arg0+"-----"+Utils.simpleDate(
	 *           System.currentTimeMillis(), "H:mm:ss")); }
	 * @Override public void onStart() { super.onStart();
	 *           System.out.println("开始倒计时---"+Utils.simpleDate(System.
	 *           currentTimeMillis(), "H:mm:ss")); } });
	 */
	public static Observable<Long> countDownTimer(int timer, boolean isCountTimer) {
		return countDownTimer(timer, TimeUnit.SECONDS, isCountTimer);
	}

	/**
	 * 倒计时封装类
	 * 
	 * @param timer
	 *            倒计时时长
	 * @param countDownTimeUnit
	 *            倒计时enum(TimeUnit)
	 * @param isCountTimer返回值是否为倒序
	 * @return
	 */
	public static Observable<Long> countDownTimer(int timer, TimeUnit countDownTimeUnit, boolean isCountTimer) {

		if (timer < 0) {
			timer = 0;
		}
		final int countTime = timer;
		final boolean isCountTime = isCountTimer;
		return Observable.interval(0, 1, countDownTimeUnit).map(new Func1<Long, Long>() {

			@Override
			public Long call(Long arg0) {
				// TODO Auto-generated method stub
				if (isCountTime) {
					return countTime - arg0;
				}
				return arg0;
			}
		}).take(countTime + 1).observeOn(AndroidSchedulers.mainThread());
	}

}
