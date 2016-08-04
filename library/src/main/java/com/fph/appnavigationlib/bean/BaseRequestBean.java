/**
 * 
 */
package com.fph.appnavigationlib.bean;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * com.fph.BaseRequestBean.java
 * 
 * Created by wang on 2016年6月14日下午1:18:32 
 * 
 * Tips:
 */
public class BaseRequestBean {

	
	
	private Request1 body;
	
	
	
	/**
	 * @return the body
	 */
	public Request1 getBody() {
		return body;
	}


	/**
	 * @param body the body to set
	 */
	public void setBody(Request1 body) {
		this.body = body;
	}


	public static class Request1 {
		
		private Request2 delegate;

		/**
		 * @return the delegate
		 */
		public Request2 getDelegate() {
			return delegate;
		}

		/**
		 * @param delegate the delegate to set
		 */
		public void setDelegate(Request2 delegate) {
			this.delegate = delegate;
		}
		
		
	}

	
	public static class Request2{
		
		private String[] encodedNames;
		
		private String[] encodedValues;
		
		private IdentityHashMap<String, String> encodeMap ;

		/**
		 * @return the encodedNames
		 */
		public String[] getEncodedNames() {
			return encodedNames;
		}

		/**
		 * @param encodedNames the encodedNames to set
		 */
		public void setEncodedNames(String[] encodedNames) {
			this.encodedNames = encodedNames;
		}

		/**
		 * @return the encodedValues
		 */
		public String[] getEncodedValues() {
			return encodedValues;
		}

		/**
		 * @param encodedValues the encodedValues to set
		 */
		public void setEncodedValues(String[] encodedValues) {
			this.encodedValues = encodedValues;
		}
		
		public String getEncodeMapWithKey(String key){
			
			if (getEncodeMap()!=null && getEncodeMap().size()>0) {
				
				Iterator iterator = getEncodeMap().entrySet().iterator();
		        while(iterator.hasNext()){  
		            Map.Entry entry = (Map.Entry)iterator.next();
		            if (key.equals(entry.getKey())) {
						return entry.getValue().toString();
					}
//		            System.out.println("key: "+entry.getKey()+"  vlaue: "+entry.getValue());  
		        }  
				
			}
			
			return "";
		}
		

		/**
		 * @return the encodeMap
		 */
		public IdentityHashMap<String, String> getEncodeMap() {
			
			if (encodedNames == null || encodedValues == null) {
				
				return new IdentityHashMap<String, String>();
			}
			
			if (encodedNames.length == 0 ||encodedNames.length != encodedValues.length) {
				
				return new IdentityHashMap<String, String>();
			}
			
			if (encodeMap ==null || encodeMap .size() == 0) {
				
				
				encodeMap =new IdentityHashMap<String, String>();
				
				for (int i = 0; i < encodedNames.length; i++) {
					
					encodeMap.put(new String(encodedNames[i]),encodedValues[i]);
					
				}
				
			}
			
			return encodeMap;
		}

	}
	
	
}
