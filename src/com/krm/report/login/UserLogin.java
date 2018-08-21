package com.krm.report.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.task.UserLoginTimerTask;
import com.krm.report.util.HttpClientUtil;

public class UserLogin {
	private static Logger log=LoggerFactory.getLogger(UserLogin.class);
	public UserLogin() {
	}

	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @param url
	 * @throws Exception 
	 */
	public static String login(String username,String password,String url) throws Exception{
		//发送登录请求，获取响应网页内容
		String responseHtml = HttpClientUtil.get(url);
		log.info(responseHtml);
		//封装发送post请求时的表单参数
		String lt="";
		String execution="";
		String loginurl="";
		if(responseHtml.indexOf("<input type=\"hidden\" name=\"lt\" value=\"")!=-1){
			
			lt = responseHtml.split("<input type=\"hidden\" name=\"lt\" value=\"")[1].split("\" />")[0];
		}
		if(responseHtml.indexOf("<input type=\"hidden\" name=\"execution\" value=\"")!=-1){
			
			execution = responseHtml.split("<input type=\"hidden\" name=\"execution\" value=\"")[1].split("\" />")[0];
		}
		if(responseHtml.indexOf("action=\"")!=-1){
			
			loginurl = responseHtml.split("action=\"")[1].split("\"")[0];
		}
        String rootUrl = url.substring(0, url.lastIndexOf("/"));
//        String service = responseHtml.split("service=")[1].split("\"")[0];
        if(lt.equals("")||execution.equals("")||loginurl.equals("")){
        	return UserLoginTimerTask.ticket;
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        map.put("execution",execution);
        map.put("_eventId", "submit");
        map.put("lt",lt);
//        map.put("service",service);
        
        //发送post请求，获取登录后的ticket
        String ticket = HttpClientUtil.post(rootUrl+loginurl, map);
        return ticket;
	}
}
