package com.navix.biz.dao;

import java.util.List;

import com.navix.biz.entity.ShopGoodsAllInfo;

/**
 * 店铺商品信息Dao（根据商品检索得到的全部店铺的相关信息）
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public interface ShopGoodsAllInfoDao {
	// 店铺商品信息查询（根据【商品管理番号】）
	public List<ShopGoodsAllInfo> selectByGoodsSeqId(String goodsSeqId, String delFlg, String langKbn);
}
