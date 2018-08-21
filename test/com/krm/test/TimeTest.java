package com.krm.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import com.krm.report.common.config.ConfigFactory;
import com.krm.report.common.config.LogConfig;
import com.krm.report.task.TenDaysReportTask;
import com.krm.report.task.UserLoginTimerTask;

public class TimeTest {
	/**
	 * 加载配置文件
	 */
	static {
		// 初始化日志
		LogConfig.config("logback.xml");
		// 加载配置文件
		ConfigFactory.init("config.xml");
	}
	public static void main(String[] args) {
		//用户登录
//		// 创建定时器
		Timer timer = new Timer();
		// 表示在0秒之后开始执行，并且每2小时执行一次
		UserLoginTimerTask userLoginTimerTask = new UserLoginTimerTask();
		timer.schedule(userLoginTimerTask, 0, 7200000);
		System.out.println("***ticket***ThreadPoolUtil.init():"+UserLoginTimerTask.ticket);
	}
	public static void main1(String[] args) {
		TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        // task to run goes here
		    	  System.out.println("***User begin login***:"
		  				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		  						.format(new Date()));
		        System.out.println("Hello !!!");
		      }
		    };
		    System.out.println("begin");
		    Timer timer = new Timer();
		    long delay = 0;
		    long intevalPeriod = 1 * 10000;
		    // schedules the task to be run in an interval
		    timer.schedule(task, delay, intevalPeriod);		
	}
}
