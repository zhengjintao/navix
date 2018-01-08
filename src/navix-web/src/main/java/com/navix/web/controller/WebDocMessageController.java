package com.navix.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.doc.dao.FarmRfDoctypeDaoInter;
import com.navix.doc.domain.FarmDocmessage;
import com.navix.doc.domain.ex.DocBrief;
import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.server.FarmDocIndexInter;
import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.doc.server.FarmDocRunInfoInter;
import com.navix.doc.server.FarmDocgroupManagerInter;
import com.navix.doc.server.FarmDocmessageManagerInter;
import com.navix.doc.server.FarmFileIndexManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.know.service.KnowServiceInter;
import com.navix.web.util.ThemesUtil;

@RequestMapping("/webdocmessage")
@Controller
public class WebDocMessageController extends WebUtils {
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
	private FarmDocIndexInter farmDocIndexManagerImpl;
	@Resource
	private FarmRfDoctypeDaoInter farmRfDoctypeDaoImpl;
	@Resource
	private FarmFileIndexManagerInter farmFileIndexManagerImpl;

	@RequestMapping("/Pubmsg")
	public ModelAndView docMessage(Integer num, String docid, HttpSession session, HttpServletRequest request) {
		try {
			// 加载热词
			List<DocBrief> hotdocs = farmDocRunInfoImpl.getPubHotDoc(10);
			DocEntire doc = farmDocManagerImpl.getDoc(docid, getCurrentUser(session));
			if (num == null) {
				num = 1;
			}
			DataResult result = farmDocmessageManagerImpl.getMessages(docid, num, 20);
			for (Map<String, Object> map : result.getResultList()) {
				if (map.get("IMGID") != null) {
					map.put("IMGURL", farmFileManagerImpl.getFileURL(map.get("IMGID").toString()));
				}

				map.put("replys", farmDocmessageManagerImpl.getReplys(docid, map.get("ID").toString()));
			}
			result.runformatTime("CTIME", "yyyy-MM-dd HH:mm:ss");
			return ViewMode.getInstance().putAttr("doc", doc).putAttr("hotdocs", hotdocs).putAttr("result", result)
					.returnModelAndView(ThemesUtil.getThemePath() + "/know/docMessage");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}

	@RequestMapping("/addmsg")
	public ModelAndView addMessage(String docid, String content, HttpSession session, HttpServletRequest request) {
		try {
			farmDocmessageManagerImpl.sendAnswering(content, "商品评论", "评论", docid, getCurrentUser(session));
			return ViewMode.getInstance().putAttr("docid", docid).returnRedirectUrl("/webdocmessage/Pubmsg.do");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}

	/**
	 * 赞同
	 * 
	 * @param session
	 * @return Map<String,Object>
	 */
	@RequestMapping("/approveOf")
	@ResponseBody
	public Map<String, Object> approveOf(String id, HttpSession session) {
		try {
			FarmDocmessage farmDocmessage = farmDocmessageManagerImpl.approveOf(id, getCurrentUser(session));
			return ViewMode.getInstance().putAttr("farmDocmessage", farmDocmessage).returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().returnObjMode();
		}
	}

	/**
	 * 反对
	 * 
	 * @param session
	 * @return Map<String,Object>
	 */
	@RequestMapping("/oppose")
	@ResponseBody
	public Map<String, Object> oppose(String id, HttpSession session) {
		try {
			FarmDocmessage farmDocmessage = farmDocmessageManagerImpl.oppose(id, getCurrentUser(session));
			return ViewMode.getInstance().putAttr("farmDocmessage", farmDocmessage).returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().returnObjMode();
		}
	}

	/**
	 * 回复
	 * 
	 * @param id
	 * @param num
	 * @return ModelAndView
	 */
	@RequestMapping("/reply")
	public ModelAndView reply(FarmDocmessage farmDocmessage, HttpSession session) {
		try {
			farmDocmessageManagerImpl.reply(farmDocmessage.getContent(), farmDocmessage.getAppid(),
					farmDocmessage.getId(), getCurrentUser(session));
			return ViewMode.getInstance().putAttr("docid", farmDocmessage.getAppid())
					.returnRedirectUrl("/webdocmessage/Pubmsg.do");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.getMessage())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}
}
