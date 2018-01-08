package com.navix.web.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.doc.server.FarmDocRunInfoInter;
import com.navix.doc.server.FarmDocgroupManagerInter;
import com.navix.doc.server.FarmDocmessageManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.doc.server.UsermessageServiceInter;
import com.navix.doc.server.commons.DocumentConfig;
import com.navix.parameter.FarmParameterService;
import com.navix.authority.domain.Organization;
import com.navix.authority.domain.User;
import com.navix.authority.service.UserServiceInter;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.know.service.KnowServiceInter;
import com.navix.web.util.ThemesUtil;

/**
 * 用户管理
 * 
 * @author 
 * 
 */
@RequestMapping("/webuser")
@Controller
public class WebUserController extends WebUtils {
	@Resource
	private FarmDocgroupManagerInter farmDocgroupManagerImpl;
	@Resource
	private FarmFileManagerInter farmFileManagerImpl;
	@Resource
	private FarmDocManagerInter farmDocManagerImpl;
	@Resource
	private FarmDocRunInfoInter farmDocRunInfoImpl;
	@Resource
	private KnowServiceInter knowServiceImpl;
	@Resource
	private FarmDocmessageManagerInter farmDocmessageManagerImpl;
	@Resource
	private FarmDocOperateRightInter farmDocOperateRightImpl;
	@Resource
	private UserServiceInter userServiceImpl;
	@Resource
	private UsermessageServiceInter usermessageServiceImpl;

	/**
	 * 修改用户信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/edit")
	public ModelAndView editUser(HttpSession session) {
		try {
			User user = userServiceImpl.getUserEntity(getCurrentUser(session).getId());
			String name = user.getName();
			String photoid = user.getImgid();
			String photourl = null;
			if (photoid != null && photoid.trim().length() > 0) {
				photourl = farmFileManagerImpl.getFileURL(photoid);
			}

			// 获取用户机构
			Organization org = userServiceImpl.getOrg(user.getId());
			String showOrgStr = FarmParameterService.getInstance().getParameter("config.regist.showOrg");
			boolean showOrg = false;
			if ("true".equals(showOrgStr)) {
				showOrg = true;
			}

			return ViewMode.getInstance().putAttr("user", user).putAttr("name", name).putAttr("photoid", photoid)
					.putAttr("org", org).putAttr("showOrg", showOrg).putAttr("photourl", photourl)
					.returnModelAndView(ThemesUtil.getThemePath()+"/user/userInfoEdit");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.toString()).returnModelAndView(ThemesUtil.getThemePath()+"/user/userInfoEdit");
		}
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@RequestMapping("/PubRegist")
	public ModelAndView regist(HttpSession session) {
		String showOrgStr = FarmParameterService.getInstance().getParameter("config.regist.showOrg");
		boolean showOrg = false;
		if ("true".equals(showOrgStr)) {
			showOrg = true;
		}
		return ViewMode.getInstance()
				.putAttr("imgUrl", DocumentConfig.getString("config.doc.download.url") + "402888ac501d764801501d817b9e0011")
				.putAttr("showOrg", showOrg).returnModelAndView(ThemesUtil.getThemePath()+"/user/regist");
	}

	/**
	 * 用户注册提交
	 * 
	 * @return
	 * @throws UserNoExistException
	 */
	@RequestMapping("/PubRegistCommit")
	public ModelAndView registSubmit(String photoid, String loginname, String name, String password, String orgid,
			HttpSession session) {
		User user = new User();
		try {
			if (FarmParameterService.getInstance().getParameter("config.sys.registable ").equals("false")) {
				throw new RuntimeException("该操作已经被管理员禁用!");
			}
			user.setImgid(photoid);
			user.setLoginname(loginname);
			name = name == null ? "昵称未设置" : name;
			user.setName(name);
			user.setState("1");
			user.setType("1");
			user = userServiceImpl.registUser(user, orgid);
			userServiceImpl.editLoginPassword(loginname,
					FarmParameterService.getInstance().getParameter("config.default.password"), password);
			
		} catch (Exception e) {
			e.printStackTrace();
			if(photoid == null || photoid.isEmpty()){
				photoid = "402888ac501d764801501d817b9e0011";
			}
			return ViewMode.getInstance()
					.putAttr("photoid", photoid)
					.putAttr("loginname", loginname)
					.putAttr("name", name)
					.putAttr("orgid", orgid)
					.putAttr("imgUrl", DocumentConfig.getString("config.doc.download.url") + photoid)
					.putAttr("errorMessage", e.getMessage())
					.returnModelAndView(ThemesUtil.getThemePath()+"/user/regist");
		}
		return ViewMode.getInstance()
				.putAttr("name", loginname)
				.putAttr("password", password)
				.returnRedirectUrl("/login/websubmit.do");
	}

