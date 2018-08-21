package com.krm.report.export;

import com.krm.report.util.ThreadPoolUtil;

/*
 * 导出半年报pdf
 */
public class BatchExportPdfByHalfYear {

	/**
	 * 入口方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ThreadPoolUtil.init(args, "halfyear.path","halfyearReportExportSuccessOrgan.txt");
	}

}
