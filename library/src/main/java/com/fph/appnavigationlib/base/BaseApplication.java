
package com.fph.appnavigationlib.base;

import android.support.multidex.MultiDexApplication;

import com.fph.appnavigationlib.log.OkHttpLogInterceptor;
import com.fph.appnavigationlib.utils.ApplicationUtil;
import com.fph.appnavigationlib.utils.BaseSharedPreferencesUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 *
 * com.fph.BaseApplication.java
 * 
 * Created by wang on 2016下午3:47:43
 * 
 * Tips:
 */
public abstract class BaseApplication extends MultiDexApplication {

	/**
	 * 是否为测试版本, as为BuildConfig.DEBUG
	 */
	public static boolean isDebug = true;

	/*
         * (non-Javadoc)
         *
         * @see android.app.Application#onCreate()
         */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		

		ApplicationUtil.initContext(this.getApplicationContext());

		initImageLoader();

		initSharedPreferences();

		initOkHttp();
		init();
//		Utils.showToast(A.);
		// LeakCanary.install() 会返回一个预定义的 RefWatcher，同时也会启用一个
		// ActivityRefWatcher，用于自动监控调用 Activity.onDestroy() 之后泄露的 activity。
	}

	/**
	 * 
	 */
	public static  void initOkHttp() {
		// TODO Auto-generated method stub
		   OkHttpClient okHttpClient = new OkHttpClient.Builder()
	                .addInterceptor(new OkHttpLogInterceptor())
	                  .connectTimeout(30*1000L, TimeUnit.MILLISECONDS)
	                  .readTimeout(30*1000L, TimeUnit.MILLISECONDS)
	                  //其他配置
	                 .build();
//		   java.net.SocketTimeoutException: timeout
//		   android.system.ErrnoException: recvfrom failed: ETIMEDOUT (Connection timed out)
	        OkHttpUtils.initClient(okHttpClient);		
//		OkHttpUtils.getInstance().debug("NetLog",true).setConnectTimeout(20 * 60, TimeUnit.MILLISECONDS);
//		// //使用https，但是默认信任全部证书
//		// OkHttpUtils.getInstance().setCertificates();
//		OkHttpUtils.getInstance(OkHttpInstance.getInstance());
	}

	public static void initFileOkHttp(){
		  OkHttpClient okHttpClient = new OkHttpClient.Builder()
	                .addInterceptor(new OkHttpLogInterceptor())
	                  .connectTimeout(100*1000L, TimeUnit.MILLISECONDS)
	                  .readTimeout(100*1000L, TimeUnit.MILLISECONDS)
	                  //其他配置
	                 .build();
		  OkHttpUtils.initClient(okHttpClient);	
	}
	
	protected abstract void init();
	/**
	 * 配置sharePre..
	 */
	private void initSharedPreferences() {
		BaseSharedPreferencesUtil.init(this.getApplicationContext(), setUserKey());
	}

	/**
	 * 主项目设置sp userkey
	 * 
	 * @return
	 */
	protected abstract String setUserKey();

	/**
	 * 配置全局imageloader
	 */
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this.getApplicationContext())
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCache(new UnlimitedDiskCache(
						new File(this.getApplicationContext().getExternalCacheDir() + "/imageLoader/imageCach")))
				.tasksProcessingOrder(QueueProcessingType.LIFO).memoryCache(new WeakMemoryCache())
				.memoryCacheSize(1500000)
				// .writeDebugLogs() // Remove for release app
//				 .enableLogging() // Not necessary in common
				.build();
		ImageLoader.getInstance().init(config);
	}

}
