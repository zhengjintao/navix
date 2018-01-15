package com.navix.biz.dao;

import com.navix.biz.entity.GoodsAllInfo;

/**
 * 商品全信息Dao
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public interface GoodsAllInfoDao {
	// 商品查询（根据商品编号查询）
	public GoodsAllInfo selectGoodsAllInfoByGoodsId(String goodsId,String delFlg,String langKbn);
}
