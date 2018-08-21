package com.krm.report.common.config;

/**
 * 用户单点登录配置类
 * @author Li Gaoling
 * @date 2018-07-10
 *
 */
public final class UserConfig {
	public UserConfig(){
	}
	/**
	 * 获取用户名
	 * @return
	 */
	public static String getUserName(){
		return ConfigFactory.getString("login.username");
	}
	/**
	 * 获取密码
	 * @return
	 */
	public static String getPassword(){
		return ConfigFactory.getString("login.password");
	}
	/**
	 * 获取登录地址
	 * @return
	 */
	public  static String getUrl(){
		return ConfigFactory.getString("login.url");
	}
	
	/**
	 * 获取导出pdf的url
	 * @return
	 */
	public static String getExportUrl(){
		return ConfigFactory.getString("login.export.url");
	}
}
