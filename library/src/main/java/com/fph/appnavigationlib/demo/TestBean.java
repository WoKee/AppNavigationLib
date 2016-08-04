/**
 * 
 */
package com.fph.appnavigationlib.demo;

import com.fph.appnavigationlib.bean.ReturnData;
import com.fph.appnavigationlib.bean.ReturnObject;
import com.fph.appnavigationlib.bean.RootInfo;

import java.util.List;

/**
 *
 * com.fph.TestBean.java
 * 
 * Created by wang on 2016年7月12日下午3:57:51 
 * 
 *  bean定义示例
 */
public class TestBean {
	
/**
 * 	ReturnValue如果只是一个字符串或者数字 不需要定义bean 直接rootinfo.getReturnValue()  
 * 	如果是int 或者double  用utils.parseInt("xxx")/parseDouble("xxx")进行转换,防止java自带的格式化出现异常;
 * 	如果是数组或者是对象 根据示例一样定义 集成 ReturnValue类
 */
	RootInfo rootInfo /*= msg.obj*/;
	
	//Tips  取returnObject 为数组
	
	/**
	 *{"serverCode":1,"errorCode":200,"status":200,"msgType":1,
	 * "msg":"OK","returnValue":1,"returnObject":[]}
	 */
	public static class ObjectBean extends ReturnObject<List<ObjectBean>> {
		private String teString;
	}
	List<ObjectBean> ObjectBeanList = rootInfo.convert(ObjectBean.class).getReturnObject();

	//Tips  取returnObject 为对象
	/**
	 *{"serverCode":1,"errorCode":200,"status":200,"msgType":1,
	 * "msg":"OK","returnValue":1,"returnObject":{}}
	 */
	public class ObjectBean1 extends ReturnObject<ObjectBean1> {
		private String teString;
	}
	ObjectBean1 ObjectBean1 =rootInfo.convert(ObjectBean1.class).getReturnObject();

	
	//Tips  取data 为对象
	/**
	 *{"serverCode":1,"errorCode":200,"status":200,"msgType":1,"data":{}
	 * "msg":"OK","returnValue":1,"returnObject":{}}
	 */
	public class DataBean extends ReturnData<DataBean> {
		private String teString;
	}
	DataBean DataBean = rootInfo.convert(DataBean.class).getData();
	
	//Tips  取data 为数组
	/**
	 *{"serverCode":1,"errorCode":200,"status":200,"msgType":1,"data":[]
	 * "msg":"OK","returnValue":1,"returnObject":{}}
	 */
	public class DataBean1 extends ReturnData<List<DataBean1>> {
		private String teString;
	}
	List<DataBean1> list =rootInfo.convert(DataBean1.class).getData();
	

	
}
