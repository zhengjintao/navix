package com.navix.authority.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.navix.authority.dao.OrganizationDaoInter;
import com.navix.authority.dao.PostDaoInter;
import com.navix.authority.dao.PostactionDaoInter;
import com.navix.authority.dao.UserDaoInter;
import com.navix.authority.dao.UserpostDaoInter;
import com.navix.authority.domain.Organization;
import com.navix.authority.domain.Post;
import com.navix.authority.domain.Postaction;
import com.navix.authority.service.OrganizationServiceInter;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DBSort;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.time.TimeTool;
import com.navix.core.web.easyui.EasyUiTreeNode;

/* *
 *功能：组织机构服务实现类
 *详细：
 *
 *版本：v0.1
 *作者：Farm代码工程自动生成
 *日期：20141122211253
 *说明：
 */
@Service
public class OrganizationServiceImpl implements OrganizationServiceInter {
	@Resource
	private OrganizationDaoInter organizationDao;
	@Resource
	private PostDaoInter postDao;
	@Resource
	private UserpostDaoInter userpostDao;
	@Resource
	private PostactionDaoInter postactionDao;
	@Resource
	private UserDaoInter userDao;

	@Override
	@Transactional
	public Organization insertOrganizationEntity(Organization entity, LoginUser user) {
		entity.setCuser(user.getId());
		entity.setCtime(TimeTool.getTimeDate14());
		entity.setMuser(user.getId());
		entity.setUtime(TimeTool.getTimeDate14());
		entity.setState("1");
		if (entity.getParentid() == null) {
			entity.setParentid("NONE");
		}
		entity.setTreecode("NONE");
		entity = organizationDao.insertEntity(entity);
		initTreeCode(entity.getId());
		return entity;
	}

	@Override
	@Transactional
	public Organization editOrganizationEntity(Organization entity, LoginUser user) {
		Organization entity2 = organizationDao.getEntity(entity.getId());
		entity2.setMuser(user.getId());
		entity2.setUtime(TimeTool.getTimeDate14());
		entity2.setType(entity.getType());
		entity2.setSort(entity.getSort());
		entity2.setState(entity.getState());
		entity2.setComments(entity.getComments());
		entity2.setName(entity.getName());
		organizationDao.editEntity(entity2);
		return entity2;
	}

	@Override
	@Transactional
	public void deleteOrganizationEntity(String id, LoginUser user) {
		if (organizationDao.selectEntitys(DBRule.addRule(new ArrayList<DBRule>(), "parentid", id, "=")).size() > 0) {
			throw new RuntimeException("不能删除该节点，请先删除其子节点");
		}
		// 删除岗位
		for (Post post : postDao.selectEntitys(new DBRule("ORGANIZATIONID", id, "=").getDBRules())) {
			deletePostEntity(post.getId(), user);
		}
		// 删除组织机构
		organizationDao.deleteEntity(organizationDao.getEntity(id));
	}

	@Override
	@Transactional
	public Organization getOrganizationEntity(String id) {
		if (id == null) {
			return null;
		}
		return organizationDao.getEntity(id);
	}

	@Override
	@Transactional
	public DataQuery createOrganizationSimpleQuery(DataQuery query) {
		DataQuery dbQuery = DataQuery.init(query, "navix_AUTH_ORGANIZATION",
				"ID,TYPE,SORT,PARENTID,MUSER,CUSER,STATE,UTIME,CTIME,COMMENTS,NAME,TREECODE");
		return dbQuery;
	}

	@Override
	@Transactional
	public void deletePostEntity(String id, LoginUser user) {
		String[] ids = id.split(",");
		for (String id1 : ids) {
			// 同时删除岗位用户
			userpostDao.deleteEntitys(new DBRule("POSTID", id1, "=").getDBRules());
			// 同时删除岗位权限
			postactionDao.deleteEntitys(new DBRule("POSTID", id1, "=").getDBRules());
			postDao.deleteEntity(postDao.getEntity(id1));
		}
	}

