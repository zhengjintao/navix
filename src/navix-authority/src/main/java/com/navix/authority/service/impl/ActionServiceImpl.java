package com.navix.authority.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navix.authority.dao.ActionDaoInter;
import com.navix.authority.dao.ActiontreeDaoInter;
import com.navix.authority.domain.Action;
import com.navix.authority.domain.Actiontree;
import com.navix.authority.domain.AuthKeyImpl;
import com.navix.authority.domain.AuthMenuImpl;
import com.navix.authority.service.ActionServiceInter;
import com.navix.core.auth.domain.AuthKey;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.auth.domain.WebMenu;
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.time.TimeTool;
import com.navix.core.web.easyui.EasyUiTreeNode;

/* *
 *功能：权限资源服务层实现类
 *详细：
 *
 *版本：v0.1
 *作者：王东
 *日期：20141119144919
 *说明：
 */
@Service
public class ActionServiceImpl implements ActionServiceInter {
	@Resource
	private ActionDaoInter actionDaoImpl;
	@Resource
	private ActiontreeDaoInter actiontreeDaoImpl;
	/**
	 * 系统所用权限缓存
	 */
	private Map<String, AuthKey> keyMap = new HashMap<String, AuthKey>();

	@Override
	@Transactional
	public Action insertActionEntity(Action entity, LoginUser user) {
		entity.setCuser(user.getId());
		entity.setCtime(TimeTool.getTimeDate14());
		entity.setMuser(user.getId());
		entity.setUtime(TimeTool.getTimeDate14());
		if (actionDaoImpl.getEntityByKey(entity.getAuthkey()) != null) {
			throw new RuntimeException("key已经存在!");
		}
		keyMap.clear();
		return actionDaoImpl.insertEntity(entity);
	}

	@Override
	@Transactional
	public Action editActionEntity(Action entity, LoginUser user) {
		Action entity2 = actionDaoImpl.getEntity(entity.getId());
		entity2.setMuser(user.getId());
		entity2.setUtime(TimeTool.getTimeDate14());
		entity2.setLoginis(entity.getLoginis());
		entity2.setCheckis(entity.getCheckis());
		entity2.setState(entity.getState());
		entity2.setComments(entity.getComments());
		entity2.setName(entity.getName());
		entity2.setAuthkey(entity.getAuthkey());
		actionDaoImpl.editEntity(entity2);
		Action keyAction = actionDaoImpl.getEntityByKey(entity.getAuthkey());
		if (keyAction != null && !keyAction.getId().equals(entity2.getId())) {
			throw new RuntimeException("key已经存在!");
		}
		keyMap.clear();
		return entity2;
	}

	@Override
	@Transactional
	public void deleteActionEntity(String id, LoginUser user) {
		actionDaoImpl.deleteEntity(actionDaoImpl.getEntity(id));
	}

	@Override
	@Transactional
	public Action getActionEntity(String id) {
		if (id == null) {
			return null;
		}
		return actionDaoImpl.getEntity(id);
	}

	@Override
	@Transactional
	public DataQuery createActionSimpleQuery(DataQuery query) {
		DataQuery dbQuery = DataQuery
				.init(query, "navix_AUTH_ACTION",
						"ID,LOGINIS,CHECKIS,STATE,MUSER,CUSER,UTIME,CTIME,COMMENTS,NAME,AUTHKEY");
		return dbQuery;
	}

	@Override
	@Transactional
	public Actiontree insertActiontreeEntity(Actiontree entity, LoginUser user,
			String authkey) {
		entity.setCuser(user.getId());
		entity.setCtime(TimeTool.getTimeDate14());
		entity.setUuser(user.getId());
		entity.setUtime(TimeTool.getTimeDate14());
		entity.setState("1");
		if (entity.getParentid() == null) {
			entity.setParentid("NONE");
		}
		// 类型为结构菜单则不设置权限
		if (entity.getType().equals("1")) {
			entity.setActionid(null);
		} else {
			// 如果传入了id标准用户选择了一个已有的权限
			if (entity.getActionid() == null
					|| entity.getActionid().trim().length() <= 0) {
				// 如果没有id则为手填的
				// 新建立action
				Action action = new Action();
				action.setAuthkey(authkey);
				String actionName = "";
				if (!entity.getParentid().equals("NONE")) {
					actionName = actiontreeDaoImpl.getEntity(
							entity.getParentid()).getName()
							+ '_' + entity.getName();
				} else {
					actionName = entity.getName();
				}
				action.setName(actionName);
				action.setCheckis("1");
				action.setLoginis("1");
				action.setState("1");
				entity.setActionid(insertActionEntity(action, user).getId());
			}
		}
		entity.setTreecode("NONE");
		entity = actiontreeDaoImpl.insertEntity(entity);
		initTreeCode(entity.getId());
		return entity;
	}

