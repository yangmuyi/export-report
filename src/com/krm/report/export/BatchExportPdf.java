package com.krm.report.export;

import com.krm.report.util.ThreadPoolUtil;

/*
 * 导出pdf
 */
public class BatchExportPdf {
	/**
	 * 入口方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) {
		ThreadPoolUtil.init(args, "day.path", "dayReportExportSuccessOrgan.txt");
	}
}
