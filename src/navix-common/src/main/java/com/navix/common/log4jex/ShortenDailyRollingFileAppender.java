package com.navix.common.log4jex;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * @author kimitei
 *
 */
public class ShortenDailyRollingFileAppender extends DailyRollingFileAppender {
	@Override
	public void append(LoggingEvent event) {
		ShortenAppenderUtil.reconstructionFullInfo(event);
		super.append(event);
	}
}
