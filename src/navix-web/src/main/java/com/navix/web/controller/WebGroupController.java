package com.navix.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.doc.domain.FarmDocgroup;
import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.domain.ex.GroupEntire;
import com.navix.doc.exception.CanNoReadException;
import com.navix.doc.exception.DocNoExistException;
import com.navix.doc.exception.NoGroupAuthForLicenceException;
import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.doc.server.FarmDocRunInfoInter;
import com.navix.doc.server.FarmDocgroupManagerInter;
import com.navix.doc.server.FarmDocgroupManagerInter.SearchType;
import com.navix.doc.server.FarmDocmessageManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.doc.server.UsermessageServiceInter;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.know.service.KnowServiceInter;
import com.navix.web.util.ThemesUtil;

/**
 * 店铺
 * 
 * @author zhengjintao
 *
 */
@RequestMapping("/webgroup")
@Controller
public class WebGroupController extends WebUtils {
	@Resource
	private FarmDocgroupManagerInter farmDocgroupManagerImpl;
	@Resource
	private FarmFileManagerInter farmFileManagerImpl;
	@Resource
	private FarmDocManagerInter farmDocManagerImpl;
	@Resource
	private FarmDocRunInfoInter farmDocRunInfoImpl;
	@Resource
	private KnowServiceInter KnowServiceImpl;
	@Resource
	private FarmDocmessageManagerInter farmDocmessageManagerImpl;
	@Resource
	private FarmDocOperateRightInter farmDocOperateRightImpl;
	@Resource
	private UsermessageServiceInter usermessageServiceImpl;

	/**
	 * 进入创建店铺
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpSession session, HttpServletRequest request) {
		return ViewMode.getInstance().returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/editGroup");
	}

	/**
	 * 进入修改店铺
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(String groupid, HttpSession session, HttpServletRequest request) {
		GroupEntire group = farmDocgroupManagerImpl.getFarmDocgroup(groupid);
		return ViewMode.getInstance().putAttr("group", group)
				.returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/editGroup");
	}

	/**
	 * 进入店铺成员管理
	 * 
	 * @return
	 */
	@RequestMapping("/userMag")
	public ModelAndView userEdit(Integer page, String groupid, HttpSession session, HttpServletRequest request) {
		if (page == null) {
			page = 1;
		}
		GroupEntire group = farmDocgroupManagerImpl.getFarmDocgroup(groupid);
		// 当前成员申请
		DataResult result = farmDocgroupManagerImpl.getGroupUser(groupid, SearchType.yes, SearchType.none,
				SearchType.none, 1, 10);
		// 成员信息
		DataResult users = farmDocgroupManagerImpl.getGroupUser(groupid, SearchType.no, SearchType.none,
				SearchType.none, page, 10);
		return ViewMode.getInstance().putAttr("applys", result).putAttr("group", group).putAttr("users", users)
				.putAttr("groupid", groupid)
				.returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/groupAdminConsole");
	}

	/**
	 * 添加店铺
	 * 
	 * @return
	 */
	@RequestMapping("/addCommit")
	public ModelAndView addCommit(FarmDocgroup group, HttpSession session) {
		try {
			if (group != null && group.getId() != null && group.getId().trim().length() <= 0) {
				group.setId(null);
			}
			group = farmDocgroupManagerImpl.creatDocGroup(group.getGroupname(), group.getGrouptag(),
					group.getGroupimg(), group.getJoincheck().equals("1") ? true : false, group.getGroupnote(),
					getCurrentUser(session));

			return ViewMode.getInstance().putAttr("id", group.getId()).returnRedirectUrl("/webgroup/PubHome.html");
		} catch (NoGroupAuthForLicenceException e) {
			return ViewMode.getInstance().returnRedirectUrl("/webgroup/PubHome.html");
		} catch (Exception e) {
			return ViewMode.getInstance().returnRedirectUrl("/webgroup/PubHome.html");
		}
	}