	/**
	 * 用户首页
	 * 
	 * @return
	 */
	@RequestMapping("/PubHome")
	public ModelAndView showUserHome(String userid, String type, Integer num, HttpSession session) {
		ViewMode mode = ViewMode.getInstance();
		boolean isSelf = false;
		DataResult result = null;
		User user = null;
		try {
			if (userid == null) {
				user = userServiceImpl.getUserEntity(getCurrentUser(session).getId());
				mode.putAttr("self", true);
				isSelf = true;
			} else {
				user = userServiceImpl.getUserEntity(userid);
				if (user.getId().equals(getCurrentUser(session) != null ? getCurrentUser(session).getId() : "none")) {
					mode.putAttr("self", true);
					isSelf = true;
				} else {
					mode.putAttr("self", false);
					isSelf = false;
				}
			}
			if (user != null) {
				DataResult users = farmDocRunInfoImpl.getStatUser(user);
				if (user.getImgid() != null && user.getImgid().trim().length() > 0) {
					mode.putAttr("photourl", farmFileManagerImpl.getFileURL(user.getImgid()));
				}
				mode.putAttr("users", users);
			}
			// --------------------------查询只是列表和店铺------------------------------
			if (num == null) {
				num = 1;
			}
			if (type == null) {
				type = "joy";
			}
			if (type.equals("know")) {
				// 发布商品
				result = isSelf ? farmDocRunInfoImpl.userDocs(user.getId(), "1", 10, num)
						: farmDocRunInfoImpl.userPubDocs(user.getId(), "1", 10, num);
			}
			if (type.equals("file")) {
				// 发布资源
				result = isSelf ? farmDocRunInfoImpl.userDocs(user.getId(), "5", 10, num)
						: farmDocRunInfoImpl.userPubDocs(user.getId(), "5", 10, num);
			}
			if (type.equals("joy")) {
				if (user != null && !user.getId().isEmpty()) {
					// 关注商品
					result = farmDocRunInfoImpl.getUserEnjoyDoc(user.getId()).setCurrentPage(num).setPagesize(10)
							.search();
					result.runformatTime("PUBTIME", "yyyy-MM-dd HH:mm");
				}
			}
			if (type.equals("group")) {
				// 关注店铺
				result = farmDocgroupManagerImpl.getGroupsByUser(user.getId(), 16, num);
			}
			if (type.equals("audit")) {
				// 审核中
				result = farmDocRunInfoImpl.getMyAuditingByUser(user.getId(), 10, num);
				result.runformatTime("PUBTIME", "yyyy-MM-dd HH:mm");
				result.runDictionary("1:待审核,2:审核通过,3:审核未通过,4:废弃", "STATE");
			}
			if (type.equals("audito")) {
				// 审核任务
				result = farmDocRunInfoImpl.getAuditDocByUser(user.getId(), 10, num);
				result.runformatTime("PUBTIME", "yyyy-MM-dd HH:mm");
				result.runDictionary("1:待审核,2:审核通过,3:审核未通过,4:废弃", "STATE");
			}
			if (type.equals("audith")) {
				// 审核历史
				result = farmDocRunInfoImpl.getMyAuditedByUser(user.getId(), 10, num);
				result.runformatTime("PUBTIME", "yyyy-MM-dd HH:mm");
				result.runDictionary("1:待审核,2:审核通过,3:审核未通过,4:废弃", "STATE");
			}
			if (type.equals("usermessage")) {
				// 用户消息
				result = usermessageServiceImpl.getMyMessageByUser(getCurrentUser(session).getId(), 10, num);
				result.runformatTime("USERMESSAGECTIME", "yyyy-MM-dd HH:mm");
				result.runDictionary("0:未读,1:已读", "READSTATE");
			}
		} catch (SQLException e) {
			return mode.setError(e.toString()).returnModelAndView(ThemesUtil.getThemePath()+"/error");
		}
		return mode.putAttr("docs", result)
				.putAttr("userid", user.getId())
				.putAttr("type", type)
				.putAttr("num", num)
				.returnModelAndView(ThemesUtil.getThemePath()+"/user/userHome");
	}

	/**
	 * 更新当前登录用户信息
	 * 
	 * @param id
	 *            用户ID
	 * @param name
	 *            用户名称
	 * @param photoid
	 *            头像ID
	 * @return ModelAndView
	 */
	@RequestMapping("/editCurrentUser")
	public ModelAndView editCurrentUser(String name, String photoid, String orgid, HttpSession session) {
		try {
			userServiceImpl.editCurrentUser(getCurrentUser(session).getId(), name, photoid, orgid);
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString()).returnModelAndView(ThemesUtil.getThemePath()+"/error");
		}
		return ViewMode.getInstance().returnRedirectUrl("/webuser/PubHome.do");
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @param id
	 * @param name
	 * @param photoid
	 * @return ModelAndView
	 */
	@RequestMapping("/editCurrentUserPwd")
	public ModelAndView editCurrentUserPwd() {
		return ViewMode.getInstance().returnModelAndView(ThemesUtil.getThemePath()+"/user/passwordEdit");
	}

	/**
	 * 更新当前登录用户的密码
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/editCurrentUserPwdCommit")
	public ModelAndView editCurrentUserPwdCommit(String password, String newPassword, HttpSession session) {
		try {
			userServiceImpl.editCurrentUserPwdCommit(getCurrentUser(session).getId(), password, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString()).returnModelAndView(ThemesUtil.getThemePath()+"/error");
		}
		return ViewMode.getInstance().returnRedirectUrl("/webuser/PubHome.do");
	}

	/**
	 * 
	 */
	@RequestMapping("/validCurrUserPwd")
	@ResponseBody
	public Map<String, Object> validCurrUserPwd(String password, HttpSession session) {
		try {
			boolean b = userServiceImpl.validCurrentUserPwd(getCurrentUser(session).getId(), password);
			return ViewMode.getInstance().putAttr("validCurrentUserPwd", b).returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString()).returnObjMode();
		}
	}
}
