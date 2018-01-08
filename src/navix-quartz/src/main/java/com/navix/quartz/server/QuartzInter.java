package com.navix.quartz.server;

import java.text.ParseException;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import com.navix.quartz.domain.FarmQzScheduler;
import com.navix.quartz.domain.FarmQzTask;
import com.navix.quartz.domain.FarmQzTrigger;

/**
 * queatz的转化接口
 * 
 * @author 
 * 
 */
public interface QuartzInter {
	public JobDetail getJobDetail(FarmQzScheduler node,FarmQzTask task) throws ClassNotFoundException;
	public JobDetail getJobDetail(String schedulerId,FarmQzTask task) throws ClassNotFoundException;
	public Trigger getTrigger(FarmQzScheduler node,FarmQzTrigger trigger) throws ParseException;
	public JobKey getJobKey(FarmQzScheduler node,FarmQzTask task);
}
