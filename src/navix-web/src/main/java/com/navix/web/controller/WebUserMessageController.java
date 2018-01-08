package com.navix.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navix.doc.domain.Usermessage;
import com.navix.doc.server.FarmDocmessageManagerInter;
import com.navix.doc.server.UsermessageServiceInter;
import com.navix.core.page.ViewMode;
import com.navix.core.sql.query.DBRule;
import com.navix.core.sql.query.DBSort;
import com.navix.core.sql.query.DataQuery;
import com.navix.core.web.WebUtils;
import com.navix.web.util.ThemesUtil;

/**
 * 用户消息
 * 
 * @author 
 * 
 */
@RequestMapping("/webusermessage")
@Controller
public class WebUserMessageController extends WebUtils {
	@Resource
	private UsermessageServiceInter usermessageServiceImpl;
	@Resource
	private FarmDocmessageManagerInter farmDocmessageManagerImpl;

	/**
	 * 查看用户信息
	 * 
	 * @param id 消息ID
	 * @param num 分页参数（返回消息列表需要）
	 * @return
	 */
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(String id, Integer num) {
		try {
			usermessageServiceImpl.setRead(id);
			Usermessage usermessage = usermessageServiceImpl.getUsermessageEntity(id);
			String readstatename = "";
			if(usermessage.getReadstate().equals("0")){//0未读、1已读
				readstatename = "未读";
			}else if(usermessage.getReadstate().equals("1")){
				readstatename = "已读";
			}
			return ViewMode.getInstance()
					.putAttr("usermessage", usermessage)
					.putAttr("readstatename", readstatename)
					.putAttr("num", num)
					.returnModelAndView(ThemesUtil.getThemePath()+"/know/userMessage");
		} catch (Exception e) {
			return ViewMode.getInstance().setError(e.toString()).returnModelAndView("");
		}
	}
	
	/**
	 * 显示首页未读消息
	 * @return
	 * Map<String,Object>
	 */
	@RequestMapping("/showHomeMessage")
	@ResponseBody
	public Map<String, Object> showHomeMessage(HttpSession session){
		try {
			DataQuery query = usermessageServiceImpl.createUsermessageSimpleQuery(null);
			query.setNoCount();
			query.addRule(new DBRule("USERMESSAGE.READUSERID", getCurrentUser(session).getId(), "="));
			query.addRule(new DBRule("USERMESSAGE.READSTATE", "0", "="));
			query.addSort(new DBSort("USERMESSAGE.CTIME", "DESC"));
			List<Map<String, Object>> list = query.search().getResultList();
			String newMessage = "";
			if(list != null && list.size() > 0){
				newMessage = list.get(0).get("TITLE").toString();
			}
			
			return ViewMode.getInstance()
					.putAttr("newMessage", newMessage)
					.putAttr("unReadCount", list.size())
					.returnObjMode();
		} catch (Exception e) {
			return ViewMode.getInstance().returnObjMode();
		}
	}
}
