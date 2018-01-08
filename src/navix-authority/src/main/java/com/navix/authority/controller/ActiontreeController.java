package com.navix.authority.controller;

import com.navix.authority.domain.Action;
import com.navix.authority.domain.Actiontree;
import com.navix.authority.service.ActionServiceInter;
import com.navix.core.page.RequestMode;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.core.web.easyui.EasyUiTreeNode;
import com.navix.core.web.easyui.EasyUiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/* *
 *功能：构造权限控制层
 *详细：
 */
@RequestMapping("/actiontree")
@Controller
public class ActiontreeController extends WebUtils {
	private final static Logger log = Logger
			.getLogger(ActiontreeController.class);
	@Resource
	ActionServiceInter actionServiceImpl;

	@RequestMapping("/list")
	public ModelAndView index(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView(
				"authority/ActiontreeResult");
	}

	@RequestMapping("/icons")
	public ModelAndView icons(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView(
				"authority/ActionCssIcon");
	}

	@RequestMapping("/actionsPage")
	public ModelAndView actionsPage(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView(
				"authority/ActionChoose");
	}

	@RequestMapping("/treePage")
	public ModelAndView treePage(HttpSession session, String ids) {
		return ViewMode.getInstance().putAttr("ids", ids)
				.returnModelAndView("authority/ActiontreenodeChooseTreeWin");
	}

	/**
	 * 查询所有主菜单结果集合
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryall(DataQuery query,
			HttpServletRequest request) {
		try {
			query = EasyUiUtils.formatGridQuery(request, query);
			if (query.getQueryRule().size() == 0) {
				query.addRule(new DBRule("PARENTID", "NONE", "="));
			}
			DataResult result = actionServiceImpl.createActiontreeSimpleQuery(
					query).search();
			result.runDictionary("1:分类,2:菜单,3:权限", "TYPE");
			result.runDictionary("1:可用,0:禁用", "STATE");
			return ViewMode.getInstance()
					.putAttrs(EasyUiUtils.formatGridData(result))
					.returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}

	}

	/**
	 * 加载页面所有菜单
	 * 
	 * @return
	 */
	@RequestMapping("/queryMenu")
	@ResponseBody
	public Map<String, Object> loadAllMenu() {
		try {
			DataQuery query = DataQuery
					.getInstance(
							1,
							"ACT_TREE.NAME AS NAME, ACT_KEY.AUTHKEY AS SRCKEY, ACT_TREE.ID AS ID,ACT_TREE.PARENTID AS PARENTID",
							"navix_auth_actiontree AS act_tree left join  navix_auth_action AS act_key on act_tree.ACTIONID=act_key.ID");
			query.setPagesize(100);
			return ViewMode.getInstance()
					.putAttrs(EasyUiUtils.formatGridData(query.search()))
					.returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 提交修改数据
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> editSubmit(Actiontree entity, Action action,
			HttpSession session) {
		try {
			entity = actionServiceImpl.editActiontreeEntity(entity,
					getCurrentUser(session), action.getAuthkey());

			return ViewMode.getInstance().putAttr("entity", entity)
					.returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 提交新增数据
	 * 
	 * @param entity
	 *            表单
	 * @param authkey
	 *            权限uri
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addSubmit(Actiontree entity, String authkey,
			HttpSession session) {
		try {
			entity = actionServiceImpl.insertActiontreeEntity(entity,
					getCurrentUser(session), authkey);
			return ViewMode.getInstance().putAttr("entity", entity)
					.returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delSubmit(String ids, HttpSession session) {
		try {
			for (String id : parseIds(ids)) {
				actionServiceImpl.deleteActiontreeEntity(id,
						getCurrentUser(session));
			}
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 显示详细信息（修改或浏览时）
	 * 
	 * @return
	 */
	@RequestMapping("/form")
	public ModelAndView view(RequestMode pageset, String domain, String ids,
			String parentid) {
		ViewMode mode = ViewMode.getInstance();
		try {
			Actiontree parent = new Actiontree();
			Actiontree entity = null;
			switch (pageset.getOperateType()) {
			case (1): {// 新增
				break;
			}
			case (0): {// 展示
				entity = actionServiceImpl.getActiontreeEntity(ids);
				mode.putAttr("entity", entity);
				break;
			}
			case (2): {// 修改
				entity = actionServiceImpl.getActiontreeEntity(ids);
				mode.putAttr("entity", entity);
				break;
			}
			default:
				break;
			}
			if (parentid != null && parentid.trim().length() > 0) {
				parent = actionServiceImpl.getActiontreeEntity(parentid);
				mode.putAttr("parent", parent);
			}
			if (entity != null && entity.getActionid() != null) {
				Action treeAction = actionServiceImpl.getActionEntity(entity
						.getActionid());
				mode.putAttr("treeAction", treeAction);
			}
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e + e.getMessage())
					.returnModelAndView("authority/ActiontreeForm");
		}
		return mode.putAttr("pageset", pageset).putAttr("domain", domain)
				.returnModelAndView("authority/ActiontreeForm");
	}

	/**
	 * 移动节点
	 * 
	 * @return
	 */
	@RequestMapping("/move")
	@ResponseBody
	public Map<String, Object> moveTreeNodeSubmit(String oid, String deid) {
		try {
			actionServiceImpl.moveActionTreeNode(oid, deid);
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 加载子节点
	 * 
	 * @param id
	 * @param domain
	 * @return
	 */
	@RequestMapping("/loadtree")
	@ResponseBody
	public List<EasyUiTreeNode> loadTreeNode(String id, String domain) {
		try {
			return actionServiceImpl.getSyncTree(id, domain);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ArrayList<EasyUiTreeNode>();
		}
	}

	/**
	 * 加载树节点根据用户权限
	 * 
	 * @return
	 */
	@RequestMapping("/cuserMenus")
	@ResponseBody
	public Object loadTreeNodeForCurrentUser(String id, String domain,
			HttpSession session) {
		try {
			List<Map<String, Object>> list1 = EasyUiTreeNode
					.queryTreeNodeOne(
							id,
							"SORT",
							"(SELECT c.NAME    AS NAME, SORT, c.ID      AS ID,  PARENTID, ICON, b.AUTHKEY     AS URL, c.PARAMS  AS PARAM, domain FROM navix_auth_actiontree c LEFT JOIN navix_auth_action b ON c.ACTIONID = b.ID)",
							"ID", "PARENTID", "NAME", "ICON",
							"and a.DOMAIN='" + domain + "'", "URL,PARAM")
					.getResultList();
			List<Map<String, Object>> list2 = EasyUiTreeNode.queryTreeNodeTow(
					id, "SORT", "navix_auth_actiontree", "ID", "PARENTID",
					"NAME", "ICON", "and a.DOMAIN='" + domain + "'")
					.getResultList();
			return EasyUiTreeNode.formatAsyncAjaxTreeForMenuTree(list1, list2,
					"PARENTID", "ID", "NAME", "ICON", "URL", "PARAM",
					getCurrentUserMenus(session));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

}
