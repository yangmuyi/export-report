package com.krm.report.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.common.config.ConfigFactory;
import com.krm.report.common.config.LogConfig;
import com.krm.report.common.config.UserConfig;
import com.krm.report.common.config.day.DayReportConfig;
import com.krm.report.common.config.halfyear.HalfYearReportConfig;
import com.krm.report.common.config.month.MonthReportConfig;
import com.krm.report.common.config.season.SeasonReportConfig;
import com.krm.report.common.config.span.SpanReportConfig;
import com.krm.report.common.config.tendays.TenDaysReportConfig;
import com.krm.report.common.config.year.YearReportConfig;
import com.krm.report.task.DayReportTask;
import com.krm.report.task.HalfYearReportTask;
import com.krm.report.task.MonthReportTask;
import com.krm.report.task.SeasonReportTask;
import com.krm.report.task.SpanReportTask;
import com.krm.report.task.TenDaysReportTask;
import com.krm.report.task.UserLoginTimerTask;
import com.krm.report.task.YearReportTask;
import com.krm.report.vo.FormVo;
import com.krm.report.vo.OrganVo;

public class ThreadPoolUtil {
	private static Logger log = LoggerFactory.getLogger(ThreadPoolUtil.class);
	/**
	 * 加载配置文件
	 */
	static {
		// 初始化日志
		LogConfig.config("logback.xml");
		// 加载配置文件
		ConfigFactory.init("config.xml");
	}

