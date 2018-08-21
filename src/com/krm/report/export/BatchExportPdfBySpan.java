package com.krm.report.export;

import com.krm.report.util.ThreadPoolUtil;


/*
 * 导出pdf
 */
public class BatchExportPdfBySpan {
	/**
	 * 入口方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ThreadPoolUtil.init(args, "span.path", "spanReportExportSuccessOrgan.txt");
	}

}
