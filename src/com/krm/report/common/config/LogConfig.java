package com.krm.report.common.config;

import java.io.InputStream;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogConfig {
	public static void config(String configFilePath)
	  {
	    LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
	    JoranConfigurator configurator = new JoranConfigurator();
	    configurator.setContext(lc);
	    lc.reset();
	    /*try
	    {
	      configurator.doConfigure(configFilePath);
	    }*/
		InputStream is= LogConfig.class.getClassLoader().getResourceAsStream(configFilePath);
		try {
			configurator.doConfigure(is);
		}
	    catch (JoranException e)
	    {
	      System.out.println(e);
	      System.out.println("Fatal: Init LogConfig Error.");
	      System.exit(1);
	    }
	  }
}
