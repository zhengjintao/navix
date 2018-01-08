package com.navix.doc.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.sql.utils.HibernateSQLTools;
import com.navix.doc.dao.FarmDocfileDaoInter;
import com.navix.doc.domain.FarmDocfile;

/**
 * 文档附件
 * 
 * @author MAC_wd
 * 
 */
@Repository
public class FarmDocfileDao extends HibernateSQLTools<FarmDocfile> implements FarmDocfileDaoInter {
	@Resource(name = "sessionFactory") 
	private SessionFactory sessionFatory;

	public void deleteEntity(FarmDocfile entity) {
		Session session = sessionFatory.getCurrentSession();
		session.delete(entity);
	}

	public int getAllListNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("select count(*) from navix_docfile");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	@Transactional
	public FarmDocfile getEntity(String id) {
		Session session = sessionFatory.getCurrentSession();
		return (FarmDocfile) session.get(FarmDocfile.class, id);
	}

	public FarmDocfile insertEntity(FarmDocfile entity) {
		Session session = sessionFatory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	public void editEntity(FarmDocfile entity) {
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
	public List<FarmDocfile> selectEntitys(List<DBRule> rules) {
		return selectSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public void updataEntitys(Map<String, Object> values, List<DBRule> rules) {
		updataSqlFromFunction(sessionFatory.getCurrentSession(),
				values, rules);
	}

	public SessionFactory getSessionFatory() {
		return sessionFatory;
	}

	public void setSessionFatory(SessionFactory sessionFatory) {
		this.sessionFatory = sessionFatory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FarmDocfile> getEntityByDocId(String id) {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("SELECT DISTINCT b.* FROM navix_rf_doctextfile a LEFT JOIN navix_docfile b ON a.FILEID=b.ID WHERE  PSTATE='1' AND a.DOCID=? order by b.etime desc");
		sqlquery.setString(0, id);
		sqlquery.addEntity(FarmDocfile.class);
		return sqlquery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FarmDocfile> getEntityByDocIdAndExName(String docid,
			String exname) {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("SELECT DISTINCT b.* FROM navix_rf_doctextfile a LEFT JOIN navix_docfile b ON a.FILEID=b.ID WHERE  PSTATE='1' AND a.DOCID=? AND b.EXNAME=? order by b.etime desc");
		sqlquery.setString(0, docid);
		sqlquery.setString(1, exname);
		sqlquery.addEntity(FarmDocfile.class);
		return sqlquery.list();
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}
	@Override
	protected Class<FarmDocfile> getTypeClass() {
		return FarmDocfile.class;
	}
}
