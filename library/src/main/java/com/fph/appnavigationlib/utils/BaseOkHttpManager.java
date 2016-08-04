package com.fph.appnavigationlib.utils;///**
// * 
// */
//package com.fph.appnavigationlib.utils;
//
///**
// *
// * com.fph.appnavigationlib.utils.BaseOkHttpManager.java
// * 
// * Created by wang on 2016上午10:48:48 
// * 
// * Tips:
// */
//public class BaseOkHttpManager<T> {
//
//	
//	/**
//	 * 
//	 */
//	package com.fph.appnavigationlib.utils;
//
//	import java.util.Iterator;
//	import java.util.LinkedList;
//	import java.util.Map;
//	import org.apache.http.NameValuePair;
//	import org.apache.http.message.BasicNameValuePair;
//
//	import com.fph.RootInfo;
//	import com.fph.HttpStatusConts;
//	import com.fph.appnavigationlib.imp.BaseRootInfoCallBack;
//	import com.fph.appnavigationlib.imp.BaseStringCallBack;
//	import com.fph.appnavigationlib.imp.RootInfoCallBack;
//	import com.zhy.http.okhttp.OkHttpUtils;
//	import com.zhy.http.okhttp.callback.Callback;
//
//	import android.os.Handler;
//
//	/**
//	 *
//	 * com.fph.OkHttpManager.java
//	 * 
//	 * Created by wang on 2016下午2:44:24 
//	 * 
//	 * Tips:
//	 */
//	public class OkHttpManager {
//
//
//		/**
//		 * post解析工具 带handler (what)
//		 *
//		 * @param url
//		 * @param list
//		 * @param context
//		 * @param handler
//		 * @param what
//		 */
//		public static void post(final String url, final Map<String, String> list,
//				final Handler handler, final int what) {
//			post(url, list, handler, what, false);
//		}
//
//		/**
//		 * post解析工具 带handler (what) isCookie
//		 * 
//		 * @param url
//		 * @param map
//		 * @param context
//		 * @param handler
//		 * @param what
//		 * @param isCookie
//		 */
//		public static void post(final String url, final Map<String, String> map, 
//				final Handler handler, final int what, final boolean isCookie) {
//			httppost(url, map, handler, what, isCookie);
//		}
//
//		/**
//		 * post解析工具 带handler
//		 * 
//		 * @param url
//		 * @param map
//		 * @param context
//		 * @param handler
//		 */
//		public static void post(final String url, final Map<String, String> map,final Handler handler) {
//			post(url, map, handler, HttpStatusConts.SUC, false);
//		}
//
//		/**
//		 * post解析工具 带handler
//		 * 
//		 * @param url
//		 * @param params
//		 * @param context
//		 * @param handler
//		 */
//		public static void post(final String url, final Map<String, String> params,
//				final Handler handler, final boolean isCookie) {
//			post(url, params, handler, HttpStatusConts.SUC, isCookie);
//		}
//
//		public static void httppost(String url, final Map<String, String> params, 
//				final Handler handler, final int what, final boolean isCookie) {
//
//		sendHttp(url, params, handler, what, isCookie, "post");
//		}
//		
//		public static void get(String url, final Handler handler, final int what) {
//
//		sendHttp(url, null, handler, what, false, "get");
//
//		}
//		
//
//		public static void sendHttp(String url, final Map<String, String> params, 
//				final Handler handler, final int what, final boolean isCookie,String type,BaseRootInfoCallBack baseRootInfoCallBack){
//			try {
//				if (type.equals("get")) {
//					OkHttpUtils.get().url(url).tag(url).build().execute(baseRootInfoCallBack);
//				}else if (type.equals("post")) {
//					OkHttpUtils.post().url(url).tag(url).params(params).build().execute(baseRootInfoCallBack);
//
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				Utils.Log(e.getMessage());
//				
//				//特殊异常 伪装成服务器异常
//				RootInfo rootInfo =new RootInfo();
//				rootInfo.setServerCode(0);
//				rootInfo.setMsg(e.getMessage());
//				rootInfo.setWhat(what);
//				handler.sendMessage(handler.obtainMessage(HttpStatusConts.ERROR, rootInfo));
//			}
//		}
//		
//
//
//
//		public static void killByTag(Object tag) {
//			OkHttpUtils.getInstance().cancelTag(tag);
//		}
//
//
//		public static LinkedList<NameValuePair> mapToList(Map<String, String> map) {
//			LinkedList<NameValuePair> list = new LinkedList<NameValuePair>();
//			Iterator iter = map.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry entry = (Map.Entry) iter.next();
//				list.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
//			}
//			return list;
//		}	
//		
//		
//		
//		
//	}
//
//	
//}
