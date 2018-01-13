package com.navix.biz.dao;

import com.navix.biz.entity.GoodsName;

/**
 * 商品名称Dao
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public interface GoodsNameDao {
	// 商品名称新规追加
	public void insert(GoodsName goodsName);
	
	// 商品名称更新
	public void update(GoodsName goodsName);
	
	// 商品名称删除
	public void delete(String goodsSeqId);
	
	// 商品名称查询（根据商品管理番号查询）
	public GoodsName selectByGoodsSeqId(String goodsSeqId);
}
