/**
 * 
 */
package com.fph.appnavigationlib.download;

import android.os.Handler;

import com.fph.appnavigationlib.bean.WebType;
import com.fph.appnavigationlib.utils.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * com.fph.DownLoadTask.java
 * 
 * Created by wang on 2016年7月1日下午3:09:56
 * 
 * Tips:
 */
public class DownLoadTask implements Runnable, DownLoadStateChangeListener, DownLoadCallBack {

	private DownLoadEntiy downLoadInfo;

	private Handler handler = new Handler();
	private List<DownLoadCallBack> downLoadCallBacks = new ArrayList<>();

	private RandomAccessFile file;

	private long completedSize, totalSize;

	DownLoadBuilderFactory builderFactory;

	private long time;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		onPrepare(downLoadInfo.getTag());
		InputStream inputStream = null;
		BufferedInputStream bis = null;
		try {
			if (downLoadInfo.isDownLoading()) {
				file = new RandomAccessFile(downLoadInfo.getFilePath() + downLoadInfo.getFileName(), "rwd");
				completedSize = downLoadInfo.getCompleteSize();
				totalSize = downLoadInfo.getTotalSize();
				if (file != null) {
					if (file.length() != completedSize) {// 重新计算
						completedSize = file.length();
					}
					if (file.length() != 0 && totalSize != 0&&totalSize <= file.length()) {
						onSuccess(downLoadInfo.getTag(),
								new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()));
						return;
					}
				} else {// 文件丢失 重置
					completedSize = 0;
					totalSize = 0;
					downLoadInfo.setCompleteSize(completedSize);
					downLoadInfo.setTotalSize(0);
					downLoadInfo.setDownLoading(false);
				}

			}else{
				if (new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()).isFile()) {
					new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()).delete();
				}
			}
			onStart(downLoadInfo.getTag());

			if (builderFactory == null) {
				onError(downLoadInfo.getTag(), "buildfactory is null ", -1);
				return;
			}
			DownLoadResponse downLoadResponse = builderFactory.create(downLoadInfo.getUrl(), completedSize,
					downLoadInfo.getTag());

			if (downLoadResponse != null) {
				if (downLoadResponse.getErrorCode() != HttpURLConnection.HTTP_PARTIAL
						&& downLoadResponse.getErrorCode() != HttpURLConnection.HTTP_OK) {
					onError(downLoadInfo.getTag(), downLoadResponse.getErrorMsg(), -1);
					return;
				}
				downLoadInfo.setDownLoading(true);
				if (downLoadResponse.getErrorCode() != HttpURLConnection.HTTP_PARTIAL) {
					if (file != null) {
						if (new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()).isFile()) {
							new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()).delete();
						}
						file = null ;
						completedSize = 0;
					}
				}
				if (file == null) {
					downLoadInfo.setFileName(getFileName(downLoadResponse));
					file = new RandomAccessFile(downLoadInfo.getFilePath() + downLoadInfo.getFileName(), "rwd");
				}
				file.seek((long) completedSize);
				onStart(downLoadInfo.getTag());
				if (totalSize <= 0) {
					totalSize = downLoadResponse.getContentLenth();
				}
				downLoadInfo.setTotalSize(totalSize);
				inputStream = downLoadResponse.getInputStream();
				bis = new BufferedInputStream(inputStream);
				byte[] buffer = new byte[2 * 1024];
				int length = 0;
				int buffOffset = 0;// 用于记录数据库
				while ((length = bis.read(buffer)) > 0 && downLoadInfo.getDownLoadState() != DownLoadState.CANCEL
						&& downLoadInfo.getDownLoadState() != DownLoadState.PAUSE) {
					file.write(buffer, 0, length);
					completedSize += length;
					buffOffset += length;
					downLoadInfo.setCompleteSize(completedSize);
					onDownLoading();
				}
			} else {
				onError(downLoadInfo.getTag(), "downLoadResponse is null ", -1);
				return;
			}

		} catch (FileNotFoundException e) {
			onError(downLoadInfo.getTag(), e.getMessage(), -1);
			return;
		} catch (IOException e) {
			onError(downLoadInfo.getTag(), e.getMessage(), -1);
			return;
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (totalSize > 0 && totalSize == completedSize) {
				onSuccess(downLoadInfo.getTag(), new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()));
				return;
			}
			switch (downLoadInfo.getDownLoadState()) {
			case SUCCESS:
				onSuccess(downLoadInfo.getTag(), new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()));
				break;
			case PAUSE:
				onPause(downLoadInfo.getTag());
				break;
			case PREPARED:// 准备下载
				onPrepare(downLoadInfo.getTag());
				break;
			case START:// 开始下载
				onStart(downLoadInfo.getTag());
				break;
			case DOWNDING:// 下载中
				onDownLoading();
				break;
			case CANCEL:// 取消下载
				onCancel(downLoadInfo.getTag());
				break;
			case RESUME:// 恢复下载
				onResume(downLoadInfo.getTag());
				break;
			case FAIL:// 下载失败
				onError(downLoadInfo.getTag(), "下载失败", -1);
				break;
			default:
				break;
			}
		}
	}

	public void onDownLoading() {
		onDownLoadProgress(completedSize, totalSize, downLoadInfo.getTag());
	}

	/**
	 * @return the downLoadTask
	 */
	public List<DownLoadCallBack> getDownLoadCallBacks() {
		return downLoadCallBacks;
	}

	/**
	 * @return the builderFactory
	 */
	public DownLoadBuilderFactory getBuilderFactory() {
		return builderFactory;
	}

	/**
	 * @param builderFactory
	 *            the builderFactory to set
	 */
	public DownLoadTask setBuilderFactory(DownLoadBuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
		return this;
	}

	/**
	 * @param downLoadTask
	 *            the downLoadTask to set
	 */
	public DownLoadTask addDownLoadCallBack(DownLoadCallBack downLoadCallBack) {
		if (downLoadCallBacks == null) {
			downLoadCallBacks = new ArrayList<>();
		}
		downLoadCallBacks.add(downLoadCallBack);
		return this;
	}

	public void removeDownLoadCallBack(DownLoadCallBack downLoadCallBack) {
		if (downLoadCallBack == null) {
			downLoadCallBacks.clear();
		}
		if (!downLoadCallBacks.contains(downLoadCallBack)) {
			return;
		}
		downLoadCallBacks.remove(downLoadCallBack);
	}

	/**
	 * @return the downLoadInfo
	 */
	public DownLoadEntiy getDownLoadInfo() {
		return downLoadInfo;
	}

	/**
	 * @param downLoadInfo
	 *            the downLoadInfo to set
	 */
	public DownLoadTask setDownLoadInfo(DownLoadEntiy downLoadInfo) {
		this.downLoadInfo = downLoadInfo;
		return this;
	}

	/**
	 * @return the downLoadState
	 */
	public DownLoadState getDownLoadState() {
		return downLoadInfo.getDownLoadState();
	}

	/**
	 * @param downLoadState
	 *            the downLoadState to set
	 */
	public DownLoadTask setDownLoadState(DownLoadState downLoadState) {
		downLoadInfo.setDownLoadState(downLoadState);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadStateChangeListener#
	 * onResume(java.lang.Object)
	 */
	@Override
	public void onResume(final Object tag) {
		// TODO Auto-generated method stub
		downLoadInfo.setDownLoadState(DownLoadState.RESUME);
		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onResume(tag);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadStateChangeListener#
	 * onPause(java.lang.Object)
	 */
	@Override
	public void onPause(final Object tag) {
		// TODO Auto-generated method stub
		// downLoadState = DownLoadState.PAUSE;
		downLoadInfo.setDownLoadState(DownLoadState.PAUSE);
		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
			for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onPause(tag);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadStateChangeListener#
	 * onCancel(java.lang.Object)
	 */
	@Override
	public void onCancel(final Object tag) {
		// TODO Auto-generated method stub
		downLoadInfo.setDownLoadState(DownLoadState.CANCEL);

		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().delete(downLoadInfo);
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onCancel(tag);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadCallBack#onPrepare(
	 * java.lang.Object)
	 */
	@Override
	public void onPrepare(final Object tag) {
		// TODO Auto-generated method stub
		// downLoadState = DownLoadState.PREPARED;
		downLoadInfo.setDownLoadState(DownLoadState.PREPARED);

			if (DownLoadManager.getInstance().getOrmFactory() != null)
				DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onPrepare(tag);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadCallBack#onSuccess(
	 * java.lang.Object, java.io.File)
	 */
	@Override
	public void onSuccess(final Object tag, File file) {
		// TODO Auto-generated method stub
		// downLoadState = DownLoadState.SUCCESS;
		downLoadInfo.setDownLoadState(DownLoadState.SUCCESS);

		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onSuccess(tag, new File(downLoadInfo.getFilePath() + downLoadInfo.getFileName()));
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadCallBack#onStart(java.
	 * lang.Object)
	 */
	@Override
	public void onStart(final Object tag) {
		// TODO Auto-generated method stub
		// downLoadState = DownLoadState.START;
		downLoadInfo.setDownLoadState(DownLoadState.START);

		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		time = System.currentTimeMillis();
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onStart(tag);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadCallBack#onError(java.
	 * lang.Object, java.lang.String, int)
	 */
	@Override
	public void onError(final Object tag, final String mString, final int errorCode) {
		// TODO Auto-generated method stub
		// downLoadState = DownLoadState.FAIL;
		downLoadInfo.setDownLoadState(DownLoadState.FAIL);

		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onError(tag, mString, errorCode);
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fph.DownLoadCallBack#
	 * onDownLoadProgress(double, double, java.lang.Object)
	 */
	@Override
	public void onDownLoadProgress(final double completeSize, final double totalSize, final Object tag) {
		// TODO Auto-generated method stub
		if (downLoadInfo.getDownLoadState() == DownLoadState.CANCEL
				|| downLoadInfo.getDownLoadState() == DownLoadState.PAUSE) {
			return;
		}
		downLoadInfo.setDownLoadState(DownLoadState.DOWNDING);
		// downLoadState = DownLoadState.DOWNDING;
		if (DownLoadManager.getInstance().getOrmFactory() != null)
			DownLoadManager.getInstance().getOrmFactory().update(downLoadInfo);
		if (System.currentTimeMillis() - time < DownLoadCallConfig.getInstance().getUpdateCurrTime()) {
			
			return;
		}
		time = System.currentTimeMillis();
		for (final DownLoadCallBack downLoadCallBack : downLoadCallBacks) {
			if (downLoadCallBack == null) {
				downLoadCallBacks.remove(downLoadCallBack);
			}else
			handler.post(new Runnable() {
				public void run() {
					downLoadCallBack.onDownLoadProgress(completeSize, totalSize, tag);
				}
			});
		}
	}

	public String getFileName(final DownLoadResponse downLoadResponse) {

		if (!Utils.isEmpty(downLoadInfo.getFileName())) {
			return downLoadInfo.getFileName();
		}

		/*
		 * //先判断有没有配置文件名 if (Utils.isEmpty(downLoadInfo.getFileName()))
		 */ {
			/*
			 * 先检测网址后缀有没有支持文件名
			 */
			String tempUrl = new String(downLoadInfo.getUrl());// 配置临时url

			String urlFix = tempUrl.substring(tempUrl.lastIndexOf("/") + 1, tempUrl.length());// 后缀是否支持

			if (urlFix.contains(".")) {// 包含. 识别是否是文件名称

				String fix = urlFix.substring(urlFix.lastIndexOf(".") + 1, urlFix.length());

				for (WebType webType : DownLoadManager.getInstance().getWebType()) {

					if (webType.getExtension().equals(fix.toLowerCase().replaceAll(" ", ""))) {
						return urlFix;
					}
				}

			}

			// filename
			if (downLoadResponse.getHeaderMap().containsKey("content-disposition")
					&& downLoadResponse.getHeaderMap().get("content-disposition").size() > 0) {// 是否包含
																								// filename
				for (String tempcp : downLoadResponse.getHeaderMap().get("content-disposition")) {
					if (tempcp.contains("filename")) {
						String fileName = tempcp.substring(tempcp.indexOf("filename=") + 9, tempcp.length());// 截取文件名以后的字符
						if (fileName.indexOf(";") != -1) {
							fileName = fileName.substring(0, fileName.indexOf(";"));
						}
						return fileName;
					}
				}
			}
			// content type
			if (downLoadResponse.getHeaderMap().containsKey("content-type")
					&& downLoadResponse.getHeaderMap().get("content-type").size() > 0) {// 是否包含content
																						// type

				for (WebType webType : DownLoadManager.getInstance().getWebType()) {

					if (webType.getType().equals(downLoadResponse.getHeaderMap().get("content-type").get(0)
							.toLowerCase().replaceAll(" ", ""))) {
						return System.currentTimeMillis() + "." + webType.getExtension();
					}
				}

			}

		}
		// 不符合http协议的网站
		Utils.showToast("不符合http协议的下载链接");
		return "未知文件";
	}

}
