package com.navix.core.web.online;

import java.util.HashMap;
import java.util.Map;

import com.navix.core.sql.result.DataResult;


/**
 * 在线用户管理 非集群实现 实现该功能需要将方法： userLoginHandle()
 * userVisitHandle()加入到用户登录，和用户访问系统资源的代码中
 * 
 */
public interface OnlineUserOpInter {
	/**
	 * 在线用户注册表，勿调用、勿操作
	 */
	static final Map<String, Map<String, Object>> onlineUserTable = new HashMap<String, Map<String, Object>>();
	/**
	 * 最近访问时间
	 */
	static final String key_TIME = "TIME";
	/**
	 * 用户IP
	 */
	static final String key_IP = "IP";
	/**
	 * 用户loginName
	 */
	static final String key_LNAME = "LNAME";
	/**
	 * 用户对象
	 */
	static final String key_USEROBJ = "USEROBJ";
	/**
	 * 用户登录时间
	 */
	static final String key_LOGINTIME = "LOGINTIME";
	/**
	 * 登录时长
	 */
	static final String key_VISITTIME = "VISITTIME";
	/**
	 * 在线用户判超时时间 （分）
	 */
	static final long onlineVilaMinute = 60;
	

	/**
	 * 用户访问handle 判断当前用户是否在线，不在线就(从session中)注销掉用户
	 * 
	 * @param strutsSession
	 *            STRUTS的session
	 * @param ip
	 *            用户ip
	 */
	public void userVisitHandle();

	/**
	 * 用户登录时对在线用户的操作 注册在线用户
	 * 
	 * @param httpSession
	 *            session
	 * @param ip
	 *            用户ip
	 */
	public void userLoginHandle(Object user);

	/**
	 * 查看当前在线用户
	 * 
	 * @return
	 */
	public DataResult findOnlineUser();

	/**
	 * 强制用户下线
	 * 
	 * @param loginName
	 * @return
	 */
	public boolean doUserDownLine(String loginName);
}
