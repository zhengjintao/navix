
package com.navix.core.web.task;

import javax.servlet.ServletContext;

/**
 * 随系统启动任务接口
 * 
 * 
 */
public interface ServletInitJobInter {
	/**被执行的任务
	 * @param context
	 */
	public void execute(ServletContext context);
}
