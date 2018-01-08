package com.navix.core.sql.query;

import com.navix.core.Context;
import com.navix.core.auth.util.KeyUtil;

public class CoreHandle {
	public static boolean runLce() {
		Context.MK = KeyUtil.getMKey();
		return true;
	}
}
