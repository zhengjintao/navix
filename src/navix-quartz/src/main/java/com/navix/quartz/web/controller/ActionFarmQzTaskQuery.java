package com.navix.quartz.web.controller;

import com.navix.core.page.OperateType;
import com.navix.core.page.RequestMode;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.query.DBSort;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.core.web.easyui.EasyUiUtils;
import com.navix.quartz.domain.FarmQzTask;
import com.navix.quartz.server.FarmQzSchedulerManagerInter;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 任务定义
 * 
 * @author 
 * 
 */
@RequestMapping("/qzTask")
@Controller
public class ActionFarmQzTaskQuery extends WebUtils {
	private static final Logger log = Logger
			.getLogger(ActionFarmQzTaskQuery.class);
	@Resource
	FarmQzSchedulerManagerInter farmQzSchedulerManagerImpl;

	/**
	 * 查询结果集合
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryall(DataQuery query,
			HttpServletRequest request) {
		query = EasyUiUtils.formatGridQuery(request, query);
		try {
			query = farmQzSchedulerManagerImpl.createTaskSimpleQuery(query).addSort(
					new DBSort("CTIME", "desc"));

			DataResult result = query.search();
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
	 * 提交修改数据
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> editSubmit(FarmQzTask entity, HttpSession session) {
		try {
			entity = farmQzSchedulerManagerImpl.editTaskEntity(entity,
					getCurrentUser(session));
			return ViewMode.getInstance().setOperate(OperateType.ADD)
					.putAttr("entity", entity).returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ViewMode.getInstance().setOperate(OperateType.ADD)
					.setError(e.getMessage()).returnObjMode();
		}
	}

	/**
	 * 提交新增数据
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addSubmit(FarmQzTask entity, HttpSession session) {
		try {
			entity = farmQzSchedulerManagerImpl.insertTaskEntity(entity,
					getCurrentUser(session));
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
				farmQzSchedulerManagerImpl.deleteTaskEntity(id,
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
	 * 跳转
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView index(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView(
				"quartz/pFarmQzTaskLayout");
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
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.returnModelAndView("quartz/pFarmQzTaskEntity");
			}
			case (0): {// 展示
				return ViewMode
						.getInstance()
						.putAttr("pageset", pageset)
						.putAttr("entity",
								farmQzSchedulerManagerImpl.getTaskEntity(ids))
						.returnModelAndView("quartz/pFarmQzTaskEntity");
			}
			case (2): {// 修改
				return ViewMode
						.getInstance()
						.putAttr("pageset", pageset)
						.putAttr("entity",
								farmQzSchedulerManagerImpl.getTaskEntity(ids))
						.returnModelAndView("quartz/pFarmQzTaskEntity");
			}
			default:
				break;
			}
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e + e.getMessage())
					.returnModelAndView("quartz/pFarmQzTaskEntity");
		}
		return ViewMode.getInstance().returnModelAndView(
				"quartz/pFarmQzTaskEntity");
	}
}
