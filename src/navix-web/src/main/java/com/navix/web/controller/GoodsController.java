package com.navix.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.doc.dao.FarmRfDoctypeDaoInter;
import com.navix.doc.domain.FarmDocfile;
import com.navix.doc.domain.FarmDocruninfo;
import com.navix.doc.domain.FarmDoctext;
import com.navix.doc.domain.FarmDoctype;
import com.navix.doc.domain.ex.DocBrief;
import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.exception.CanNoReadException;
import com.navix.doc.exception.DocNoExistException;
import com.navix.doc.server.FarmDocIndexInter;
import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.doc.server.FarmDocRunInfoInter;
import com.navix.doc.server.FarmDocgroupManagerInter;
import com.navix.doc.server.FarmDocmessageManagerInter;
import com.navix.doc.server.FarmFileIndexManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.page.ViewMode;
import com.navix.core.web.WebUtils;
import com.navix.know.service.KnowServiceInter;
import com.navix.web.util.ThemesUtil;

@RequestMapping("/goods")
@Controller
public class GoodsController extends WebUtils {
	private final static Logger log = Logger.getLogger(GoodsController.class);
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

	/**
	 * 查看商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/Pub{docid}", method = RequestMethod.GET)
	public ModelAndView showDoc(@PathVariable("docid") String docid, HttpSession session, HttpServletRequest request)
			throws Exception {
		ViewMode page = ViewMode.getInstance();
		try {
			DocEntire doc = farmDocManagerImpl.getDoc(docid, getCurrentUser(session));
			page.putAttr("DOCE", doc);
			List<FarmDoctext> versions = farmDocManagerImpl.getDocVersions(docid);
			page.putAttr("VERSIONS", versions);
			if (getCurrentUser(session) != null) {
				boolean isenjoy = farmDocRunInfoImpl.isEnjoyDoc(getCurrentUser(session).getId(), docid);
				page.putAttr("ISENJOY", isenjoy);
			}
			FarmDoctype type = doc.getType();
			page.putAttr("TYPEID", type == null ? "" : type.getId());
			Set<String> fileTypes = new HashSet<String>();
			for (FarmDocfile node : doc.getFiles()) {
				fileTypes.add(node.getExname().trim().replace(".", "").toUpperCase());
			}
			page.putAttr("FILETYPES", fileTypes);
			farmDocRunInfoImpl.visitDoc(docid, getCurrentUser(session), getCurrentIp(request));
			LoginUser user = getCurrentUser(session);
			if (type != null) {
				List<DocBrief> typedocs = farmDocRunInfoImpl.getTypeDocs(type == null ? "" : type.getId(),
						user == null ? "none" : user.getId(), 10);
				page.putAttr("TYPEDOCS", typedocs);
			}
			if (doc.getDoc().getDomtype().equals("1")) {
				return page.returnModelAndView(ThemesUtil.getThemePath() + "/know/view");
			}
			if (doc.getDoc().getDomtype().equals("5")) {
				return page.returnModelAndView(ThemesUtil.getThemePath() + "/webfile/webfile");
			}
			if (doc.getDoc().getDomtype().equals("4")) {
				return page.putAttr("groupid", doc.getGroup().getId()).returnRedirectUrl("/webgroup/Pubshow.do");
			}
		} catch (CanNoReadException e) {
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		} catch (DocNoExistException e) {
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
		return ViewMode.getInstance().setError("请实现正确的DOCTYPE类型解析")
				.returnModelAndView(ThemesUtil.getThemePath() + "/error");
	}

	/**
	 * 查看附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/PubFile{fileid}", method = RequestMethod.GET)
	public ModelAndView showFile(@PathVariable("fileid") String fileid, HttpSession session, HttpServletRequest request)
			throws Exception {
		ViewMode page = ViewMode.getInstance();
		try {
			FarmDocfile file = farmFileManagerImpl.getFile(fileid);
			if (file == null) {
				throw new DocNoExistException();
			}
			file.setUrl(farmFileManagerImpl.getFileURL(file.getId()));
			page.putAttr("file", file);
		} catch (DocNoExistException e) {
			log.error(e.getMessage());
			farmFileIndexManagerImpl.delFileLucenneIndex(fileid);
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
		return page.returnModelAndView(ThemesUtil.getThemePath() + "/webfile/file");
	}

	@RequestMapping("/enjoy")
	@ResponseBody
	public Map<String, Object> enjoy(HttpSession session, String id) {
		try {
			farmDocRunInfoImpl.enjoyDoc(getCurrentUser(session).getId(), id);
			return ViewMode.getInstance().putAttr("commitType", "0").returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().putAttr("commitType", "1").setError(e.getMessage()).returnObjMode();
		}
	}

	@RequestMapping("/FLunEnjoy")
	@ResponseBody
	public Map<String, Object> unenjoy(HttpSession session, String id) {
		try {
			farmDocRunInfoImpl.unEnjoyDoc(getCurrentUser(session).getId(), id);
			return ViewMode.getInstance().putAttr("commitType", "0").returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().putAttr("commitType", "1").setError(e.getMessage()).returnObjMode();
		}
	}

	@RequestMapping("/PubVersion")
	public ModelAndView showVersion(String textid, HttpSession session, HttpServletRequest request) {
		ViewMode page = ViewMode.getInstance();
		try {
			DocEntire doc = farmDocManagerImpl.getDocVersion(textid, getCurrentUser(session));
			if (!doc.getDoc().getState().equals("1")) {
				throw new RuntimeException("没有权限访问该文档");
			}
			List<FarmDoctext> versions = farmDocManagerImpl.getDocVersions(doc.getDoc().getId());
			page.putAttr("VERSIONS", versions);
			return page.putAttr("DOCE", doc).returnModelAndView(ThemesUtil.getThemePath() + "/know/version");
		} catch (Exception e) {
			return page.setError(e.getMessage()).returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}

	/**
	 * 公开文档（将该文档开放阅读和编辑权限，同时如果是店铺文档将删除店铺所有权）
	 * 
	 * @return
	 */
	@RequestMapping("/FLflyKnow")
	public ModelAndView flyKnow(String id, HttpSession session) {
		try {
			farmDocOperateRightImpl.flyDoc(id, getCurrentUser(session));
			return ViewMode.getInstance().returnRedirectUrl("/webdoc/view/Pub" + id + ".html");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	@RequestMapping("/FLDelKnow")
	public ModelAndView delCommit(String id, HttpSession session) {
		try {
			DocEntire doc = farmDocManagerImpl.deleteDoc(id, getCurrentUser(session));
			// 只有资源文件才有附件索引
			if (doc.getDoc().getDomtype().equals("5")) {
				// 删除附件索引
				for (FarmDocfile file : doc.getFiles()) {
					farmFileIndexManagerImpl.delFileLucenneIndex(file.getId(), doc);
				}
			}
			return ViewMode.getInstance().putAttr("MESSAGE", "删除成功！")
					.returnModelAndView(ThemesUtil.getThemePath() + "/message");
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.toString())
					.returnModelAndView(ThemesUtil.getThemePath() + "/error");
		}
	}

	@RequestMapping("/delImg")
	public Map<String, Object> delImg(String imgid) {
		try {
			farmDocManagerImpl.delImg(imgid);
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.toString()).returnObjMode();
		}
	}

