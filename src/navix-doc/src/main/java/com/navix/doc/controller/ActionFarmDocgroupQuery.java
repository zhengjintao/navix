package com.navix.doc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.core.page.OperateType;
import com.navix.core.page.RequestMode;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.core.web.easyui.EasyUiUtils;
import com.navix.doc.domain.FarmDocgroup;
import com.navix.doc.server.FarmDocgroupManagerInter;
import com.navix.doc.server.FarmFileManagerInter;

/**
 * 店铺
 * 
 * 
 */
@RequestMapping("/docgroup")
@Controller
public class ActionFarmDocgroupQuery extends WebUtils {
	private static final Logger log = Logger.getLogger(ActionFarmDocgroupQuery.class);
	@Resource
	private FarmDocgroupManagerInter farmDocgroupManagerImpl;
	@Resource
	private FarmFileManagerInter farmFileManagerImpl;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryall(DataQuery query, HttpServletRequest request) {
		query = EasyUiUtils.formatGridQuery(request, query);
		try {
			query = EasyUiUtils.formatGridQuery(request, query);
			DataResult result = farmDocgroupManagerImpl.createFarmDocgroupQuery(query).search();
			result.runDictionary("1:是,0:否", "JOINCHECK");
			result.runDictionary("1:可用,0:禁用", "PSTATE");
			return ViewMode.getInstance().putAttrs(EasyUiUtils.formatGridData(result)).returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
	}

	@RequestMapping("/list")
	public ModelAndView index(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView("doc/DocgroupResult");
	}

	//
	/**
	 * 提交修改数据
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> editSubmit(FarmDocgroup entity, HttpSession session) {
		try {
			entity = farmDocgroupManagerImpl.editDocGroup(entity.getId(), entity.getGroupname(), entity.getGrouptag(),
					entity.getGroupimg(), entity.getJoincheck().equals("1"), entity.getGroupnote(),
					getCurrentUser(session));

			return ViewMode.getInstance().setOperate(OperateType.ADD).putAttr("entity", entity).returnObjMode();

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ViewMode.getInstance().setOperate(OperateType.ADD).setError(e.getMessage()).returnObjMode();
		}
	}

	/**
	 * 提交新增数据
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addSubmit(FarmDocgroup entity, HttpSession session) {
		try {
			entity = farmDocgroupManagerImpl.creatDocGroup(entity.getGroupname(), entity.getGrouptag(),
					entity.getGroupimg(), entity.getJoincheck().equals("1"), entity.getGroupnote(),
					getCurrentUser(session));
			return ViewMode.getInstance().putAttr("entity", entity).returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
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
				farmDocgroupManagerImpl.deleteFarmDocgroupEntity(id, getCurrentUser(session));
			}
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
	}

	/**
	 * 显示详细信息（修改或浏览时）
	 *
	 * @return
	 */
	@RequestMapping("/form")
	public ModelAndView view(RequestMode pageset, String ids) {
		try {
			switch (pageset.getOperateType()) {
			case (1): {// 新增
				return ViewMode.getInstance().putAttr("pageset", pageset).returnModelAndView("doc/DocgroupForm");
			}
			case (0): {// 展示
				FarmDocgroup entity = farmDocgroupManagerImpl.getFarmDocgroupEntity(ids);
				return ViewMode.getInstance().putAttr("pageset", pageset).putAttr("entity", entity)
						.putAttr("imgurl", farmFileManagerImpl.getFileURL(entity.getGroupimg()))
						.returnModelAndView("doc/DocgroupForm");
			}
			case (2): {// 修改
				FarmDocgroup entity = farmDocgroupManagerImpl.getFarmDocgroupEntity(ids);
				return ViewMode.getInstance().putAttr("pageset", pageset).putAttr("entity", entity)
						.putAttr("imgurl", farmFileManagerImpl.getFileURL(entity.getGroupimg()))
						.returnModelAndView("doc/DocgroupForm");
			}
			case (3): {// 查看店铺
				return ViewMode.getInstance().putAttr("pageset", pageset).putAttr("ids", ids)
						.returnModelAndView("doc/DocgroupuserGrid");
			}
			default:
				break;
			}
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e + e.getMessage()).returnModelAndView("doc/DocgroupForm");
		}
		return ViewMode.getInstance().returnModelAndView("doc/DocgroupForm");
	}
}
