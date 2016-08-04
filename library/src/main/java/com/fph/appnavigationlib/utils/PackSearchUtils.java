/**
 * 
 */
package com.fph.appnavigationlib.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;
import java.util.NoSuchElementException;

import rx.Observable;
import rx.functions.Func1;

/**
 *
 * com.example.test.PackSearchUtils.java
 * 
 * Created by wang on 2016上午11:50:07
 * 
 * Tips:
 */
public class PackSearchUtils {

	// // 默认市场过滤
	// static String[] markets = new String[] {
	// "com.tencent.android.qqdownloader", "com.xiaomi.market",
	// "com.wandoujia.phoenix2", "com.qihoo.appstore", "com.baidu.appsearch" };
	//
	// // 获取已安装的默认市场列表
	// public static List<PackageInfo> getAvilibleList() {
	// return getAvilibleList(markets);
	// }
	
	
	/**
	 * 按照过滤规则优先级 返回较前的程序
	 * 
	 * @param packageNameList
	 * @throws NoSuchElementException 过滤条件不满足抛异常
	 * @return
	 */
	public  static Observable<PackageInfo> getAviliblePriorityPackage(String[] packageNameList) {
		if (packageNameList == null ||packageNameList.length==0) {
			return Observable.error(new NullPointerException("过滤规则为空!"));
		}
		 final List<PackageInfo> pinfo = getPackInfo();// 获取所有已安装程序的包信息
		 if (pinfo==null||pinfo.size()==0) {
			return Observable.error(new NullPointerException("获取安装应用程序列表出错!"));
		}
		 
		try {
			return Observable.from(packageNameList)
					.concatMap(new Func1<String, Observable<PackageInfo>>() {
						@Override
						public Observable<PackageInfo> call(final String arg01) {
							return Observable.from(pinfo).filter(new Func1<PackageInfo, Boolean>() {
								@Override
								public Boolean call(PackageInfo arg0) {
									return arg0.applicationInfo.packageName.equals(arg01);
								}
							}); 
						}
					}).take(1).first();
		} catch (Exception e) {
			// TODO: handle exception
			return Observable.error(e);
		}
	}
	

	/**
	 * 按照过滤规则 返回已安装的程序列表
	 * 
	 * @param packageNameList
	 * @return
	 */
	public static Observable<List<PackageInfo>>getAvilibleList(String[] packageNameList) {
		if (packageNameList == null ||packageNameList.length==0) {
			return Observable.error(new NullPointerException("过滤规则为空!"));
		}
		 final List<PackageInfo> pinfo = getPackInfo();// 获取所有已安装程序的包信息
		 if (pinfo==null||pinfo.size()==0) {
			return Observable.error(new NullPointerException("获取安装应用程序列表出错!"));
		}
		return Observable.from(packageNameList)
				.concatMap(new Func1<String, Observable<PackageInfo>>() {
					@Override
					public Observable<PackageInfo> call(final String arg01) {
						return Observable.from(pinfo).filter(new Func1<PackageInfo, Boolean>() {
							@Override
							public Boolean call(PackageInfo arg0) {
								return arg0.applicationInfo.packageName.equals(arg01);
							}
						});
					}
				}).distinct().takeLastBuffer(packageNameList.length);
	}

	/**
	 * 获取已安装的应用商店 不兼容一加的商店
	 * 
	 * @return
	 */
	public static List<ResolveInfo> getMarketList() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		List<ResolveInfo> resolveInfo = ApplicationUtil.getApplicationContext().getPackageManager()
				.queryIntentActivities(intent, 0);
		return resolveInfo;
	}

	// 判断市场是否存在的方法
	public static boolean isAvilible(String packageName) {
		List<PackageInfo> pinfo = getPackInfo();// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (PackageInfo packageInfo : pinfo) {
				if (packageInfo.packageName.equals(packageName)) {
					return true;
				}
			}
		}
		return false;
	}

	// 跳转指定应用市场
	public static void startMarket(String marketPkg, String appPkg) {

		if (Utils.isEmpty(appPkg) && Utils.isEmpty(marketPkg)) {
			return;
		}
		try {
			Uri uri = Uri.parse("market://details?id=" + appPkg);
			Intent intent = new Intent();
			intent.setPackage(marketPkg);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(uri);
			ApplicationUtil.getApplicationContext().startActivity(intent);
		} catch (Exception e) {
			// 没有找到市场
			e.printStackTrace();
		}
	}

	// 跳转指定应用市场
	public static void startMarket(String appPkg) throws Exception {

		if (Utils.isEmpty(appPkg)) {
			return;
		}
		try {
			Uri uri = Uri.parse("market://details?id=" + appPkg);
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setData(uri);
			ApplicationUtil.getApplicationContext().startActivity(intent);
		} catch (Exception e) {
			// 没有找到市场
			throw e;
		}
	}

	// 获取已安装的程序列表
	public static List<PackageInfo> getPackInfo() {
		List<PackageInfo> pinfo = ApplicationUtil.getApplicationContext().getPackageManager().getInstalledPackages(0);// 获取所有已安装程序的包信息
		return pinfo;
	}

}
