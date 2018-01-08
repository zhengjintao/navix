package com.navix.quartz.adapter;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.navix.core.util.spring.BeanFactory;
import com.navix.core.web.task.ServletInitJobInter;
import com.navix.quartz.server.FarmQzSchedulerManagerInter;

public class StartSysTask implements ServletInitJobInter {
	private static final Logger log = Logger.getLogger(StartSysTask.class);

	@Override
	public void execute(ServletContext context) {
		FarmQzSchedulerManagerInter aloneIMP = (FarmQzSchedulerManagerInter) BeanFactory
				.getBean("farmQzSchedulerManagerImpl");
		try {
			aloneIMP.start();
			log.info("started '任务调度'");
		} catch (Exception e) {
			log.error(e);
		}
	}

}
