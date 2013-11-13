package com.huoban.download.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** hilary Create on @2013-11-7. */
public class DownLoadHttpClient extends BaseHttpClient {

	private String filePath;

	private OnDownloadListener listener;

	private int downloadSize = 0;
	private int fileSize = 0;
	
	@Override
	void succeed(InputStream input) {
		createPath(filePath);
		File file = new File(filePath);
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			byte buffer[] = new byte[1024];
			int len = 0;

			while ((len = input.read(buffer)) != -1) {
				downloadSize += len;
				 output.write(buffer, 0, len);
				 listener.onProgress(downloadSize, fileSize);
			}
			listener.downloadSucceed(getmUrl(), filePath);
			output.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			listener.downloadFileError(e);
		} 
	}

	@Override
	void failed(Exception e) {
		listener.downloadFileError(e);
	}

	@Override
	RequestMethod getRequestMethod() {
		return RequestMethod.GET;
	}

	public void setOnDownloadListener(OnDownloadListener listener) {
		this.listener = listener;
	}

	/**
	 * Download progress listener
	 * 
	 * @author hilary
	 * 
	 */
	public interface OnDownloadListener {
		/**
		 * Download after a successful call
		 * @param url	
		 * @param path  The file path
		 */
		public void downloadSucceed(String url, String path);
		/**
		 * show downloadProgress
		 * @param downloadSize	The total number of downloads
		 * @param totalSize		file size
		 */
		public void onProgress(int downloadSize, int totalSize);

		/**
		 * Download faild call
		 * @param e
		 */
		public void downloadFileError(Exception e);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 创建文件路径
	 * @param pathName
	 */
	public static void createPath(String pathName){
		String path = pathName.substring(0, pathName.lastIndexOf("/"));
		File file = new File(path);
		if(!file.exists() ||!file.isDirectory()){
			file.mkdirs();
		}
	}
}
