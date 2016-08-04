/**
 * 
 */
package com.fph.appnavigationlib.download;

import com.fph.appnavigationlib.R;
import com.fph.appnavigationlib.bean.WebType;
import com.fph.appnavigationlib.utils.ApplicationUtil;
import com.fph.appnavigationlib.utils.GsonUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * com.fph.DownLoadManager.java
 * 
 * Created by wang on 2016年7月1日下午3:09:37
 * 
 * Tips:
 */
@SuppressWarnings({ "rawtypes", "unused" })
public class DownLoadManager implements DownLoadStateChangeListener {

	private DownLoadManager() {
		init();
	}

	private final static class Holder {
		final static DownLoadManager INSTANCE = new DownLoadManager();
	}

	public static DownLoadManager getInstance() {
		return Holder.INSTANCE;
	}

	private ExecutorService executorService;

	private Map<Object, DownLoadTask> currTaskMap;

	private Map<Object, Future> futureMap;

	private List<WebType> webType;
	
	private ORMFactory ormFactory;

	private void init() {
		
		executorService = Executors.newFixedThreadPool(DownLoadCallConfig.getInstance().getPoplSize());
		currTaskMap = new HashMap<>();
		futureMap = new HashMap<>();
		webType = GsonUtil.fromJson(ApplicationUtil.getApplicationContext().getResources().getString(R.string.webtype),
				new TypeToken<List<WebType>>() {
				}.getType());
		if (webType == null) {
			webType = new ArrayList<>();
		}
		try {
			ormFactory = DownLoadCallConfig.getInstance().getOrmFactory().newInstance();
		} catch (Exception e) {
		}
	}

	public static void addDownLoadUrl(String url, Object object, DownLoadCallBack downLoadCallBack) {
		addDownLoadUrl(url, "", object, downLoadCallBack);
	}

	public static void addDownLoadUrl(String url, String fileName, Object object, DownLoadCallBack downLoadCallBack) {
		addDownLoadUrl(url, DownLoadCallConfig.getInstance().getFilePath(), fileName, object, downLoadCallBack);
	}

	public static void addDownLoadUrl(String url, String filePath, String fileName, Object object,
									  DownLoadCallBack downLoadCallBack) {

		if (getInstance().getOrmFactory() != null
				&& getInstance().getOrmFactory().getDownLoadEntiys(false).contains(object)) {
			return;
		}
		if (getInstance().currTaskMap.containsKey(object)
				/*|| getInstance().currTaskMap.get(object).getDownLoadState() != DownLoadState.INIT*/) {
			return;
		}
		getInstance().currTaskMap.put(object,
				getInstance().createTask(url, filePath, fileName, object, downLoadCallBack));
		getInstance().futureMap.put(object,
				getInstance().executorService.submit(DownLoadManager.getInstance().currTaskMap.get(object)));
		if (getInstance().getOrmFactory() != null) {
			getInstance().getOrmFactory().create(getInstance().getCurrTaskMap().get(object).getDownLoadInfo());
			return;
		}
	}

