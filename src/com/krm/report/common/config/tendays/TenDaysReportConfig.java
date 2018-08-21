package com.krm.report.common.config.tendays;

import com.krm.report.common.config.ConfigFactory;

public class TenDaysReportConfig {

	public TenDaysReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.tendays.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.tendays.end");
	}
	
}
