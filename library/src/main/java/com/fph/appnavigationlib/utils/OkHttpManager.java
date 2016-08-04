/**
 * 
 */
package com.fph.appnavigationlib.utils;

import android.os.Handler;

import com.fph.appnavigationlib.bean.RootInfo;
import com.fph.appnavigationlib.bean.UploadFileForm;
import com.fph.appnavigationlib.bean.UploadFileRootInfo;
import com.fph.appnavigationlib.callback.BaseUploadFileCallback;
import com.fph.appnavigationlib.callback.RootInfoCallBack;
import com.fph.appnavigationlib.callback.UploadFileCallBack;
import com.fph.appnavigationlib.conts.HttpStatusConts;
import com.socks.library.KLog;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

/**
 *
 * com.fph.OkHttpManager.java
 * 
 * Created by wang on 2016下午2:44:24
 * 
 * Tips:
 */
public class OkHttpManager {

	/**
	 * post解析工具 带handler (what)
	 *
	 * @param url
	 * @param list
	 * @param context
	 * @param handler
	 * @param what
	 */
	public static void post(final String url, final Map<String, String> list, final Handler handler, final int what,
							String tagCurrTime) {
		post(url, list, handler, what, false, tagCurrTime);
	}

	/**
	 * post解析工具 带handler (what) isCookie
	 * 
	 * @param url
	 * @param map
	 * @param context
	 * @param handler
	 * @param what
	 * @param isCookie
	 */
	public static void post(final String url, final Map<String, String> map, final Handler handler, final int what,
							final boolean isCookie, String tagCurrTime) {
		httppost(url, map, handler, what, isCookie, tagCurrTime);
	}

	/**
	 * post解析工具 带handler
	 * 
	 * @param url
	 * @param map
	 * @param context
	 * @param handler
	 */
	public static void post(final String url, final Map<String, String> map, final Handler handler,
							String tagCurrTime) {
		post(url, map, handler, HttpStatusConts.SUC, false, tagCurrTime);
	}

	/**
	 * post解析工具 带handler
	 * 
	 * @param url
	 * @param params
	 * @param context
	 * @param handler
	 */
	public static void post(final String url, final Map<String, String> params, final Handler handler,
							final boolean isCookie, String tagCurrTime) {
		post(url, params, handler, HttpStatusConts.SUC, isCookie, tagCurrTime);
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @param handler
	 * @param what
	 * @param isCookie
	 */
	public static void httppost(String url, final Map<String, String> params, final Handler handler, final int what,
								final boolean isCookie, String tagCurrTime) {

		sendHttp(url, params, handler, what, isCookie, "post", tagCurrTime);
	}

	/**
	 * 
	 * @param url
	 * @param handler
	 * @param what
	 */
	public static void get(String url, final Handler handler, final int what, String tagCurrTime) {

		sendHttp(url, null, handler, what, false, "get", tagCurrTime);

	}

	/**
	 * 
	 * @param <T>
	 * @param url
	 * @param params
	 * @param handler
	 * @param what
	 * @param isCookie
	 * @param type
	 */
	public static void sendHttp(String url, final Map<String, String> params, final Handler handler, final int what,
								final boolean isCookie, String type, String tagCurrTime) {
		try {
			if (type.equals("get")) {
				OkHttpUtils.get().url(url).tag(what + tagCurrTime).params(params).build().execute(new RootInfoCallBack(handler, what));
			} else if (type.equals("post")) {
				OkHttpUtils.post().url(url).tag(what + tagCurrTime).params(params).build()
						.execute(new RootInfoCallBack(handler, what));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			KLog.e(e.getMessage());
			if (handler == null) {
				return;
			}
			// 特殊异常 伪装成服务器异常
			RootInfo rootInfo = new RootInfo();
			rootInfo.setServerCode(0);
			rootInfo.setRequest(
					OkHttpUtils.post().url(url).tag(what + tagCurrTime).params(params).build().getRequest());
			rootInfo.setMsg(e.getMessage());
			rootInfo.setWhat(what);
			handler.sendMessage(handler.obtainMessage(HttpStatusConts.ERROR, rootInfo));
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param url
	 * @param params
	 * @param handler
	 * @param what
	 * @param isCookie
	 * @param type
	 */
	public static void sendHttp(String url, final Map<String, String> params, final UploadFileForm file, final int what, final UploadFileCallBack callBack) {
//	 String IMGUR_CLIENT_ID = "...";
//		 MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//		RequestBody requestBody = new MultipartBody.Builder()
//		            .setType(MultipartBody.FORM)
//		            .addFormDataPart("files", "logo-square.png",
//		                    RequestBody.create(MEDIA_TYPE_PNG, new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/1.png")))
//		            .build();
//
//		   final  Request request = new Request.Builder()
//		            .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
//		            .url(url)
//		            .post(new ProgressRequestBody(requestBody, new Listener() {
//						
//						@Override
//						public void onProgress(int progress) {
//							// TODO Auto-generated method stub
//							Utils.showToast("-------");
//						}
//					}))
//		            .build();
//
//		new Thread(){
//			public void run() {
//			    try {
//					Response response = new OkHttpClient().newCall(request).execute();
//					
//					String string  = response.body().toString();
//					Utils.showToast(string);
//					
//					
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//		}.start();
		if (file == null) {
			if (callBack!=null) {
				UploadFileRootInfo rootInfo =new UploadFileRootInfo() ;
				rootInfo.setCode(-2);
				rootInfo.setMsg("文件不存在");
				callBack.onError(rootInfo);
			}
			return;
		}
		
		OkHttpUtils.post().url(url).tag(what).files(file.getKey(), file.getFile()).params(params).build()
						.execute(new BaseUploadFileCallback(callBack));

	}

	/**
	 * 
	 * @param tag
	 */
	public static void killByTag(Object tag) {
		OkHttpUtils.getInstance().cancelTag(tag);
	}

	/*	*//**
			 * 
			 * @param map
			 * @return
			 *//*
			 * public static LinkedList<NameValuePair> mapToList(Map<String,
			 * String> map) { LinkedList<NameValuePair> list = new
			 * LinkedList<NameValuePair>(); Iterator iter =
			 * map.entrySet().iterator(); while (iter.hasNext()) { Map.Entry
			 * entry = (Map.Entry) iter.next(); list.add(new
			 * BasicNameValuePair(entry.getKey().toString(),
			 * entry.getValue().toString())); } return list; }
			 */

}
