package com.navix.biz.util.log4j.extension;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * @author kimitei
 *
 */
public class ShortenAppenderUtil {
	/**
	 * 重构fullInfo 类报名改为简写,并显示行番号
	 * 
	 * 
	 * @param locationInfo
	 */
	public static void reconstructionFullInfo(LoggingEvent event) {
		LocationInfo locationInfo = event.getLocationInformation();
		String quotes = locationInfo.getClassName();
		StringBuilder buf = new StringBuilder();
		if (quotes != null) {
			String[] split = quotes.split("\\.");
			int length = split.length - 1;
			for (int i = 0; i < length; i++) {
				buf.append(split[i].substring(0, 1) + ".");
			}
			buf.append(split[length]);
			buf.append("#");
			buf.append(locationInfo.getMethodName());
			buf.append("(");
			buf.append(locationInfo.getLineNumber());
			buf.append(")");
			locationInfo.fullInfo = buf.toString();
		}
	}
}
