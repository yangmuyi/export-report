package com.krm.report.common.config.year;

import com.krm.report.common.config.ConfigFactory;

public class YearReportConfig {

	public YearReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.year.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.year.end");
	}
	
}
