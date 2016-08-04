package com.fph.appnavigationlib.utils;

import com.fph.appnavigationlib.bean.RootInfo;
import com.google.gson.Gson;
import com.socks.library.KLog;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by wang on 2015/4/7 0007.
 */
public class GsonUtil {


	private GsonUtil() {
	}

	private static class Holder {
		public final static Gson gson =new Gson();  /*new GsonBuilder().  
                registerTypeAdapter(double.class, new JsonSerializer<Double>() {  
                    @Override  
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {  
                        if (src != null && src == src.longValue())  
                            return new JsonPrimitive(src.longValue());  
                        return new JsonPrimitive(src);  
                    }  
                }).create();*/
	}

	public static Gson getInstance() {
		return Holder.gson;
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		try {
			return getInstance().fromJson(json, classOfT);
		} catch (Exception e) {
			Log(e.getMessage());
			if (!classOfT.getName().equals(RootInfo.class.getName())) {
				Utils.showToast("解析异常");
			}
		}
		return null;
	}

	public static void Log(String string) {
		KLog.e(string);
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		try {
			return getInstance().fromJson(json, typeOfT);
		} catch (Exception e) {
			Log(e.getMessage());
			Utils.showToast("解析异常");
		}
		return null;
	}

	public static <T> T fromJson(Reader json, Class<T> classOfT) {
		try {
			return getInstance().fromJson(json, classOfT);
		} catch (Exception e) {
			Log(e.getMessage());
			if (!classOfT.getName().equals(RootInfo.class.getName())) {
				Utils.showToast("解析异常");
			}
		}
		return null;
	}

	public static <T> T fromJson(Reader json, Type typeOfT) {
		try {
			return getInstance().fromJson(json, typeOfT);
		} catch (Exception e) {
			Log(e.getMessage());
			Utils.showToast("解析异常");
		}
		return null;
	}

	public static String toJson(Object src) {
		return getInstance().toJson(src);
	}

}
