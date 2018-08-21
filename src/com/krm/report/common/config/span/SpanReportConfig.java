package com.krm.report.common.config.span;

import com.krm.report.common.config.ConfigFactory;

public class SpanReportConfig {

	public SpanReportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取开始日期
	 * @return
	 */
	public static String getBeginDate(){
		return ConfigFactory.getString("date.span.begin");
	}
	/**
	 * 获取结束日期
	 * @return
	 */
	public static String getEndDate(){
		return ConfigFactory.getString("date.span.end");
	}
	
}
