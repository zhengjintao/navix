package com.navix.doc.server;

import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.query.DataQuery;
import com.navix.doc.domain.Farmtop;

/* *
 *功能：置顶文档服务层接口
 *详细：
 */
public interface FarmtopServiceInter {
	/**
	 * 新增实体管理实体
	 * 
	 * @param entity
	 */
	public Farmtop insertFarmtopEntity(Farmtop entity, LoginUser user);

	/**
	 * 修改实体管理实体
	 * 
	 * @param entity
	 */
	public Farmtop editFarmtopEntity(Farmtop entity, LoginUser user);

	/**
	 * 删除实体管理实体
	 * 
	 * @param entity
	 */
	public void deleteFarmtopEntity(String id, LoginUser user);

	/**
	 * 获得实体管理实体
	 * 
	 * @param id
	 * @return
	 */
	public Farmtop getFarmtopEntity(String id);

	/**
	 * 创建一个基本查询用来查询当前实体管理实体
	 * 
	 * @param query
	 *            传入的查询条件封装
	 * @return
	 */
	public DataQuery createFarmtopSimpleQuery(DataQuery query);

	/**
	 * 置顶文档选择文档列表数据
	 * @param query
	 * @return DataQuery
	 */
	public DataQuery docTopChooseDocList(DataQuery query);
}