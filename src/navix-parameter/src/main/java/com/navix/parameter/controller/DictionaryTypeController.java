package com.navix.parameter.controller;

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
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DBSort;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.sql.result.DataResult;
import com.navix.core.web.WebUtils;
import com.navix.core.web.easyui.EasyUiTreeNode;
import com.navix.core.web.easyui.EasyUiUtils;
import com.navix.parameter.domain.AloneDictionaryType;
import com.navix.parameter.service.DictionaryTypeServiceInter;

/**
 * 字典类型
 * 
 * @author zhaonaixia
 * @time 2015-7-06 上午10:19:25
 * 
 */
@RequestMapping("/dictionaryType")
@Controller
public class DictionaryTypeController extends WebUtils {
	private String parentName;// 父组织机构名称
	private static final Logger log = Logger
			.getLogger(DictionaryTypeController.class);
	
	@Resource
	DictionaryTypeServiceInter dictionaryTypeServiceImpl;
	
	/**
	 * 查询结果集合
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> queryall(DataQuery query,
			HttpServletRequest request,String ids) {
		try {
			query = EasyUiUtils.formatGridQuery(request, query);
			query = DataQuery
					.init(
							query,
							"navix_dictionary_type a LEFT JOIN navix_dictionary_type b ON a.parentid = b.id",
							"a.id AS ID,a.sort AS SORT,a.name AS NAME,a.entitytype AS ENTITYTYPE,a.state AS STATE,b.name AS PNAME");
			query.addSqlRule(" and (a.state = '0' or a.state = '1') ");// 查询非删除的组织机构
			query.addDefaultSort(new DBSort("a.sort", "asc"));
			if (ids != null && ids.trim().length() > 0) {
				query.addRule(new DBRule("a.entity", ids, "="));
			}
			DataResult result = query.search();
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
	 * 提交修改数据
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object>  editSubmit(AloneDictionaryType dictionary, HttpSession session) {
		try {
			AloneDictionaryType entity = dictionaryTypeServiceImpl.editEntity(dictionary, getCurrentUser(session));
			return ViewMode.getInstance().putAttr("entity", entity)
					.returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e.getMessage())
					.returnObjMode();
		}
	}

	/**
	 * 提交新增数据
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addSubmit(AloneDictionaryType dictionary, HttpSession session) {
		
		try {
			AloneDictionaryType entity = dictionaryTypeServiceImpl.insertEntity(dictionary, getCurrentUser(session));
			return ViewMode.getInstance().setOperate(OperateType.ADD)
					.putAttr("entity", entity).returnObjMode();
		} catch (Exception e) {
			e.printStackTrace();
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
				if (dictionaryTypeServiceImpl.getEntity(id) != null) {
					dictionaryTypeServiceImpl.deleteEntity(id, getCurrentUser(session));
				}
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
	@RequestMapping("/ALONEDictionaryType_ACTION_CONSOLE")
	public ModelAndView forSend(String ids,String type) {
		if("0".equals(type))//树
		{
			return ViewMode.getInstance()
					.putAttr("ids", ids)
					.putAttr("type", type)
					.returnModelAndView("parameter/pAloneDictionaryTypeTreeLayout");
		}
		else{
			//序列
			return ViewMode.getInstance()
					.putAttr("ids", ids)
					.putAttr("type", type)
					.returnModelAndView("parameter/pAloneDictionaryTypeLayout");
		}
		
	}

	/**
	 * 显示详细信息（修改或浏览时）
	 * 
	 * @return
	 */
	@RequestMapping("/form")
	public ModelAndView view(RequestMode pageset, String ids, String dicId) {
		try {
			switch (pageset.getOperateType()) {
			case (1): {// 新增
				if (dicId == null || dicId.equals("")) {
					throw new RuntimeException("找不到关联的数据字典！");
				}
				AloneDictionaryType entity = new AloneDictionaryType();
				entity.setEntity(dicId);
				
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", entity)
						.returnModelAndView("parameter/pAloneDictionaryTypeEntity");
			}
			case (0): {// 展示
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", dictionaryTypeServiceImpl.getEntity(ids))
						.returnModelAndView("parameter/pAloneDictionaryTypeEntity");
			}
			case (2): {// 修改
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", dictionaryTypeServiceImpl.getEntity(ids))
						.returnModelAndView("parameter/pAloneDictionaryTypeEntity");
			}
			default:
				break;
			}
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e + e.getMessage())
					.returnModelAndView("parameter/pAloneDictionaryEntityEntity");
		}
		return ViewMode.getInstance().returnModelAndView(
				"parameter/pAloneDictionaryEntityEntity");
	}

	/**
	 * (树组织结构)显示详细信息
	 * 
	 * @return
	 */
	@RequestMapping("/viewTreeform")
	public ModelAndView viewTree(RequestMode pageset, String ids, String dicId,String parentId) {
		try {
			switch (pageset.getOperateType()) {
			case (1): {// 新增
				if (dicId == null || dicId.equals("")) {
					throw new RuntimeException("找不到关联的数据字典！");
				}
				AloneDictionaryType entity = new AloneDictionaryType();
				entity.setEntity(dicId);
				if (parentId != null && !parentId.equals("")) {
					AloneDictionaryType pEntity = dictionaryTypeServiceImpl.getEntity(parentId);
					if (pEntity.getState().equals("1")) {
						 parentName = pEntity.getName();// 回显父组织机构名称
						entity.setParentid(parentId);
					}
				} else {
					entity.setParentid("NONE");
				}
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", entity)
						.putAttr("parentName", parentName)
						.returnModelAndView("parameter/pAloneDictionaryTypeTreeEntity");

			}
			case (0): {// 展示
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", dictionaryTypeServiceImpl.getEntity(ids))
						.returnModelAndView("parameter/pAloneDictionaryTypeTreeEntity");
				
			}
			case (2): {// 修改
				AloneDictionaryType entity = dictionaryTypeServiceImpl.getEntity(ids);
				if (!entity.getParentid().equals("NONE")) {
					parentName = dictionaryTypeServiceImpl.getEntity(entity.getParentid())
							.getName();
				}
				
				return ViewMode.getInstance().putAttr("pageset", pageset)
						.putAttr("entity", entity)
						.putAttr("parentName", parentName)
						.returnModelAndView("parameter/pAloneDictionaryTypeTreeEntity");
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ViewMode.getInstance().setError(e + e.getMessage())
					.returnModelAndView("parameter/pAloneDictionaryTypeTreeEntity");
		}
		return ViewMode.getInstance().returnModelAndView(
				"parameter/pAloneDictionaryTypeTreeEntity");
	}

	/**加载树节点
	 * @param ids 
	 * @param id
	 * @return
	 */
	@RequestMapping("/dictionaryTreeNote")
	@ResponseBody
	public Object loadTreeNode(String ids,String id) {
		if (id == null) {
			id = "NONE";
		}
		try{
		return EasyUiTreeNode
				.formatAsyncAjaxTree(
						EasyUiTreeNode
								.queryTreeNodeOne(
										id,
										"SORT",
										" (SELECT a.id AS ID,a.parentid AS PARENTID,a.name AS NAME,a.ctime AS CTIME,a.sort AS SORT,a.entity AS ENTITY,a.state AS STATE FROM navix_DICTIONARY_TYPE a LEFT JOIN navix_DICTIONARY_ENTITY b ON a.ENTITY = b.id  WHERE b.type = 0) ",
										"ID",
										"PARENTID",
										"NAME",
										"CTIME",
										" and a.ENTITY='" + ids
												+ "' and a.state!=2")
								.getResultList(),
						EasyUiTreeNode
								.queryTreeNodeTow(
										id,
										"SORT",
										" (SELECT a.id AS ID,a.parentid AS PARENTID,a.name AS NAME,a.ctime AS CTIME,a.sort AS SORT,a.entity AS ENTITY,a.state AS STATE FROM navix_DICTIONARY_TYPE a LEFT JOIN navix_DICTIONARY_ENTITY b ON a.ENTITY = b.id  WHERE b.type = 0) ",
										"ID",
										"PARENTID",
										"NAME",
										"CTIME",
										" and a.ENTITY='" + ids
												+ "' and a.state!=2")
								.getResultList(), "PARENTID", "ID", "NAME",
						"CTIME");
	} catch (Exception e) {
		log.error(e.getMessage());
		return ViewMode.getInstance().setError(e.getMessage())
				.returnObjMode();
	}
	}

}
