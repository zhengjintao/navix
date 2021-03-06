package com.navix.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;

import com.navix.core.page.OperateType;
import com.navix.core.page.RequestMode;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.core.web.easyui.EasyUiUtils;
import com.navix.doc.domain.Weburl;
import com.navix.doc.server.WeburlServiceInter;
import com.navix.doc.server.commons.DocumentConfig;

import java.util.Map;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

/* *
 *功能：推荐服务控制层
 *详细：
 */
@RequestMapping("/weburl")
@Controller
public class WeburlController extends WebUtils {
	private final static Logger log = Logger.getLogger(WeburlController.class);
	@Resource
	WeburlServiceInter weburlServiceImpl;

	public WeburlServiceInter getWeburlServiceImpl() {
		return weburlServiceImpl;
	}

	public void setWeburlServiceImpl(WeburlServiceInter weburlServiceImpl) {
		this.weburlServiceImpl = weburlServiceImpl;
	}

	/**
	 * 查询结果集合
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryall(DataQuery query,
			HttpServletRequest request) {

		try {
			query = EasyUiUtils.formatGridQuery(request, query);
			DataResult result = weburlServiceImpl
					.createWeburlSimpleQuery(query).search();
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
	public Map<String, Object> editSubmit(Weburl entity, HttpSession session) {

		try {
			entity = weburlServiceImpl.editWeburlEntity(entity,
					getCurrentUser(session));
			return ViewMode.getInstance().setOperate(OperateType.UPDATE)
					.putAttr("entity", entity).returnObjMode();

		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setOperate(OperateType.UPDATE)
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
	public Map<String, Object> addSubmit(Weburl entity, HttpSession session) {

		try {
			entity = weburlServiceImpl.insertWeburlEntity(entity,
					getCurrentUser(session));
			return ViewMode.getInstance().setOperate(OperateType.ADD)
					.putAttr("entity", entity).returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setOperate(OperateType.ADD)
					.setError(e.getMessage()).returnObjMode();
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
				weburlServiceImpl.deleteWeburlEntity(id,
						getCurrentUser(session));
			}
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	@RequestMapping("/list")
	public ModelAndView index(HttpSession session) {
		return ViewMode.getInstance().returnModelAndView("doc/WeburlResult");
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
			case (0): {// 查看
				Weburl entity = weburlServiceImpl.getWeburlEntity(ids);
				String imgUrl = DocumentConfig.getString("config.doc.download.url") + entity.getFileid();
				return ViewMode
						.getInstance()
						.putAttr("pageset", pageset)
						.putAttr("entity", entity)
						.putAttr("imgUrl", imgUrl)
						.returnModelAndView("doc/WeburlForm");
			}
			case (1): {// 新增
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.returnModelAndView("doc/WeburlForm");
			}
			case (2): {// 修改
				Weburl entity = weburlServiceImpl.getWeburlEntity(ids);
				String imgUrl = null;
				if(entity.getFileid() != null && !entity.getFileid().isEmpty()){
					imgUrl = DocumentConfig.getString("config.doc.download.url") + entity.getFileid();
				}
				return ViewMode.getInstance()
						.putAttr("pageset", pageset)
						.putAttr("entity", entity)
						.putAttr("imgUrl", imgUrl)
						.returnModelAndView("doc/WeburlForm");
			}
			default:
				break;
			}
			return ViewMode.getInstance().returnModelAndView("doc/WeburlForm");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e + e.getMessage())
					.returnModelAndView("doc/WeburlForm");
		}
	}
	
	/**
	 * 删除附件
	 * @return Map<String,Object>
	 */
	@RequestMapping("/delImg")
	@ResponseBody
	public Map<String, Object> delImg(String imgid) {
		try {
			weburlServiceImpl.delImg(imgid);
			return ViewMode.getInstance().returnObjMode();
		} catch (Exception e) {
			log.error(e.toString());
			return ViewMode.getInstance().setError(e.getMessage()).returnObjMode();
		}
	}
}
