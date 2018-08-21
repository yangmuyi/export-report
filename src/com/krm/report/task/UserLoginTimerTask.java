package com.krm.report.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.common.config.UserConfig;
import com.krm.report.util.UserLoginUtil;

public class UserLoginTimerTask extends TimerTask {
	private static Logger log = LoggerFactory
			.getLogger(UserLoginTimerTask.class);
	private static String username = UserConfig.getUserName(); // 用户名
	private static String password = UserConfig.getPassword(); // 密码
	private static String url = UserConfig.getUrl(); // 登录地址
	public static String ticket = ""; // 登录成功后返回的ticket

	@Override
	public void run() {
		log.info("***User begin login***:"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
		ticket = UserLoginUtil.login(username, password, url);
		log.info("*ticket**"+UserLoginTimerTask.ticket);
	}
}
