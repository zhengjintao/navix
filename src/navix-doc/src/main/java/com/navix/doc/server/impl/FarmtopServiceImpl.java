package com.navix.doc.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.time.TimeTool;
import com.navix.doc.dao.FarmtopDaoInter;
import com.navix.doc.domain.Farmtop;
import com.navix.doc.server.FarmtopServiceInter;

/* *
 *功能：置顶文档服务层实现类
 *详细：
 */
@Service
public class FarmtopServiceImpl implements FarmtopServiceInter {
	@Resource
	private FarmtopDaoInter farmtopDaoImpl;


	@Override
	@Transactional
	public Farmtop insertFarmtopEntity(Farmtop entity, LoginUser user) {
		entity.setCtime(TimeTool.getTimeDate14());
		entity.setCuser(user.getId());
		entity.setCusername(user.getName());
		entity.setPstate("1");
		return farmtopDaoImpl.insertEntity(entity);
	}

	@Override
	@Transactional
	public Farmtop editFarmtopEntity(Farmtop entity, LoginUser user) {
		Farmtop entity2 = farmtopDaoImpl.getEntity(entity.getId());
		entity2.setSort(entity.getSort());
		entity2.setDocid(entity.getDocid());
		entity2.setPcontent(entity.getPcontent());
		farmtopDaoImpl.editEntity(entity2);
		return entity2;
	}

	@Override
	@Transactional
	public void deleteFarmtopEntity(String id, LoginUser user) {

		farmtopDaoImpl.deleteEntity(farmtopDaoImpl.getEntity(id));
	}

	@Override
	@Transactional
	public Farmtop getFarmtopEntity(String id) {

		if (id == null) {
			return null;
		}
		return farmtopDaoImpl.getEntity(id);
	}

	@Override
	@Transactional
	public DataQuery createFarmtopSimpleQuery(DataQuery query) {
		DataQuery dbQuery = DataQuery
				.init(query,
						"NAVIX_TOP TOP "
								+ "LEFT JOIN NAVIX_DOC A ON TOP.DOCID = A.ID and a.STATE='1'"
								+ "LEFT JOIN NAVIX_RF_DOCTYPE B ON B.DOCID =A.ID "
								+ "LEFT JOIN NAVIX_DOCTYPE C ON C.ID=B.TYPEID "
								+ "LEFT JOIN NAVIX_DOCGROUP D ON D.ID=A.DOCGROUPID "
								+ "LEFT JOIN NAVIX_DOCRUNINFO DOCRUNINFO ON A.RUNINFOID = DOCRUNINFO.ID",
						"TOP.ID AS ID, TOP.SORT AS SORT, A.DOCDESCRIBE AS DOCDESCRIBE,A.WRITEPOP AS WRITEPOP,"
						+ "A.READPOP AS READPOP,A.TITLE AS TITLE,A.AUTHOR AS AUTHOR,A.PUBTIME AS PUBTIME,"
						+ "A.DOMTYPE AS DOMTYPE,A.SHORTTITLE AS SHORTTITLE,A.TAGKEY AS TAGKEY,A.STATE AS STATE,"
						+ "D.GROUPNAME AS GROUPNAME, DOCRUNINFO.VISITNUM AS VISITNUM, DOCRUNINFO.ANSWERINGNUM AS ANSWERINGNUM, "
						+ "A.IMGID AS IMGID, A.ID AS DOCID");
		return dbQuery;

	}


	@Override
	public DataQuery docTopChooseDocList(DataQuery query) {
		DataQuery dbQuery = DataQuery
				.init(query,
						"NAVIX_DOC  A "
								+ "LEFT JOIN NAVIX_RF_DOCTYPE B ON B.DOCID =A.ID "
								+ "LEFT JOIN NAVIX_DOCTYPE C ON C.ID=B.TYPEID "
								+ "LEFT JOIN NAVIX_docgroup d ON d.ID=a.DOCGROUPID",
						"A.ID AS ID,A.DOCDESCRIBE as DOCDESCRIBE,A.WRITEPOP as WRITEPOP,A.READPOP as READPOP,A.TITLE AS TITLE,A.AUTHOR AS AUTHOR,A.PUBTIME AS PUBTIME,A.DOMTYPE AS DOMTYPE,A.SHORTTITLE AS SHORTTITLE,A.TAGKEY AS TAGKEY,A.STATE AS STATE,D.GROUPNAME AS GROUPNAME ");
		
		dbQuery.addSqlRule(" AND NOT EXISTS (SELECT 1 FROM NAVIX_TOP TOP_ WHERE A.ID = TOP_.DOCID) ");
		return dbQuery;

	}

}
