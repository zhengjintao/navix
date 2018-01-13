package com.navix.biz.util.log4j.extension;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.spi.LoggingEvent;

public class ShortenConsoleAppender extends ConsoleAppender {
	
	@Override
	public void append(LoggingEvent event) {
		ShortenAppenderUtil.reconstructionFullInfo(event);
		super.append(event);
	}
}
