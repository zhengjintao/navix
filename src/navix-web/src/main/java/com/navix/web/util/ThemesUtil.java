package com.navix.web.util;

import com.navix.parameter.FarmParameterService;

public class ThemesUtil {
	public static String getThemePath() {
		return FarmParameterService.getInstance().getParameter("config.sys.web.themes.path");
	}
}
