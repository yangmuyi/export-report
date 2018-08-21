package com.krm.report.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.login.UserLogin;
import com.krm.report.task.UserLoginTimerTask;

public class UserLoginUtil {
	private static Logger log = LoggerFactory.getLogger(UserLoginUtil.class);
	//
	public static String login(String username,String password,String url){
		log.info("***Prepare Login***");
		log.info("***username:" + username + ",password:" + password + ",loginUrl:" + url);
		try {
			UserLoginTimerTask.ticket = UserLogin.login(username, password, url);
			log.info("***Login Success***");
			log.info("***ticket:" + UserLoginTimerTask.ticket+"***");
		} catch (Exception e) {
			log.error("***Failed User Login***");
			log.error(LogExceptionStackUtil.LogExceptionStack(e));
		}
		return UserLoginTimerTask.ticket;
	}
	
	public static boolean isPass2Hours(long initTimeMillis){
		boolean flag = false;
		long currentTimeMillis = System.currentTimeMillis(); // 获取当前时间毫秒数
		if((currentTimeMillis-initTimeMillis)/1000/60/60/2 ==0){
			log.info("***Time Pass 2 Hours***");
			flag=true;
		}
		return flag;
	}
	
	
}