	/**
	 * 修改店铺
	 * 
	 * @return
	 */
	@RequestMapping("/editCommit")
	public ModelAndView editCommit(FarmDocgroup group, HttpSession session) {
		try {
			group.setPstate("1");
			farmDocgroupManagerImpl.editFarmDocgroupEntity(group, getCurrentUser(session));

			return ViewMode.getInstance().returnRedirectUrl("/webgroup/Pubshow.do?groupid=" + group.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().returnRedirectUrl("/webgroup/Pubshow.do?groupid=" + group.getId());
		}
	}

	/**
	 * 编辑店铺首页 v1.0 zhanghc 2015年9月24日下午7:33:48
	 * 
	 * @param group
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("/homeeditCommit")
	public ModelAndView homeeditCommit(String groupId, String docid, String text, String editNote,
			HttpSession session) {
		try {
			@SuppressWarnings("deprecation")
			DocEntire doc = farmDocManagerImpl.getDoc(docid);
			doc.setTexts(text, getCurrentUser(session));
			farmDocManagerImpl.editDoc(doc, editNote, getCurrentUser(session));
			return ViewMode.getInstance().returnRedirectUrl("/webgroup/Pubshow.do?groupid=" + groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().returnRedirectUrl("/webgroup/Pubshow.do?groupid=" + groupId);
		}
	}

	/**
	 * 进入编辑店铺首页
	 * 
	 * @return
	 */
	@RequestMapping("/homeedit")
	public ModelAndView homeedit(String groupid, HttpSession session, HttpServletRequest request) {
		ViewMode mode = ViewMode.getInstance();
		try {
			FarmDocgroup group = farmDocgroupManagerImpl.getFarmDocgroupEntity(groupid);
			String homedocid = group.getHomedocid();
			@SuppressWarnings("deprecation")
			DocEntire doc = farmDocManagerImpl.getDoc(homedocid);
			mode.putAttr("group", group).putAttr("doc", doc);
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
		return mode.returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/editHomePage");
	}

	/**
	 * 进入店铺页面
	 * 
	 * @return
	 */
	@RequestMapping("/Pubshow")
	public ModelAndView show(String typeid, Integer pagenum, String groupid, HttpSession session,
			HttpServletRequest request) {
		ViewMode mode = ViewMode.getInstance();
		GroupEntire docgroup = farmDocgroupManagerImpl.getFarmDocgroup(groupid);
		DocEntire doc = null;
		try {
			doc = farmDocManagerImpl.getDoc(docgroup.getHomedocid(), getCurrentUser(session));
		} catch (CanNoReadException | DocNoExistException e) {
			return ViewMode.getInstance().setError("没有权限，或不存在")
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
		if (getCurrentUser(session) != null) {
			// 查询当前用户的店铺权限
			mode.putAttr("usergroup",
					farmDocgroupManagerImpl.getFarmDocgroupUser(docgroup.getId(), getCurrentUser(session).getId()));
		}
		int docnum = farmDocgroupManagerImpl.getGroupDocNum(groupid);
		// 获得店铺最新商品
		mode.putAttr("docs",
				farmDocgroupManagerImpl.getNewGroupDoc(getCurrentUser(session) == null ? null : getCurrentUser(session),
						groupid, 10, pagenum == null ? 1 : pagenum));
		return mode.putAttr("docnum", docnum).putAttr("home", doc).putAttr("groupid", groupid)
				.putAttr("docgroup", docgroup).returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/groupSite");
	}

	/**
	 * 店铺一览首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/PubHome", method = RequestMethod.GET)
	public ModelAndView showDoc(Integer pagenum, HttpSession session, HttpServletRequest request) throws Exception {
		if (pagenum == null) {
			pagenum = 1;
		}
		// 获得最热店铺
		DataResult groups = farmDocgroupManagerImpl.getGroups(12, pagenum);
		return ViewMode.getInstance().putAttr("groups", groups)
				.returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/groupHome");
	}

	/**
	 * 取消用户管理员权限
	 * 
	 * @return
	 */
	@RequestMapping("/wipeAdmin")
	@ResponseBody
	public Map<String, Object> groupWipeAdmin(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.wipeAdminFromGroup(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 设置为店铺管理员
	 * 
	 * @return
	 */
	@RequestMapping("/groupSetAdmin")
	@ResponseBody
	public Map<String, Object> groupSetAdmin(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.setAdminForGroup(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 去除店铺编辑权限
	 * 
	 * @return
	 */

	@RequestMapping("/groupWipeEditor")
	@ResponseBody
	public Map<String, Object> groupWipeEditor(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.wipeEditorForGroup(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 设置店铺编辑权限
	 * 
	 * @return
	 */
	@RequestMapping("/groupSetEditor")
	@ResponseBody
	public Map<String, Object> groupSetEditor(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.setEditorForGroup(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 将用户取消关注店铺
	 * 
	 * @return
	 */
	@RequestMapping("/groupQuit")
	@ResponseBody
	public Map<String, Object> groupQuit(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.leaveGroup(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 同意关注店铺
	 * 
	 * @param groupUserId
	 * @param session
	 * @return
	 */
	@RequestMapping("/agreeApply")
	@ResponseBody
	public Map<String, Object> agreeApply(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.agreeJoinApply(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 拒绝关注店铺
	 * 
	 * @param groupUserId
	 * @param session
	 * @return
	 */
	@RequestMapping("/refuseApply")
	@ResponseBody
	public Map<String, Object> refuseApply(String groupUserId, HttpSession session) {
		try {
			farmDocgroupManagerImpl.refuseJoinApply(groupUserId, getCurrentUser(session));
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
		return ViewMode.getInstance().returnObjMode();
	}

	/**
	 * 是否有店铺管理员存在,通过店铺管理员数量判断
	 * 
	 * @return
	 */
	@RequestMapping("/haveAdministratorIs")
	@ResponseBody
	public Map<String, Object> haveAdministratorIs(String groupId) {
		int adminNum = farmDocgroupManagerImpl.getAllAdministratorByGroup(groupId).size();
		return ViewMode.getInstance().putAttr("adminNum", adminNum).returnObjMode();
	}

	/**
	 * 取消关注店铺
	 * 
	 * @return
	 */
	@RequestMapping("/leaveGroup")
	public ModelAndView leaveGroup(String groupId, HttpSession session) {
		farmDocgroupManagerImpl.leaveGroup(groupId, getCurrentUser(session).getId());
		return ViewMode.getInstance().returnRedirectUrl("/webgroup/Pubshow.do?groupid=" + groupId);
	}

	/**
	 * 关注店铺
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/join")
	public ModelAndView joinform(String groupId, HttpSession session, HttpServletRequest request) throws Exception {
		if (farmDocgroupManagerImpl.isJoinGroupByUser(groupId, getCurrentUser(session).getId())) {
			if (farmDocgroupManagerImpl.isAuditing(groupId, getCurrentUser(session).getId())) {
				return ViewMode.getInstance().setError("正在审核中")
						.returnModelAndView(ThemesUtil.getThemePath() + "/error");
			} else {
				// 进入店铺
			}
		} else {
			// 关注店铺
			if (farmDocgroupManagerImpl.isJoinCheck(groupId)) {
				// 进入申请页面

				return ViewMode.getInstance().putAttr("group", farmDocgroupManagerImpl.getFarmDocgroupEntity(groupId))
						.returnModelAndView(ThemesUtil.getThemePath() + "/docgroup/joinCheckForm");
			} else {
				// 直接加入
				farmDocgroupManagerImpl.applyGroup(groupId, getCurrentUser(session).getId(), "申请加入",
						getCurrentUser(session));
			}
		}
		return ViewMode.getInstance().putAttr("groupid", groupId).returnRedirectUrl("/webgroup/Pubshow.do");
	}

	/**
	 * 提交店铺申请
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/joincommit")
	public ModelAndView joincommit(String groupId, String message, HttpSession session, HttpServletRequest request)
			throws Exception {
		if (farmDocgroupManagerImpl.isJoinGroupByUser(groupId, getCurrentUser(session).getId())) {
			if (farmDocgroupManagerImpl.isAuditing(groupId, getCurrentUser(session).getId())) {
				return ViewMode.getInstance().setError("正在审核中")
						.returnModelAndView(ThemesUtil.getThemePath() + "/error");
			} else {
				return ViewMode.getInstance().putAttr("groupid", groupId).returnRedirectUrl("/webgroup/Pubshow.do");
			}
		} else {
			// 直接加入
			farmDocgroupManagerImpl.applyGroup(groupId, getCurrentUser(session).getId(), message,
					getCurrentUser(session));
			return ViewMode.getInstance().setError("已经提交加入请求，请等待管理员审核!")
					.returnModelAndView(ThemesUtil.getThemePath() + "/message");
		}
	}

}
