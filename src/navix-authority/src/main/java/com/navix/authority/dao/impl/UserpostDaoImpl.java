package com.navix.authority.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.navix.authority.dao.UserpostDaoInter;
import com.navix.authority.domain.Userpost;
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.sql.utils.HibernateSQLTools;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/* *
 *功能：用户岗位持久层实现
 *详细：
 *
 *版本：v0.1
 *作者：FarmCode代码工程
 *日期：20141204174206
 *说明：
 */
@Repository
public class UserpostDaoImpl extends HibernateSQLTools<Userpost> implements
		UserpostDaoInter {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFatory;

	@Override
	public void deleteEntity(Userpost userpost) {
		// TODO 自动生成代码,修改后请去除本注释
		Session session = sessionFatory.getCurrentSession();
		session.delete(userpost);
	}

	@Override
	public int getAllListNum() {
		// TODO 自动生成代码,修改后请去除本注释
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("select count(*) from farm_code_field");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	@Override
	public Userpost getEntity(String userpostid) {
		// TODO 自动生成代码,修改后请去除本注释
		Session session = sessionFatory.getCurrentSession();
		return (Userpost) session.get(Userpost.class, userpostid);
	}

	@Override
	public Userpost insertEntity(Userpost userpost) {
		// TODO 自动生成代码,修改后请去除本注释
		Session session = sessionFatory.getCurrentSession();
		session.save(userpost);
		return userpost;
	}

	@Override
	public void editEntity(Userpost userpost) {
		// TODO 自动生成代码,修改后请去除本注释
		Session session = sessionFatory.getCurrentSession();
		session.update(userpost);
	}

	@Override
	public Session getSession() {
		// TODO 自动生成代码,修改后请去除本注释
		return sessionFatory.getCurrentSession();
	}

	@Override
	public DataResult runSqlQuery(DataQuery query) {
		// TODO 自动生成代码,修改后请去除本注释
		try {
			return query.search(sessionFatory.getCurrentSession());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteEntitys(List<DBRule> rules) {
		// TODO 自动生成代码,修改后请去除本注释
		deleteSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public List<Userpost> selectEntitys(List<DBRule> rules) {
		return selectSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public void updataEntitys(Map<String, Object> values, List<DBRule> rules) {
		updataSqlFromFunction(sessionFatory.getCurrentSession(), values, rules);
	}

	@Override
	public int countEntitys(List<DBRule> rules) {
		return countSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	public SessionFactory getSessionFatory() {
		return sessionFatory;
	}

	public void setSessionFatory(SessionFactory sessionFatory) {
		this.sessionFatory = sessionFatory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public List<Userpost> getStandardUserPost(String userId) {
//		Session session = sessionFatory.getCurrentSession();
//		SQLQuery sqlquery = session
//				.createSQLQuery("SELECT a.USERID as userid,a.ID as id,a.ORGANIZATIONID as organizationid,a.POSTID as postid FROM navix_auth_userorgpost a LEFT JOIN navix_auth_post  b ON a.POSTID=b.ID WHERE b.TYPE='1' and a.USERID=?");
//		sqlquery.setString(0, userId);
//		sqlquery.addEntity(Userpost.class);
//		return sqlquery.list();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Userpost> getTempUserPost(String userId) {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session
				.createSQLQuery("SELECT a.USERID as userid,a.ID as id,a.ORGANIZATIONID as organizationid,a.POSTID as postid FROM navix_auth_userorgpost a LEFT JOIN navix_auth_post  b ON a.POSTID=b.ID WHERE b.TYPE='2' and a.USERID=?");
		sqlquery.setString(0, userId);
		sqlquery.addEntity(Userpost.class);
		return sqlquery.list();
	}

	@Override
	protected Class<Userpost> getTypeClass() {
		return Userpost.class;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}
}