	@Override
	@Transactional
	public Actiontree editActiontreeEntity(Actiontree entity, LoginUser user,
			String authkey) {
		Actiontree entity2 = actiontreeDaoImpl.getEntity(entity.getId());
		entity2.setUuser(user.getId());
		entity2.setUtime(TimeTool.getTimeDate14());
		entity2.setParams(entity.getParams());
		entity2.setImgid(entity.getImgid());
		entity2.setIcon(entity.getIcon());
		entity2.setActionid(entity.getActionid());
		entity2.setState(entity.getState());
		entity2.setType(entity.getType());
		entity2.setComments(entity.getComments());
		entity2.setName(entity.getName());
		entity2.setSort(entity.getSort());
		// 类型为结构菜单则不设置权限
		if (entity.getType().equals("1")) {
			entity.setActionid(null);
		} else {
			// 如果传入了id标准用户选择了一个已有的权限
			if (entity.getActionid() == null
					|| entity.getActionid().trim().length() <= 0) {
				// 如果没有id则为手填的
				// 新建立action
				Action action = new Action();
				action.setAuthkey(authkey);
				String actionName = "";
				if (!entity.getParentid().equals("NONE")) {
					actionName = actiontreeDaoImpl.getEntity(
							entity.getParentid()).getName()
							+ '_' + entity.getName();
				} else {
					actionName = entity.getName();
				}
				action.setName(actionName);
				action.setCheckis("1");
				action.setLoginis("1");
				entity.setActionid(insertActionEntity(action, user).getId());
			}
		}
		actiontreeDaoImpl.editEntity(entity2);
		return entity2;
	}

	@Override
	@Transactional
	public void deleteActiontreeEntity(String id, LoginUser user) {
		// TODO 自动生成代码,修改后请去除本注释
		if (actiontreeDaoImpl.selectEntitys(
				DBRule.addRule(new ArrayList<DBRule>(), "parentid", id, "="))
				.size() > 0) {
			throw new RuntimeException("不能删除该节点，请先删除其子节点");
		}
		actiontreeDaoImpl.deleteEntity(actiontreeDaoImpl.getEntity(id));
	}

	@Override
	@Transactional
	public Actiontree getActiontreeEntity(String id) {
		// TODO 自动生成代码,修改后请去除本注释
		if (id == null) {
			return null;
		}
		return actiontreeDaoImpl.getEntity(id);
	}

	@Override
	@Transactional
	public DataQuery createActiontreeSimpleQuery(DataQuery query) {
		// TODO 自动生成代码,修改后请去除本注释
		DataQuery dbQuery = DataQuery
				.init(query,
						"navix_AUTH_ACTIONTREE",
						"ID,PARAMS,IMGID,ICON,DOMAIN,ACTIONID,STATE,UUSER,CUSER,UTIME,CTIME,TYPE,COMMENTS,TREECODE,NAME,PARENTID,SORT");
		return dbQuery;
	}

	@Override
	@Transactional
	public List<EasyUiTreeNode> getSyncTree(String parentId, String domain) {
		if (parentId == null) {
			parentId = "NONE";
		}
		if (domain == null) {
			domain = "alone";
		}
		return EasyUiTreeNode
				.formatAsyncAjaxTree(
						EasyUiTreeNode
								.queryTreeNodeOne(
										parentId,
										"SORT",
										"(SELECT c.NAME AS NAME,SORT,c.ID AS ID,PARENTID,ICON,b.AUTHKEY as URL,c.PARAMS as PARAM,domain FROM navix_auth_actiontree c LEFT JOIN navix_auth_action b ON c.ACTIONID=b.ID)",
										"ID", "PARENTID", "NAME", "ICON",
										"and a.DOMAIN='" + domain + "'",
										"URL,PARAM").getResultList(),
						EasyUiTreeNode
								.queryTreeNodeTow(parentId, "SORT",
										"navix_auth_actiontree", "ID",
										"PARENTID", "NAME", "ICON",
										"and a.DOMAIN='" + domain + "'")
								.getResultList(), "PARENTID", "ID", "NAME",
						"ICON");
	}

	@Override
	@Transactional
	public void moveActionTreeNode(String treeNodeId, String targetTreeNodeId) {
		// 移动节点
		Actiontree node = getActiontreeEntity(treeNodeId);
		if (node.getParentid().equals("NONE")) {
			throw new RuntimeException("不能够移动根节点!");
		}
		Actiontree target = getActiontreeEntity(targetTreeNodeId);
		if (target.getTreecode().indexOf(node.getTreecode()) >= 0) {
			throw new RuntimeException("不能够移动到其子节点下!");
		}
		node.setParentid(targetTreeNodeId);
		actiontreeDaoImpl.editEntity(node);
		// 构造所有树TREECODE
		List<Actiontree> list = actiontreeDaoImpl.getAllSubNodes(treeNodeId);
		for (Actiontree action : list) {
			action.setDomain(target.getDomain());
			actiontreeDaoImpl.editEntity(action);
			initTreeCode(action.getId());
		}
	}

