package com.navix.core.web.task;

import java.util.List;

/**由spring注入启动任务，被sysinit类调用来启动配置的任务
 * 
 */
public interface TaskListInter {
	public List<ServletInitJobInter> getTasks();
}
