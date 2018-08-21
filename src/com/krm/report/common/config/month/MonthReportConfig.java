package com.krm.report.common.config.month;

import com.krm.report.common.config.ConfigFactory;

public class MonthReportConfig {

	public MonthReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.month.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.month.end");
	}
	
}
