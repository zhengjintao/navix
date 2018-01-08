package com.navix.parameter.service;

import com.navix.core.auth.domain.LoginUser;
import com.navix.parameter.domain.AloneApplog;


/**系统日志
 * @author 
 *
 */
public interface AloneApplogServiceInter {
	/**
	 *新增实体
	 * 
	 * @param entity
	 */
	public AloneApplog insertEntity(AloneApplog entity,LoginUser user);
	/**
	 *修改实体
	 * 
	 * @param entity
	 */
	public AloneApplog editEntity(AloneApplog entity,LoginUser user);
	/**
	 *删除实体
	 * 
	 * @param entity
	 */
	public void deleteEntity(String entity,LoginUser user);
	/**
	 *获得实体
	 * 
	 * @param id
	 * @return
	 */
	public AloneApplog getEntity(String id);
	
	/**写入日志
	 * @param describes 内容
	 * @param appuser 操作人
	 * @param level 日志等级
	 * @param method 方法
	 * @param classname 类
	 * @param ip 
	 * @return
	 */
	public AloneApplog log(String describes,String appuser,String level,String method,String classname,String ip);
}