	private DownLoadTask createTask(String url, String filePath, String fileName, Object object,
									DownLoadCallBack downLoadCallBack) {
		DownLoadEntiy entiy = new DownLoadEntiy();
		entiy.setFileName(fileName);
		entiy.setFilePath(filePath);
		entiy.setTag(object);
		entiy.setUrl(url);
		try {
			return new DownLoadTask().setDownLoadInfo(entiy).addDownLoadCallBack(downLoadCallBack)
					.setBuilderFactory(DownLoadCallConfig.getInstance().getBuilderFactory().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static  void addDownLoadCallBack(Object object, DownLoadCallBack downLoadCallBack) {
		if (!getInstance().currTaskMap.containsKey(object) || downLoadCallBack == null) {
			return;
		}
		getInstance().currTaskMap.get(object).addDownLoadCallBack(downLoadCallBack);
	}

	public static  void removeDownLoadCallBack(Object object, DownLoadCallBack downLoadCallBack) {
		if (!getInstance().currTaskMap.containsKey(object)) {
			return;
		}
		getInstance().currTaskMap.get(object).removeDownLoadCallBack(downLoadCallBack);
	}

	@Override
	public void onCancel(Object object) {
		if (!currTaskMap.containsKey(object)) {
			return;
		}
		currTaskMap.get(object).onCancel(object);
		currTaskMap.remove(object);
		futureMap.remove(object);
	}

	@Override
	public void onPause(Object object) {
		if (!currTaskMap.containsKey(object)) {
			return;
		}
		if (currTaskMap.get(object).getDownLoadState() == DownLoadState.PAUSE) {
			return;
		}
		currTaskMap.get(object).onPause(object);
		futureMap.remove(object);
	}

	@Override
	public void onResume(Object object) {
		if (!currTaskMap.containsKey(object)) {
			return;
		}
		if (currTaskMap.get(object).getDownLoadState() == DownLoadState.DOWNDING) {
			return;
		}
		currTaskMap.get(object).onResume(object);
		futureMap.put(object, executorService.submit(currTaskMap.get(object)));
	}

	public static List<DownLoadEntiy> getDownLoadEntiyfromDB(boolean isDownLoaded) {
		if (getInstance().getOrmFactory() != null) {
			return getInstance().getOrmFactory().getDownLoadEntiys(isDownLoaded);
		}
		return new ArrayList<>();
	}

	public static void addDBTaskToCurrTask(boolean isDownLoaded){
		for (DownLoadTask iterable_element : getDownLoadTaskfromDB(isDownLoaded)) {
			getInstance().getCurrTaskMap().put(iterable_element.getDownLoadInfo().getTag(), iterable_element);
		}
	}
	
	public static List<DownLoadTask> getDownLoadTaskfromDB(boolean isDownLoaded) {
			List<DownLoadTask> list = new ArrayList<>();

			for (DownLoadEntiy downLoadEntiy : getInstance().getOrmFactory().getDownLoadEntiys(isDownLoaded)) {
				try {
					list.add(new DownLoadTask().setDownLoadInfo(downLoadEntiy).addDownLoadCallBack(null)
						.setBuilderFactory(DownLoadCallConfig.getInstance().getBuilderFactory().newInstance()));
				} catch (Exception e) {
				}
			}
		return list;
	}

	/**
	 * @return the executorService
	 */
	public ExecutorService getExecutorService() {
		return executorService;
	}

	/**
	 * @param executorService
	 *            the executorService to set
	 */
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	/**
	 * @return the currTaskMap
	 */
	public Map<Object, DownLoadTask> getCurrTaskMap() {
		return currTaskMap;
	}

	/**
	 * @param currTaskMap
	 *            the currTaskMap to set
	 */
	public void setCurrTaskMap(Map<Object, DownLoadTask> currTaskMap) {
		this.currTaskMap = currTaskMap;
	}

	/**
	 * @return the futureMap
	 */
	public Map<Object, Future> getFutureMap() {
		return futureMap;
	}

	/**
	 * @param futureMap
	 *            the futureMap to set
	 */
	public void setFutureMap(Map<Object, Future> futureMap) {
		this.futureMap = futureMap;
	}

	/**
	 * @return the webType
	 */
	public List<WebType> getWebType() {
		return webType;
	}

	/**
	 * @param webType
	 *            the webType to set
	 */
	public void setWebType(List<WebType> webType) {
		this.webType = webType;
	}

	/**
	 * @return the ormFactory
	 */
	public ORMFactory getOrmFactory() {
		return ormFactory;
	}

	/**
	 * @param ormFactory
	 *            the ormFactory to set
	 */
	public void setOrmFactory(ORMFactory ormFactory) {
		this.ormFactory = ormFactory;
	}

}