	@RequestMapping("/PubPraiseYes")
	@ResponseBody
	public Map<String, Object> praiseYes(String id, HttpSession session, HttpServletRequest request) {
		try {
			if (getCurrentUser(session) == null) {
				farmDocRunInfoImpl.praiseDoc(id, request.getRemoteAddr());
			} else {
				farmDocRunInfoImpl.praiseDoc(id, getCurrentUser(session), request.getRemoteAddr());
			}
			FarmDocruninfo runinfo = farmDocRunInfoImpl.loadRunInfo(id);
			return ViewMode.getInstance().putAttr("runinfo", runinfo).returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString()).returnObjMode();
		}
	}

	@RequestMapping("/PubPraiseNo")
	@ResponseBody
	public Map<String, Object> praiseNo(String id, HttpSession session, HttpServletRequest request) {
		try {
			if (getCurrentUser(session) == null) {
				farmDocRunInfoImpl.criticalDoc(id, getCurrentIp(request));
			} else {
				farmDocRunInfoImpl.criticalDoc(id, getCurrentUser(session), getCurrentIp(request));
			}
			FarmDocruninfo runinfo = farmDocRunInfoImpl.loadRunInfo(id);
			return ViewMode.getInstance().putAttr("runinfo", runinfo).returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
			return ViewMode.getInstance().setError(e.toString()).returnObjMode();
		}
	}
}
