package com.krm.report.common.config.season;

import com.krm.report.common.config.ConfigFactory;

public class SeasonReportConfig {

	public SeasonReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.season.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.season.end");
	}
	
}
