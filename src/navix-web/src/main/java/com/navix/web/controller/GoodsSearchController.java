package com.navix.web.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.navix.biz.entity.GoodsAllInfo;
import com.navix.biz.entity.ShopGoodsAllInfo;
import com.navix.biz.service.GoodsAllInfoService;
import com.navix.biz.service.ShopGoodsAllInfoService;
import com.navix.core.page.ViewMode;
import com.navix.core.web.WebUtils;
import com.navix.web.util.ThemesUtil;

@RequestMapping("/goods")
@Controller
public class GoodsSearchController extends WebUtils {
	private final static Logger log = Logger.getLogger(GoodsController.class);
	@Resource  
	private GoodsAllInfoService goodsAllInfoService;
	@Resource 
	private ShopGoodsAllInfoService shopGoodsAllInfoService;
	
	/**
	 * 查看商品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view1/Pub{goodId}", method = RequestMethod.GET)
	public ModelAndView showDoc(@PathVariable("goodId") String goodId, HttpSession session, HttpServletRequest request)
			throws Exception {
		// 言语区分
		String langKbn = "C";
		// 删除标志
		String delFlg = "0";
		// 商品编号对应商品情报取得
		GoodsAllInfo goodsAllInfo = goodsAllInfoService.selectGoodsAllInfoByGoodsId(goodId, delFlg, langKbn);

		// 商品编号对应管理番号
		String goodsSeqId = goodsAllInfo.getSeqId();
		List<ShopGoodsAllInfo> shopGoodsAllInfoList = shopGoodsAllInfoService.selectByGoodsSeqId(goodsSeqId, delFlg, langKbn);
		
		// 商品编号对应的店铺信息取得
		return ViewMode.getInstance()
				.putAttr("GOODSINFO", goodsAllInfo)
				.putAttr("SHOPINFOLIST", shopGoodsAllInfoList)
				.returnModelAndView(ThemesUtil.getThemePath() + "/goods/goodsSearchResListview");
		
	}
}
