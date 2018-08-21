package com.krm.report.common.config.day;

import com.krm.report.common.config.ConfigFactory;

public class DayReportConfig {
	public DayReportConfig() {
	}
	
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.end");
	}
}
