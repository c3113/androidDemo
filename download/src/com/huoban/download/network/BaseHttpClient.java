package com.huoban.download.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** hilary Create on @2013-11-6. */
abstract class BaseHttpClient implements Runnable {

	private HttpURLConnection conn;

	// 连接超时时间
	private int mConnectTimeout = 10000;

	private String mUrl;

	@Override
	public void run() {
		URL url;
		try {
			System.out.println("**httpClient_url****" + mUrl);
			url = new URL(mUrl);
			if (conn != null) {
				conn.disconnect();
			}
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(mConnectTimeout);
			conn.setRequestMethod(getRequestMethod().name());
			conn.setUseCaches(false);
			StringBuilder cookieBuilder = new StringBuilder();

			if (getRequestMethod() == RequestMethod.POST) {
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			}
			conn.addRequestProperty("Cookie", cookieBuilder.toString());
			conn.connect();

			postResponse();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			failed(e);
		}
	}

	// 处理请求结果并分发给相应的方法处理
	private void postResponse() {
		int responseCode = -1;
		try {
			responseCode = conn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (responseCode == 200) {
			try {
				succeed(conn.getInputStream());
			} catch (IOException e) {
				failed(e);
				e.printStackTrace();
			}
		} else if (responseCode == 304) {
			try {
				succeed(conn.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			failed(new Exception());
		}
	}

	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	/**
	 * 接收返回结果
	 * 
	 * @param input
	 */
	abstract void succeed(InputStream input);

	/**
	 * 请求异常处理方法
	 * 
	 * @param e
	 */
	abstract void failed(Exception e);

	/**
	 * 获得请求方法
	 * 
	 * @return
	 */
	abstract RequestMethod getRequestMethod();

	public enum RequestMethod {
		POST, GET, PUT, DELETE;
	}
}
