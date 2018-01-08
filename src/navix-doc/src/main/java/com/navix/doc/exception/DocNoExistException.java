package com.navix.doc.exception;

import com.navix.parameter.FarmParameterService;

/**
 * 商品没有存在异常
 * 
 * @author 
 * 
 */
public class DocNoExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocNoExistException(String message) {
		super(message);
	}

	public DocNoExistException() {
		super(FarmParameterService.getInstance().getParameter(
				"title.com.farm.doc.exception.docnoexist"));
	}
}
