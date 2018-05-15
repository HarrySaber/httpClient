package cn.starpost.tms.client.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

	static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doPost(String url, String json) {
		return doPost(url, json, null);
	}

	public static String doPost(String url, String json, Map<String, String> header) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			if (!StringUtils.isEmpty(json)) {
				StringEntity entity = new StringEntity(json, "UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			if (header != null) {
				for (String key : header.keySet()) {
					httpPost.setHeader(key, header.get(key));
				}
			}
			response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				logger.error("http post error status [ " + status + "]");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("http post Exception [ " + ex + "]");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("http post IOException [ " + e + "]");
			}
		}
		return resData;
	}

	public static String doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resData = null;
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response = httpclient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity());
			} else {
				logger.error("http doGet error status [ " + status + "]");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("http doGet Exception [ " + ex + "]");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("http doGet IOException [ " + e + "]");
			}
		}
		return resData;
	}

	public static String doPut(String url, String json) {
		CloseableHttpClient httpclient = null;
		String resData = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut(url);
			if (!StringUtils.isEmpty(json)) {
				StringEntity entity = new StringEntity(json, "utf-8");
				entity.setContentType("application/json");
				httpPut.setEntity(entity);
			}
			response = httpclient.execute(httpPut);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				resData = EntityUtils.toString(response.getEntity());
			} else {
				logger.error("http put error status [ " + status + "]");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("http put Exception [ " + ex + "]");
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("http put IOException [ " + e + "]");
			}
		}
		return resData;
	}
}
