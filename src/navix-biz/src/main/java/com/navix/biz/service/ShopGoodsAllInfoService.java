package com.navix.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.navix.biz.dao.ShopGoodsAllInfoDao;
import com.navix.biz.entity.ShopGoodsAllInfo;

/**
 * 店铺商品信息Service
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */

@Service
public class ShopGoodsAllInfoService {
	@Autowired
    private ShopGoodsAllInfoDao shopGoodsAllInfoDao;

	/**
	 * 店铺商品信息查询（根据【商品管理番号】）
	 * @param goodsSeqId
	 * @param delFlg
	 * @param langKbn
	 * @return 店铺商品信息
	 */
    public List<ShopGoodsAllInfo> selectByGoodsSeqId(String goodsSeqId, String delFlg, String langKbn) {
    	List<ShopGoodsAllInfo> shopGoodsInfoList = shopGoodsAllInfoDao.selectByGoodsSeqId(goodsSeqId, delFlg, langKbn);
    	return shopGoodsInfoList;
    }
}