	@Override
	@Transactional
	public Post getPostEntity(String id) {
		if (id == null) {
			return null;
		}
		return postDao.getEntity(id);
	}

	@Override
	@Transactional
	public DataQuery createPostSimpleQuery(DataQuery query) {
		DataQuery dbQuery = DataQuery.init(query, "navix_AUTH_POST",
				"ID,EXTENDIS,NAME,ORGANIZATIONID,PSTATE,EUSER,EUSERNAME,CUSER,CUSERNAME,ETIME,CTIME");
		return dbQuery;
	}

	@Override
	@Transactional
	public void moveOrgTreeNode(String orgId, String targetOrgId) {
		String[] orgIds = orgId.split(",");
		for (int i = 0; i < orgIds.length; i++) {
			// 移动节点
			Organization node = getOrganizationEntity(orgIds[i]);
			// if (node.getParentid().equals("NONE")) {
			// throw new RuntimeException("不能够移动根节点!");
			// }
			Organization target = getOrganizationEntity(targetOrgId);
			if (target.getTreecode().indexOf(node.getTreecode()) >= 0) {
				throw new RuntimeException("不能够移动到其子节点下!");
			}
			node.setParentid(targetOrgId);
			organizationDao.editEntity(node);
			// 构造所有树TREECODE
			List<Organization> list = organizationDao.getAllSubNodes(orgIds[i]);
			for (Organization org : list) {
				initTreeCode(org.getId());
			}
		}
	}

	private void initTreeCode(String treeNodeId) {
		Organization node = getOrganizationEntity(treeNodeId);
		if (node.getParentid().equals("NONE")) {
			node.setTreecode(node.getId());
		} else {
			node.setTreecode(organizationDao.getEntity(node.getParentid()).getTreecode() + node.getId());
		}
		organizationDao.editEntity(node);
	}

	@Override
	@Transactional
	public Post editPost(String postId, String postname, String extendis, LoginUser user) {
		// 更新岗位
		Post post = postDao.getEntity(postId);
		post.setName(postname);
		post.setExtendis(extendis);
		post.setEtime(TimeTool.getTimeDate14());
		post.setEuser(user.getId());
		post.setEusername(user.getName());
		postDao.editEntity(post);

		// 如果子机构设为不可用，则删除用户和该岗位的关系
		userpostDao.deleteEntitys(new DBRule("POSTID", post.getId(), "=").getDBRules());
		return post;
	}

	@Override
	@Transactional
	public Post insertPost(String orgId, String postname, String extendis, LoginUser user) {
		Post post = new Post();
		post.setName(postname);
		post.setExtendis(extendis);
		post.setCtime(TimeTool.getTimeDate14());
		post.setCuser(user.getId());
		post.setCusername(user.getName());
		post.setEtime(TimeTool.getTimeDate14());
		post.setEuser(user.getId());
		post.setEusername(user.getName());
		post.setOrganizationid(orgId);
		post.setPstate("1");
		return postDao.insertEntity(post);
	}

