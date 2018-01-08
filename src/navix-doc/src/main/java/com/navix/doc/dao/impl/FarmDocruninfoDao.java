package com.navix.doc.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.sql.utils.HibernateSQLTools;
import com.navix.doc.dao.FarmDocruninfoDaoInter;
import com.navix.doc.domain.FarmDocruninfo;

/**
 * 实体管理
 * 
 * @author 
 * 
 */
@Repository
public class FarmDocruninfoDao extends HibernateSQLTools<FarmDocruninfo> implements FarmDocruninfoDaoInter {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFatory;

	public void deleteEntity(FarmDocruninfo entity) {
		Session session = sessionFatory.getCurrentSession();
		session.delete(entity);
	}

	public int getAllListNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery("select count(*) from navix_docruninfo");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	public FarmDocruninfo getEntity(String id) {
		Session session = sessionFatory.getCurrentSession();
		return (FarmDocruninfo) session.get(FarmDocruninfo.class, id);
	}

	public FarmDocruninfo insertEntity(FarmDocruninfo entity) {
		Session session = sessionFatory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	public void editEntity(FarmDocruninfo entity) {
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
	public List<FarmDocruninfo> selectEntitys(List<DBRule> rules) {
		return selectSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public void updataEntitys(Map<String, Object> values, List<DBRule> rules) {
		updataSqlFromFunction(sessionFatory.getCurrentSession(), values, rules);
	}

	public SessionFactory getSessionFatory() {
		return sessionFatory;
	}

	public void setSessionFatory(SessionFactory sessionFatory) {
		this.sessionFatory = sessionFatory;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}

	@Override
	protected Class<FarmDocruninfo> getTypeClass() {
		return FarmDocruninfo.class;
	}

	@Override
	public Integer getKnowsNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery("select count(*) from navix_DOC where STATE=1 and DOMTYPE!=4");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getGoodKnowsNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery(
				"select count(*) from navix_DOC a left join navix_DOCRUNINFO b on a.RUNINFOID=b.ID where a.STATE=1 and a.DOMTYPE!=4 and b.EVALUATE>=0");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	@Override
	public Integer getBadKnowsNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery(
				"select count(*) from navix_DOC a left join navix_DOCRUNINFO b on a.RUNINFOID=b.ID where a.STATE=1 and a.DOMTYPE!=4 and b.EVALUATE<0");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}
}
