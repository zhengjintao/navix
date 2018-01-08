package com.navix.doc.exception;

import com.navix.parameter.FarmParameterService;

/**
 * 没有删除权限异常
 * 
 * @author Administrator
 * 
 */
public class CanNoDeleteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CanNoDeleteException(String message) {
		super(message);
	}

	public CanNoDeleteException() {
		super(FarmParameterService.getInstance().getParameter(
				"title.com.farm.doc.exception.nowdel"));
	}
}
