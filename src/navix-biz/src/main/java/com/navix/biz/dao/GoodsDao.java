package com.navix.biz.dao;

import com.navix.biz.entity.Goods;

/**
 * 商品Dao
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public interface GoodsDao {
	// 商品新规追加
	public void insert(Goods goods);
	
	// 商品更新
	public void update(Goods goods);
	
	// 商品删除
	public void delete(String goodsSeqId);
	
	// 商品查询（根据商品编号查询）
	public Goods selectByGoodsId(String goodsId);
	
	// 商品查询（根据商品管理番号查询）
	public Goods selectByGoodsSeqId(String goodsSeqId);
}
