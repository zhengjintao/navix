package  com.navix.doc.dao.impl;

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
import com.navix.doc.dao.FarmDocgroupUserDaoInter;
import com.navix.doc.domain.FarmDocgroupUser;

/**店铺成员
 * @author MAC_wd
 * 
 */
@Repository
public class FarmDocgroupUserDao extends HibernateSQLTools<FarmDocgroupUser> implements  FarmDocgroupUserDaoInter {
	@Resource(name = "sessionFactory") 
	private SessionFactory sessionFatory;

	public void deleteEntity(FarmDocgroupUser entity) {
		Session session=sessionFatory.getCurrentSession();
		session.delete(entity);
	}
	public int getAllListNum(){
		Session session= sessionFatory.getCurrentSession();
		SQLQuery sqlquery= session.createSQLQuery("select count(*) from navix_docgroup_user");
		BigInteger num=(BigInteger)sqlquery.list().get(0);
		return num.intValue() ;
	}
	public FarmDocgroupUser getEntity(String id) {
		Session session= sessionFatory.getCurrentSession();
		return (FarmDocgroupUser)session.get(FarmDocgroupUser.class, id);
	}
	public FarmDocgroupUser insertEntity(FarmDocgroupUser entity) {
		Session session= sessionFatory.getCurrentSession();
		session.save(entity);
		return entity;
	}
	public void editEntity(FarmDocgroupUser entity) {
		Session session= sessionFatory.getCurrentSession();
		session.update(entity);
	}
	
	@Override
	public Session getSession() {
		return sessionFatory.getCurrentSession();
	}
	public DataResult runSqlQuery(DataQuery query){
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
	public List<FarmDocgroupUser> selectEntitys(List<DBRule> rules) {
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
	
	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}
	@Override
	protected Class<FarmDocgroupUser> getTypeClass() {
		return FarmDocgroupUser.class;
	}
}
