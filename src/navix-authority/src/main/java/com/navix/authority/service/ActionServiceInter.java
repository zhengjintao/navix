package com.navix.authority.service;

import java.util.List;

import com.navix.authority.domain.Action;
import com.navix.authority.domain.Actiontree;
import com.navix.core.auth.domain.AuthKey;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.auth.domain.WebMenu;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.web.easyui.EasyUiTreeNode;

/* *
 *功能：权限资源服务层接口
 *详细：
 *
 *版本：v0.1
 *作者：王东
 *日期：20141119144919
 *说明：
 */
public interface ActionServiceInter {
	/**
	 *新增权限定义
	 * 
	 * @param entity
	 */
	public Action insertActionEntity(Action entity, LoginUser user);

	/**
	 *修改权限定义
	 * 
	 * @param entity
	 */
	public Action editActionEntity(Action entity, LoginUser user);

	/**
	 *删除权限定义
	 * 
	 * @param entity
	 */
	public void deleteActionEntity(String id, LoginUser user);

	/**
	 *获得权限定义
	 * 
	 * @param id
	 * @return
	 */
	public Action getActionEntity(String id);

	/**
	 * 创建一个基本查询用来查询当前权限定义
	 * 
	 * @param query
	 *            传入的查询条件封装
	 * @return
	 */
	public DataQuery createActionSimpleQuery(DataQuery query);

	/**
	 *新增构造权限
	 * 
	 * @param entity
	 * @param user
	 * @param authkey
	 *            如果不是选择的就用authkey创建一个权限
	 * @return
	 */
	public Actiontree insertActiontreeEntity(Actiontree entity, LoginUser user,
			String authkey);

	/**
	 *修改构造权限
	 * 
	 * @param entity
	 * @param user
	 * @param authkey
	 *            如果不是选择的就用authkey创建一个权限
	 * @return
	 */
	public Actiontree editActiontreeEntity(Actiontree entity, LoginUser user,
			String authkey);

	/**
	 *删除构造权限
	 * 
	 * @param entity
	 */
	public void deleteActiontreeEntity(String id, LoginUser user);

	/**
	 *获得构造权限
	 * 
	 * @param id
	 * @return
	 */
	public Actiontree getActiontreeEntity(String id);

	/**
	 * 创建一个基本查询用来查询当前构造权限
	 * 
	 * @param query
	 *            传入的查询条件封装
	 * @return
	 */
	public DataQuery createActiontreeSimpleQuery(DataQuery query);

	/**
	 * 构造权限异步树
	 * 
	 * @return
	 */
	public List<EasyUiTreeNode> getSyncTree(String parentId, String domain);

	/**
	 * 移动构造树节点到目标节点下
	 * 
	 * @param treeNodeId
	 *            原来id
	 * @param targetTreeNodeId
	 *            目标id
	 */
	public void moveActionTreeNode(String treeNodeId, String targetTreeNodeId);

	/**
	 * 获得缓存的权限属性
	 * 
	 * @param key
	 * @return
	 */
	public AuthKey getCacheAction(String key);

	/**
	 * 获得全部可用菜单
	 * 
	 * @return
	 */
	public List<WebMenu> getAllMenus();

	/**
	 * 获得全部权限
	 * 
	 * @return
	 */
	public List<Action> getAllActions();

}