	private void initTreeCode(String treeNodeId) {
		Actiontree node = getActiontreeEntity(treeNodeId);
		if (node.getParentid().equals("NONE")) {
			node.setTreecode(node.getId());
		} else {
			node.setTreecode(actiontreeDaoImpl.getEntity(node.getParentid())
					.getTreecode() + node.getId());
		}
		actiontreeDaoImpl.editEntity(node);
	}

	@Override
	@Transactional
	public AuthKey getCacheAction(String key) {
		if (keyMap.size() <= 0) {
			List<Action> list = actionDaoImpl.getAllEntity();
			for (Action node : list) {
				AuthKeyImpl authkey = new AuthKeyImpl();
				authkey.setIscheck(node.getCheckis().equals("1"));
				authkey.setIslogin(node.getLoginis().equals("1"));
				authkey.setUseAble(node.getState().equals("1"));
				authkey.setTitle(node.getName());
				keyMap.put(node.getAuthkey(), authkey);
			}
		}
		return keyMap.get(key);
	}

	@Override
	@Transactional
	public List<WebMenu> getAllMenus() {
		DataQuery query = DataQuery
				.getInstance(
						1,
						"SORT,ID,PARENTID,NAME,TYPE,STATE,ICON,IMGID,PARAMS,AUTHKEY",
						"(SELECT c.SORT,c.ID,c.PARENTID,c.NAME,c.TYPE,c.STATE,c.ICON,c.IMGID,c.PARAMS,d.AUTHKEY FROM  navix_auth_actiontree c LEFT JOIN navix_auth_action d ON d.ID=c.ACTIONID WHERE (d.STATE = '1' or d.STATE IS NULL) and c.type!='3' order by LENGTH(c.TREECODE),c.SORT asc) e");
		List<WebMenu> menus = new ArrayList<WebMenu>();
		query.setPagesize(1000);
		query.setNoCount();
		query.setDistinct(true);
		try {
			for (Map<String, Object> map : query.search().getResultList()) {
				AuthMenuImpl node = new AuthMenuImpl();
				node.setIcon(map.get("ICON") != null ? map.get("ICON")
						.toString() : null);
				node.setId(map.get("ID") != null ? map.get("ID").toString()
						: null);
				node.setName(map.get("NAME") != null ? map.get("NAME")
						.toString() : null);
				node.setParams(map.get("PARAMS") != null ? map.get("PARAMS")
						.toString() : null);
				node.setParentid(map.get("PARENTID") != null ? map.get(
						"PARENTID").toString() : null);
				node.setUrl(map.get("AUTHKEY") != null ? map.get("AUTHKEY")
						.toString() : null);
				menus.add(node);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return menus;
	}

	@Override
	@Transactional
	public List<Action> getAllActions() {
		DataQuery dbQuery = DataQuery.getInstance(1,
				"d.id,d.AUTHKEY,d.NAME,d.COMMENTS,d.STATE,d.CHECKIS,d.LOGINIS",
				" navix_auth_action d ");
		dbQuery.addRule(new DBRule("d.STATE", "1", "="));
		dbQuery.setDistinct(true);
		dbQuery.setNoCount();
		dbQuery.setPagesize(5000);
		List<Action> list = new ArrayList<Action>();
		try {
			for (Map<String, Object> node : dbQuery.search().getResultList()) {
				Action action = new Action();
				action.setAuthkey(node.get("D_AUTHKEY") != null ? node.get(
						"D_AUTHKEY").toString() : null);
				action.setId(node.get("D_ID") != null ? node.get("D_ID")
						.toString() : null);
				action.setName(node.get("D_NAME") != null ? node.get("D_NAME")
						.toString() : null);
				action.setComments(node.get("D_COMMENTS") != null ? node.get(
						"D_COMMENTS").toString() : null);
				action.setState(node.get("D_STATE") != null ? node.get(
						"D_STATE").toString() : null);
				action.setCheckis(node.get("D_CHECKIS") != null ? node.get(
						"D_CHECKIS").toString() : null);
				action.setLoginis(node.get("D_LOGINIS") != null ? node.get(
						"D_LOGINIS").toString() : null);
				list.add(action);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return list;
	}

	public ActionDaoInter getActionDaoImpl() {
		return actionDaoImpl;
	}

	public void setActionDaoImpl(ActionDaoInter actionDaoImpl) {
		this.actionDaoImpl = actionDaoImpl;
	}

	public ActiontreeDaoInter getActiontreeDaoImpl() {
		return actiontreeDaoImpl;
	}

	public void setActiontreeDaoImpl(ActiontreeDaoInter actiontreeDaoImpl) {
		this.actiontreeDaoImpl = actiontreeDaoImpl;
	}

}
