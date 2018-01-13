package com.navix.common.log4jex;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

public class ShortenRollingFileAppender extends RollingFileAppender {
	@Override
	public void append(LoggingEvent event) {
		ShortenAppenderUtil.reconstructionFullInfo(event);
		super.append(event);
	}
}
