package com.navix.quartz.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.sql.utils.HibernateSQLTools;
import com.navix.quartz.dao.FarmQzTriggerDaoInter;
import com.navix.quartz.domain.FarmQzTrigger;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 触发器定义
 * 
 * @author MAC_wd
 * 
 */
@Repository
public class FarmQzTriggerDao extends HibernateSQLTools<FarmQzTrigger>
		implements FarmQzTriggerDaoInter {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFatory;

	public void deleteEntity(FarmQzTrigger entity) {
		Session session = sessionFatory.getCurrentSession();
		session.delete(entity);
	}

	public int getAllListNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("select count(*) from navix_qz_trigger");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	public FarmQzTrigger getEntity(String id) {
		Session session = sessionFatory.getCurrentSession();
		return (FarmQzTrigger) session.get(FarmQzTrigger.class, id);
	}

	public FarmQzTrigger insertEntity(FarmQzTrigger entity) {
		Session session = sessionFatory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	public void editEntity(FarmQzTrigger entity) {
		Session session = sessionFatory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Session getSession() {
		return sessionFatory.getCurrentSession();
	}

	public DataResult runSqlQuery(DataQuery query) {
		try {
			return query.search(sessionFatory.getCurrentSession());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteEntitys(List<DBRule> rules) {
		deleteSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public List<FarmQzTrigger> selectEntitys(List<DBRule> rules) {
		return selectSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public void updataEntitys(Map<String, Object> values, List<DBRule> rules) {
		updataSqlFromFunction(sessionFatory.getCurrentSession(), values, rules);
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}

	@Override
	protected Class<?> getTypeClass() {
		return FarmQzTrigger.class;
	}
}
