package com.krm.report.export;

import com.krm.report.util.ThreadPoolUtil;

/*
 * 导出pdf
 */
public class BatchExportPdfBySeason {
	/**
	 * 入口方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ThreadPoolUtil.init(args, "season.path", "seasonReportExportSuccessOrgan.txt");
	}

}
