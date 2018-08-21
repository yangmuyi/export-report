package com.krm.report.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogExceptionStackUtil {
	/**
	 * 
	 * @功能说明:在日志文件中，打印异常堆栈
	 * @param Throwable
	 * @return:String
	 */
	public static String LogExceptionStack(Throwable e) {
		StringWriter errorsWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(errorsWriter));
		return errorsWriter.toString();
	}
}
