/**
 * 
 */
package com.fph.appnavigationlib.http;

import com.fph.appnavigationlib.http.callback.BuilderCallFactory;
import com.fph.appnavigationlib.http.http.BaseUrl;
import com.fph.appnavigationlib.http.http.Body;
import com.fph.appnavigationlib.http.http.Encrypt;
import com.fph.appnavigationlib.http.http.File;
import com.fph.appnavigationlib.http.http.GET;
import com.fph.appnavigationlib.http.http.Header;
import com.fph.appnavigationlib.http.http.Multipart;
import com.fph.appnavigationlib.http.http.OldToken;
import com.fph.appnavigationlib.http.http.Old_Token;
import com.fph.appnavigationlib.http.http.POST;
import com.fph.appnavigationlib.http.http.Query;
import com.fph.appnavigationlib.http.http.Tag;
import com.fph.appnavigationlib.http.http.Token;
import com.fph.appnavigationlib.http.http.URL;
import com.fph.appnavigationlib.http.http.URL2;
import com.fph.appnavigationlib.http.http.URLH5;
import com.fph.appnavigationlib.http.http.URLS;
import com.fph.appnavigationlib.http.http.UnEncrypt;
import com.fph.appnavigationlib.http.http._Token;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * com.fph.HttpImp.java
 * 
 * Created by wang on 2016年6月24日上午10:34:54 
 * 
 * Tips:
 */
public class HttpImp {

	private HttpImp httpImp;
	
	private HttpImp(){}
	
	private final static class Holder {
		private final static HttpImp INSTANCE = new HttpImp();
	}
	
	public static HttpImp getInstance(){
		return Holder.INSTANCE;
	}
	
	public Class<? extends BuilderCallFactory> builderDefaultCallFactory;

	public Map<Class, Object> serviceMap= new HashMap<Class, Object>();
	
	
	public static <T> T with(Class<T> service){
		return  with(service,getInstance().builderDefaultCallFactory);
	} 
	

	@SuppressWarnings("unchecked")
	public static <T> T with(Class<T> service, Class<? extends BuilderCallFactory> builder){
		if (getInstance().serviceMap == null) {
			getInstance().serviceMap = new HashMap<Class, Object>();
		}
		if (!getInstance().serviceMap.containsKey(service) || getInstance().serviceMap.get(service)== null) {
			getInstance().serviceMap.put(service, getInstance().create(service, builder));
		}
	return (T) getInstance().serviceMap.get(service);
	} 
	
	
	@SuppressWarnings("unchecked")
	private <T> T create(final Class<T> service , final Class<? extends BuilderCallFactory> builder){
		
		
		return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				if (builder == null) throw new NullPointerException("BuilderCallFactory is null!");
				
				BuilderCallFactory builderCallFactory =builder.newInstance();
				
				HttpRequest httpRequest = builderUrl(service, method , builderCallFactory);
				
				builderParameters(builderCallFactory,httpRequest,method.getParameterAnnotations(), args);
				return httpRequest;
			}


			private void builderParameters(BuilderCallFactory callBack, HttpRequest httpRequest,
										   Annotation[][] annotationArray, Object[] args) {
				// TODO Auto-generated method stub
				  for (int i = 0; i < annotationArray.length; i++) {
			            for (Annotation a : annotationArray[i]) {
			                if (a instanceof Query) {
			                    callBack.addQuery(((Query) a).value(),toString(args[i]));
			                }else if (a instanceof Body) {
			                    callBack.addBody(((Body) a).value(),toString(args[i]));
			                }else if (a instanceof Header){
			                    callBack.addHeader(((Header) a).value(),toString(args[i]));
			                }else if(a instanceof File){
			                	if (httpRequest.getHttpUrl().isMulit()) {
			                		callBack.addFile(((File)a).value(), (List) args[i]);
								}else
			                	callBack.addFile(((File)a).value(), toString(args[i]));
			                }/*else if (a instanceof Json){
			                    httpRequest.setJson(toString(args[i]));
			                }*/
			            }
			        }
			}
			private String toString(Object object) {
				// TODO Auto-generated method stub
				if (object == null) {
					return "";
				}
				return object.toString();
			}
			private HttpRequest builderUrl(Class service, Method method, BuilderCallFactory builderCallFactory) {
				// TODO Auto-generated method stub
				
				HttpRequest httpRequest =new HttpRequest();
				
				HttpUrl httpUrl =new HttpUrl();
				
				httpRequest.setHttpUrl(httpUrl);
				
				Annotation baseUrl = service.getAnnotation(BaseUrl.class);
				
				if (baseUrl !=null && baseUrl instanceof BaseUrl) {
					httpUrl.setBaseUrl(((BaseUrl)baseUrl).value());
				}
				
				if (method.getAnnotation(GET.class) != null) {
					GET get=method.getAnnotation(GET.class);
					httpUrl.setHttpMethod(HttpMethod.GET);
					httpUrl.setPath(get.path());
				}
				
				if (method.getAnnotation(POST.class) != null) {
					POST post = method.getAnnotation(POST.class);
					httpUrl.setHttpMethod(HttpMethod.POST);
//					httpUrl.setMethod(post.method());
					httpUrl.setPath(post.value());
				}
				
				if (method.getAnnotation(Multipart.class)!=null) {
					httpUrl.setMulit(true);
				}
				
				if (method.getAnnotation(UnEncrypt.class) !=null) {
					httpUrl.setEncrypt(false);
				}
				if (method.getAnnotation(Encrypt.class) !=null) {
					httpUrl.setEncrypt(true);
				}
				if (method.getAnnotation(Tag.class)!=null) {
					Tag tag =method.getAnnotation(Tag.class);
					httpUrl.setRequestTag(tag.value());
				}
				//-----
				if (method.getAnnotation(Token.class)!=null) {
					httpUrl.setToken(true);
				}
				if (method.getAnnotation(_Token.class)!=null) {
					httpUrl.set_token(true);
				}
				if (method.getAnnotation(OldToken.class)!=null) {
					httpUrl.setOldToken(true);
				}
				if (method.getAnnotation(Old_Token.class)!=null) {
					httpUrl.setOld_Token(true);
				}
				if (method.getAnnotation(URL.class)!=null) {
					httpUrl.setUrl(true);
				}
				if (method.getAnnotation(URL2.class)!=null) {
					httpUrl.setUrl2(true);
				}
				if (method.getAnnotation(URLS.class)!=null) {
					httpUrl.setUrls(true);
				}
				if (method.getAnnotation(URLH5.class)!=null) {
					httpUrl.setUrlh5(true);
				}
				//----
				
				httpRequest.setBuildCallFactory(builderCallFactory);
				builderCallFactory.addUrl(httpUrl);
				return httpRequest;
			}
		} );
		
	}


	
	
	/**
	 * @return the builderDefaultCallFactory
	 */
	public Class<? extends BuilderCallFactory> getBuilderDefaultCallFactory() {
		return builderDefaultCallFactory;
	}


	/**
	 * @param builderDefaultCallFactory the builderDefaultCallFactory to set
	 */
	public void setBuilderDefaultCallFactory(Class<? extends BuilderCallFactory> builderDefaultCallFactory) {
		this.builderDefaultCallFactory = builderDefaultCallFactory;
	}

	
	
	
}
