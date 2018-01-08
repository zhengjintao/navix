package com.navix.doc.exception;

import com.navix.parameter.FarmParameterService;

/**
 * 没有读取权限异常
 * 
 * @author 
 * 
 */
public class CanNoReadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CanNoReadException(String message) {
		super(message);
	}

	public CanNoReadException() {
		super(FarmParameterService.getInstance().getParameter(
				"title.com.farm.doc.exception.noread"));
	}
}
