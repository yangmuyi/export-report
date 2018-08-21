package com.krm.report.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.common.config.UserConfig;
import com.krm.report.login.UserLogin;


public class HttpClientUtil {
	static CookieStore cookieStore = new BasicCookieStore();
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static void printRespose(CloseableHttpResponse response) throws ParseException, IOException{
        // 获取响应消息实体
        HttpEntity entity = response.getEntity();
        // 响应状态
        System.out.println("status:" + response.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = response.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            System.out.println("response length:" + responseString.length());
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));
        }
	}
	
	/**
	 * 发送get请求，并获取响应内容
	 * @param url 请求地址
	 * @return String 响应内容
	 * @throws Exception 
	 */
	public static String get(String url) throws Exception{
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)
				.setConnectionRequestTimeout(15000).setSocketTimeout(20000).build();
		httpGet.setConfig(config);
		log.info("***HTTPGetURI Path***:"+url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		log.info("***HTTPGetURI Success***:"+response.getStatusLine().getStatusCode());
		if(response.getStatusLine().getStatusCode()==200){
			String result = getResponseHtml(response);
//		log.info("***HTTPGet response***:"+result);
			close(httpClient, response);
			return result;
		}
		if(response.getStatusLine().getStatusCode()>=400){
			log.error("bi system shutdown,please restart");
				throw new Exception("bi system shutdown,please restart");
		}
		return "";
	}
	
	/**
	 * 将响应实体转换成字符串（UTF-8）
	 * @param response
	 * @return String 响应内容
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getResponseHtml(CloseableHttpResponse response) throws ParseException, IOException{
		HttpEntity entity = response.getEntity();
		String responseHtml = EntityUtils.toString(entity, "utf-8");
		return responseHtml;
	}
	
	/**
	 * 发送Post请求
	 * @param url 请求地址
	 * @param map 请求表单参数
	 * @return String 响应内容
	 * @throws Exception 
	 */
	public static String post(String url,Map<String,String> map){
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost httpPost = new HttpPost(url);
//		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)
//				.setConnectionRequestTimeout(15000).setSocketTimeout(20000).build();
//		httpPost.setConfig(config);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator  iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry= (Entry<String, String>) iterator.next();
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		}
		CloseableHttpResponse response;
		try {
			log.info("***POST URL***"+url);
			response = httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==302){
				String ssourl = response.getFirstHeader("Location").getValue();
				log.info("***HTTP POST 302 loginURL***"+ssourl);
				if(ssourl.indexOf("ticket=")!=-1){
					String ticket = ssourl.split("ticket=")[1];
					try {
						get(ssourl);
					} catch (Exception e) {
//						e.printStackTrace();
					}
					close(httpClient, response);
					return ticket;
				}else{
					try {
						get(ssourl);
						log.info("***302 ticket NULL Repeat Login SSO***:"+ssourl);
//						UserLogin.login(UserConfig.getUserName(), UserConfig.getPassword(), UserConfig.getUrl());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			}
			if(response.getStatusLine().getStatusCode()==200){
				log.info("***200 ticket  ");
//				printRespose(response);
				log.info("***SUCESS**HTTP Response Code*"+response.getStatusLine().getStatusCode()+"***"+map.get("reportId")+","+map.get("value"));
				close(httpClient, response);
				return "";
			}else{
				log.info("***>200 ticket NULL");
				log.info("***ERROR**HTTP Response Code*"+response.getStatusLine().getStatusCode());
				log.error(response.getStatusLine().getStatusCode()+"***export report error***"+map.get("reportId")+","+map.get("value"));
			}
			close(httpClient, response);
		} catch (ClientProtocolException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		//String result = getResponseHtml(response);
		return "";
	}
	/**
	 * 发送业务Post请求
	 * @param url 请求地址
	 * @param map 请求表单参数
	 * @return String 响应内容
	 * @throws Exception 
	 */
	public static String post(String url,Map<String,String> map,String name){
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost httpPost = new HttpPost(url);
//		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000)
//				.setConnectionRequestTimeout(15000).setSocketTimeout(20000).build();
//		httpPost.setConfig(config);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator  iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry= (Entry<String, String>) iterator.next();
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		}
		CloseableHttpResponse response;
		try {
			log.info("***POST URL***"+url);
			response = httpClient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==302){
				String ssourl = response.getFirstHeader("Location").getValue();
				log.info("***HTTP POST 302 loginURL***"+ssourl);
				if(ssourl.indexOf("ticket=")!=-1){
					String ticket = ssourl.split("ticket=")[1];
					try {
						get(ssourl);
					} catch (Exception e) {
//						e.printStackTrace();
					}
					close(httpClient, response);
					return ticket;
				}else{
					try {
						get(ssourl);
						log.info("***302 ticket NULL Repeat Login SSO***:"+ssourl);
//						UserLogin.login(UserConfig.getUserName(), UserConfig.getPassword(), UserConfig.getUrl());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			}
			if(response.getStatusLine().getStatusCode()==200){
				log.info("***200 ticket  "+name+map.get("ticket"));
//				printRespose(response);
				log.info("***SUCESS**HTTP Response Code*"+name+response.getStatusLine().getStatusCode()+"***"+map.get("reportId")+","+map.get("value")+map.get("ticket"));
				close(httpClient, response);
				return "1";
			}else{
				log.info("***>200 ticket NULL"+name+map.get("ticket"));
				log.info("***ERROR**HTTP Response Code*"+name+response.getStatusLine().getStatusCode()+map.get("ticket"));
				log.error(response.getStatusLine().getStatusCode()+name+"***export report error***"+map.get("reportId")+","+map.get("value")+map.get("ticket"));
			}
			close(httpClient, response);
		} catch (ClientProtocolException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		//String result = getResponseHtml(response);
		return "";
	}
	
	/**
	 * 关闭资源
	 * @param httpClient
	 * @param response
	 * @throws IOException
	 */
	public static void close(CloseableHttpClient httpClient,CloseableHttpResponse response) throws IOException{
		if(response!=null){
			response.close();
		}
		if(httpClient!=null){
			httpClient.close();
		}
	}
}
