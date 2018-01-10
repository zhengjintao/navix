package com.navix.biz.dao;

import java.util.List;
import com.navix.biz.entity.ShopGoodsInfo;

/**
 * 店铺商品信息Dao
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public interface ShopGoodsInfoDao {
	// 店铺商品信息新规追加
	public void insert(ShopGoodsInfo shopGoodsInfo);
	
	// 店铺商品信息更新
	public void update(ShopGoodsInfo shopGoodsInfo);
	
	// 店铺商品信息删除
	public void delete(String shopGoodsInfoSeqId);
	
	// 店铺商品信息查询（根据【店铺管理番号】）
	public List<ShopGoodsInfo> selectByShopSeqId(String shopSeqId);
	
	// 店铺商品信息查询（根据【商品管理番号】）
	public List<ShopGoodsInfo> selectByGoodsSeqId(String goodsSeqId);
}