	@Override
	@Transactional
	public List<EasyUiTreeNode> loadPostTree(String ids) {
		if (ids == null || ids.trim().length() <= 0) {
			ids = "NONE";
		}
		DataQuery query = DataQuery.getInstance("1", "NAME,PARENTID,ID,UTYPE",
				"(SELECT NAME,PARENTID,ID,'11' AS UTYPE,SORT FROM navix_auth_organization  UNION SELECT NAME,ORGANIZATIONID AS PARENTID,ID,TYPE AS UTYPE,1000 as SORT FROM navix_auth_post  ) a ");
		query.setPagesize(1000);
		query.addRule(new DBRule("PARENTID", ids, "="));
		query.setNoCount();
		query.addSort(new DBSort("UTYPE", "asc"));
		query.addSort(new DBSort("SORT", "asc"));
		DataQuery query2 = DataQuery.getInstance("1",
				"a.NAME as NAME,a.PARENTID as PARENTID,a.ID as ID,a.UTYPE as UTYPE",
				"navix_auth_organization b LEFT JOIN (SELECT NAME,PARENTID,ID,'0' AS UTYPE FROM navix_auth_organization  UNION SELECT NAME,ORGANIZATIONID AS PARENTID,ID,TYPE AS UTYPE FROM navix_auth_post  ) a ON a.PARENTID=b.ID");
		query2.setPagesize(1000);
		query2.addRule(new DBRule("b.PARENTID", ids, "="));
		query2.setNoCount();
		List<EasyUiTreeNode> list = null;
		try {
			List<Map<String, Object>> listOne = query.search().getResultList();
			for (Map<String, Object> node : listOne) {
				if (node.get("UTYPE").equals("1")) {
					node.put("UTYPE", "icon-suppliers");
				}
				if (node.get("UTYPE").equals("2")) {
					node.put("UTYPE", "icon-user_medical");
				}
			}
			list = EasyUiTreeNode.formatAsyncAjaxTree(listOne, query2.search().getResultList(), "PARENTID", "ID",
					"NAME", "UTYPE");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	@Transactional
	public void addUserPost(String userId, String postId, LoginUser currentUser) {
		// User user = userDao.getEntity(userId);
		// if (user.getState().equals("2")) {
		// throw new RuntimeException("该用户已经删除");
		// }
		// Post post = postDao.getEntity(postId);
		// if (post == null) {
		// throw new RuntimeException("请选择正确的岗位");
		// }
		// // 先判断岗位是否标准岗1:标准2:临时
		// if (post.getType().equals("1")) {
		// // 标准，清理掉用户其他的标准岗
		// for (Userpost userpost : userpostDao.getStandardUserPost(userId)) {
		// userpostDao.deleteEntity(userpost);
		// }
		// }
		// for (Userpost userpost : userpostDao.getTempUserPost(userId)) {
		// if (userpost.getUserid().equals(userId) &&
		// userpost.getPostid().equals(postId)) {
		// return;
		// }
		// }
		// // 添加岗位
		// Userpost userpost = new Userpost();
		// userpost.setOrganizationid(post.getOrganizationid());
		// userpost.setPostid(postId);
		// userpost.setUserid(userId);
		// userpostDao.insertEntity(userpost);
	}

	// ----------------------------------------------------------------------------------
	public OrganizationDaoInter getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDaoInter dao) {
		this.organizationDao = dao;
	}

	public PostDaoInter getPostDao() {
		return postDao;
	}

	public PostactionDaoInter getPostactionDao() {
		return postactionDao;
	}

	public void setPostactionDao(PostactionDaoInter postactionDao) {
		this.postactionDao = postactionDao;
	}

	public void setPostDao(PostDaoInter postDao) {
		this.postDao = postDao;
	}

	public UserpostDaoInter getUserpostDao() {
		return userpostDao;
	}

	public void setUserpostDao(UserpostDaoInter userpostDao) {
		this.userpostDao = userpostDao;
	}

	public UserDaoInter getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoInter userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void removePostUsers(String postId, String userid, LoginUser currentUser) {
		String[] userIds = userid.split(",");
		for (String userId : userIds) {
			userpostDao.deleteEntitys(new DBRule("USERID", userId, "=").addRule("POSTID", postId, "=").getDBRules());
		}
	}

	@Override
	@Transactional
	public void setPostActionTree(List<String> actionTreeIds, String postId) {
		if (postId == null || postId.trim().length() <= 0) {
			throw new IllegalArgumentException("请选择一个岗位");
		}
		// 删除之前的菜单
		postactionDao.deleteEntitys(new DBRule("POSTID", postId, "=").getDBRules());
		for (String nodeId : actionTreeIds) {
			Postaction postaction = new Postaction();
			postaction.setMenuid(nodeId);
			postaction.setPostid(postId);
			postactionDao.insertEntity(postaction);
		}
	}

	@Override
	@Transactional
	public List<Organization> getTree() {
		return organizationDao.selectEntitys(new DBRule("1", "1", "=").getDBRules());
	}

	@Override
	public List<Map<String, Object>> getPostList(String orgId) {
		try {
			DataQuery query = DataQuery.getInstance(1,
					"POST.ID AS POSTID, POST.NAME AS POSTNAME, ORG.ID AS ORGID, ORG.NAME AS ORGNAME ",
					"navix_AUTH_POST POST " + "INNER JOIN navix_AUTH_ORGANIZATION ORG ON POST.ORGANIZATIONID = ORG.ID");
			query.setNoCount();
			query.addRule(new DBRule("POST.ORGANIZATIONID", orgId, "="));
			DataResult result = query.search();
			List<Map<String, Object>> list = result.getResultList();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getPostListWithPOrgPost(String orgId) {
		if (orgId == null || orgId.isEmpty()) {
			return new ArrayList<Map<String, Object>>();
		}

		Organization entity = organizationDao.getEntity(orgId);
		String treecode = entity.getTreecode();
		String pOrgIds = "";
		for (int i = 1; i <= (treecode.length() - 32) / 32; i++) {
			pOrgIds += "'" + treecode.substring((i - 1) * 32, i * 32) + "'";
			if (i <= (treecode.length() - 64) / 32) {
				pOrgIds += ",";
			}
		}
		if (pOrgIds.isEmpty()) {
			pOrgIds = "''";
		}

		try {
			DataQuery query = DataQuery.getInstance(1,
					"a.POSTID AS POSTID, a.POSTNAME AS POSTNAME, a.ORGID AS ORGID, a.ORGNAME AS ORGNAME ",
					"(SELECT PPOST.ID AS POSTID, PPOST.NAME AS POSTNAME, PORG.ID AS ORGID, PORG.NAME AS ORGNAME "
							+ "FROM navix_AUTH_ORGANIZATION PORG "
							+ "LEFT JOIN navix_AUTH_POST PPOST ON PORG.ID = PPOST.ORGANIZATIONID "
							+ "WHERE PPOST.EXTENDIS = '1' AND PORG.ID IN (" + pOrgIds + ") /* 查找父机构可用的岗位 */"
							+ "UNION ALL "
							+ "SELECT POST.ID AS POSTID, POST.NAME AS POSTNAME, ORG.ID AS ORGID, ORG.NAME AS ORGNAME "
							+ "FROM navix_AUTH_ORGANIZATION ORG "
							+ "LEFT JOIN navix_AUTH_POST POST ON ORG.ID = POST.ORGANIZATIONID " + "WHERE ORG.ID = '"
							+ orgId + "' /* 查找当前机构所有的岗位 */) a ");
			query.setNoCount();
			query.setPagesize(1000);
			DataResult result = query.search();
			List<Map<String, Object>> list = result.getResultList();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public List<Organization> getParentOrgs(String orgid) {
		String id = orgid;
		List<Organization> orgs = new ArrayList<Organization>();
		while (id != null) {
			Organization centity = getOrganizationEntity(id);
			if (centity == null || centity.getParentid() == null || centity.getParentid().trim().length() <= 0) {
				id = null;
			} else {
				id = centity.getParentid();
			}
			if (centity != null) {
				orgs.add(centity);
			}
		}
		Collections.reverse(orgs);
		return orgs;
	}

	@Override
	public List<String> getOrgUsers(String orgid) {
		DataQuery query = DataQuery.getInstance(1, "a.id as id ,a.name as name",
				"navix_AUTH_USER a left join navix_AUTH_USERORG b on b.USERID=a.id left join navix_AUTH_ORGANIZATION c on c.ID=b.ORGANIZATIONID");
		query.setPagesize(1000);
		Organization org = getOrganizationEntity(orgid);
		query.addSqlRule(" and c.TREECODE like '" + org.getTreecode() + "%'");
		List<String> list = new ArrayList<>();
		try {
			for (Map<String, Object> node : query.search().getResultList()) {
				list.add((String) node.get("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}

}