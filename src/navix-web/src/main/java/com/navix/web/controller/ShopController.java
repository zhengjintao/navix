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
import org.springframework.web.servlet.ModelAndView;
import com.navix.biz.entity.ShopGoodsInfo;
import com.navix.biz.service.ShopGoodsInfoService;
import com.navix.core.page.ViewMode;
import com.navix.core.web.WebUtils;
import com.navix.web.util.ThemesUtil;

@RequestMapping("/shop")
@Controller
public class ShopController extends WebUtils {
	private final static Logger log = Logger.getLogger(ShopController.class);
	@Resource  
	private ShopGoodsInfoService shopGoodsInfoService;
	/**
	 * 查看商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/Pub{goodid}", method = RequestMethod.GET)
	public ModelAndView showDoc(@PathVariable("goodid") String goodid, HttpSession session, HttpServletRequest request)
			throws Exception {
		List<ShopGoodsInfo> goodsInfoLists = shopGoodsInfoService.selectByGoodsSeqId(goodid);

		return ViewMode.getInstance()
				.putAttr("goodsInfoLists", goodsInfoLists)
				.returnModelAndView(ThemesUtil.getThemePath() + "/goods/goodsSearchResListview");
		
	}
}
