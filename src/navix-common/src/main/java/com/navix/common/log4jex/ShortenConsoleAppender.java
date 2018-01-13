package com.navix.common.log4jex;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.spi.LoggingEvent;

public class ShortenConsoleAppender extends ConsoleAppender {
	
	@Override
	public void append(LoggingEvent event) {
		ShortenAppenderUtil.reconstructionFullInfo(event);
		super.append(event);
	}
}
