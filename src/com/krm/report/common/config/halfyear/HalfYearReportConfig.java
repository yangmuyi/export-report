package com.krm.report.common.config.halfyear;

import com.krm.report.common.config.ConfigFactory;

public class HalfYearReportConfig {

	public HalfYearReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.halfyear.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.halfyear.end");
	}
	
}