	/**
	 * 导出报表工具类
	 * 
	 * @param args
	 *            导出指定日期的报表,如果没指定则从config.xml读取日期配置
	 * @param reportPath
	 *            导出报表的文件路径
	 * @param reportFileName
	 *            已经成功导出的报表
	 */
	public static void init(String args[], String reportPath,
			String reportFileName) {

//		// 用户登录
//		// // 创建定时器
		Timer timer = new Timer();
		// 表示在0秒之后开始执行，并且每24小时执行一次
		UserLoginTimerTask userLoginTimerTask = new UserLoginTimerTask();
		timer.schedule(userLoginTimerTask, 0, 2000 * 60 /** 60*12*/);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		log.info("***Prepare Export Report***" + reportPath + "," + ","
				+ reportFileName);
		String dataDate = ""; // 接收脚本传的第一个日期参数，用来导出这个日期的数据
		// 线程池参数
		int corePoolSize = ConfigFactory.getInt("thread.pool.corePoolSize");
		int maximumPoolSize = ConfigFactory
				.getInt("thread.pool.maximumPoolSize");
		long keepAliveTime = ConfigFactory.getInt("thread.pool.keepAliveTime");

		// 创建线程池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				new ThreadPoolExecutor.CallerRunsPolicy());

		List<OrganVo> organVoList = Dom4jUtil.getOrganVoList(); // 获取机构信息

		if (args.length == 1) {
			dataDate = args[0];
			log.info("***Exprot Specific Date:" + dataDate + "***");
		}

		List<String> dateList = new ArrayList<String>();
		if (dataDate == null || dataDate.equals("")) {
			log.info("***sh file date is undefined,read config.xml date set***");
			if (reportPath.equalsIgnoreCase("day.path")) {
				String beginDate = DayReportConfig.getBeginDate(); // 获取日报开始信息
				String endDate = DayReportConfig.getEndDate(); // 获取日报结束日期
				dateList = HandelReportDateUtil.handleCirculationDate(endDate,
						beginDate); // 获取两个日期之间的每一天
			} else if (reportPath.equalsIgnoreCase("season.path")) {
				String beginDate = SeasonReportConfig.getBeginDate(); // 获取日报开始信息
				String endDate = SeasonReportConfig.getEndDate(); // 获取日报结束日期
				dateList = HandelReportDateUtil.handleCirculationDateBySeason(
						endDate, beginDate); // 获取两个日期之间的每一天
			} else if (reportPath.equalsIgnoreCase("tendays.path")) {
				// 获取旬报开始日期
				String beginDate = TenDaysReportConfig.getBeginDate();
				// 获取旬报结束日期
				String endDate = TenDaysReportConfig.getEndDate();
				dateList = HandelReportDateUtil.handleCirculationDateByTenDays(
						endDate, beginDate);
			} else if (reportPath.equalsIgnoreCase("month.path")) {
				// 获取月报开始日期
				String beginDate = MonthReportConfig.getBeginDate();
				// 获取月报结束日期
				String endDate = MonthReportConfig.getEndDate();
				dateList = HandelReportDateUtil.handleCirculationDateByMonth(
						endDate, beginDate);
			} else if (reportPath.equalsIgnoreCase("span.path")) {
				// 日期跨度
				String beginDate = SpanReportConfig.getBeginDate();
				String endDate = SpanReportConfig.getEndDate();
				dateList = HandelReportDateUtil
						.handleCirculationDateByMonthBegin(endDate, beginDate);

			} else if (reportPath.equalsIgnoreCase("year.path")) {
				// 年报
				String beginDate = YearReportConfig.getBeginDate();
				String endDate = YearReportConfig.getEndDate();
				dateList = HandelReportDateUtil.handleCirculationDateByYear(
						endDate, beginDate);
			} else if (reportPath.equalsIgnoreCase("halfyear.path")) {
				// 半年报
				String beginDate = HalfYearReportConfig.getBeginDate();
				String endDate = HalfYearReportConfig.getEndDate();
				dateList = HandelReportDateUtil
						.handleCirculationDateByHalfYear(endDate, beginDate);
			}
		} else {
			dateList.add(dataDate);
		}
		String path = ConfigFactory.getString(reportPath); // 获取生成的报表参数
		String systemName = System.getProperty("os.name");
		List<FormVo> formVoList = new ArrayList<FormVo>();
		if (systemName.indexOf("Windows") != -1) {
			// win环境调用
			formVoList = Dom4jUtil.getFormVoList("win",
					path.substring(6));

		} else {
			formVoList = Dom4jUtil.getFormVoList("linux", path);
		}

		// Linux环境调用
		// List<FormVo> formVoList = Dom4jUtil.getFormVoList("linux",path);

		// 脚本异常退出后，从下面的日期和机构继续跑
		log.info("***Prepare read disrupted info***");
		// //已经导出的机构list
		List<String> lineList = FileUtil.getFileLine(reportFileName);

		log.info("***End read disrupted info***");
		// 遍历每一个日期
		for (int dateCount = 0; dateCount < dateList.size(); dateCount++) {
			String exportDate = dateList.get(dateCount);
			// 遍历每一家机构
			for (int organCount = 0; organCount < organVoList.size(); organCount++) {

				OrganVo organVo = organVoList.get(organCount);
				String organ = organVo.getId();
				// log.info("***begin isExport***"+ exportDate + "," + organ);
				// 如果已经导出不再重复导
				if (FileUtil.isExport(lineList, exportDate, organ)) {
					log.info("***This Organ Report is already Export ALL***:"
							+ exportDate + "," + organ);
					continue;
				}
				if (reportPath.equalsIgnoreCase("day.path")) {
					DayReportTask task = new DayReportTask(exportDate, organVo,
							formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("season.path")) {

					SeasonReportTask task = new SeasonReportTask(exportDate,
							organVo, formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("year.path")) {
					YearReportTask task = new YearReportTask(exportDate,
							organVo, formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("halfyear.path")) {
					HalfYearReportTask task = new HalfYearReportTask(
							exportDate, organVo, formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("month.path")) {
					MonthReportTask task = new MonthReportTask(exportDate,
							organVo, formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("span.path")) {
					SpanReportTask task = new SpanReportTask(exportDate,
							organVo, formVoList);
					executor.execute(task);
				}
				if (reportPath.equalsIgnoreCase("tendays.path")) {
					TenDaysReportTask task = new TenDaysReportTask(exportDate,
							organVo, formVoList);
					executor.execute(task);
				}
			}
		}
		executor.shutdown();
		try {
			while (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				 log.info("***ThreadNumber of Pool:"+executor.getPoolSize()+",TaskNumber Wait In Queue:"+
			             executor.getQueue().size()+",TaskNumber Success:"+executor.getCompletedTaskCount());
			}
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
		log.info("***sh file success end***");
		System.exit(0);
	}

	public static void main(String[] args) {
		String systemName = System.getProperty("os.name");
		System.out.println(systemName);
	}
}
