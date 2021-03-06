package com.navix.doc.server;

import java.util.List;
import java.util.Map;

import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.query.DataQuery;
import com.navix.doc.domain.Weburl;

/* *
 *功能：推荐服务服务层接口
 *详细：
 */
public interface WeburlServiceInter {
	/**
	 * 新增实体管理实体
	 * 
	 * @param entity
	 */
	public Weburl insertWeburlEntity(Weburl entity, LoginUser user);

	/**
	 * 修改实体管理实体
	 * 
	 * @param entity
	 */
	public Weburl editWeburlEntity(Weburl entity, LoginUser user);

	/**
	 * 删除实体管理实体
	 * 
	 * @param entity
	 */
	public void deleteWeburlEntity(String id, LoginUser user);

	/**
	 * 获得实体管理实体
	 * 
	 * @param id
	 * @return
	 */
	public Weburl getWeburlEntity(String id);

	/**
	 * 创建一个基本查询用来查询当前实体管理实体
	 * 
	 * @param query
	 *            传入的查询条件封装
	 * @return
	 */
	public DataQuery createWeburlSimpleQuery(DataQuery query);

	/**
	 * 获取列表集合
	 * @return
	 * List<Weburl>
	 */
	public List<Map<String, Object>> getList();

	/**
	 * 删除附件
	 * @param imgid
	 * void
	 */
	public void delImg(String imgid);
}