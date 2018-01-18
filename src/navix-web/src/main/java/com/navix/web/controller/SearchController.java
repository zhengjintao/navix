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

/**
 * 商品检索Controller
 * @author fangkangming
 *
 */
@RequestMapping("/search")
@Controller
public class SearchController extends WebUtils {
	private final static Logger log = Logger.getLogger(SearchController.class);
	@Resource  
	private GoodsAllInfoService goodsAllInfoService;
	@Resource 
	private ShopGoodsAllInfoService shopGoodsAllInfoService;
	
	/**
	 * 根据商品条码检索对应全部店铺（条形码）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search/Goods{goodId}", method = RequestMethod.GET)
	public ModelAndView ShowSearchBygoodsId(@PathVariable("goodId") String goodId, HttpSession session, HttpServletRequest request)
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
	
	/**
	 * 根据关键字检索商品（关键字）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search/Key{keyWord}", method = RequestMethod.GET)
	public ModelAndView ShowSearchByGoodsKeyWord(@PathVariable("keyWord") String keyWord, HttpSession session, HttpServletRequest request)
			throws Exception {	
		// 商品编号对应的店铺信息取得
		return null;
		
	}
}
