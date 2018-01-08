package com.navix.core.web.constant;


public class FarmConstant {
	/**
	 * 菜单树索引编码长度，用来计算上层节点时使用
	 */
	public static final int MENU_TREECODE_UNIT_LENGTH=32;
	/**
	 * SESSION当前登录用户
	 */
	public static final String SESSION_USEROBJ = "USEROBJ";
	/**
	 * SESSION当前登录用户头像
	 */
	public static final String SESSION_USERPHOTO = "USERPHOTO";
	/**
	 * SESSION被权限系统拦截的URL
	 */
	public static final String SESSION_GO_URL = "WANTURL";
	
	/**
	 * SESSION登录前来自的页面URL
	 */
	public static final String SESSION_FROM_URL = "FROMURL";
	/**
	 * SESSION当前登录时间
	 */
	public static final String SESSION_LOGINTIME = "LOGINTIME";
	/**
	 * SESSION当前登录组织机构
	 */
	public static final String SESSION_ORG = "USERORG";
	/**
	 * SESSION当前登录角色
	 */
	public static final String SESSION_ROLES = "LOGINROLES";
	/**
	 * SESSION当前用户权限
	 */
	public static final String SESSION_USERACTION = "USERACTION";
	/**
	 * SESSION当前用户菜单List<Map<String, Object>>
	 * TREECODE,TYPE,URL,ID,IMG,SORT,PARENTID,NAME
	 */
	public static final String SESSION_USERMENU = "USERMENU";
	
	
	/**
	 * licence
	 */
	public static  String LICENCE;
}
