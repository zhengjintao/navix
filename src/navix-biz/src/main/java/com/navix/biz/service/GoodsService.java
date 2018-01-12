package com.navix.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.navix.biz.dao.GoodsDao;
import com.navix.biz.entity.Goods;

/**
 * 商品Service
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */

@Service
public class GoodsService {
	@Autowired
    private GoodsDao goodsDao;
   
    /**
     * 商品新规追加
     * @param goods
     */
    public void insert(Goods goods) {
    	goodsDao.insert(goods);
    }
    
    /**
     * 商品更新
     * @param goods
     */
    public void update(Goods goods) {
    	goodsDao.update(goods);
    }
    /**
     * 商品删除
     * @param goodsSeqId
     */
    public void delete(String goodsSeqId) {
    	goodsDao.delete(goodsSeqId);
    }      
    
    /**
     * 商品查询（根据商品编号查询）
     * @param goodsId
     * @return 商品信息
     */
    public Goods selectByGoodsId(String goodsId) {
    	Goods goods = goodsDao.selectByGoodsId(goodsId);
    	return goods;
    }    
    
    /**
     * 商品查询（根据商品管理番号查询）
     * @param goodsSeqId
     * @return 商品信息
     */
    public Goods selectByGoodsSeqId(String goodsSeqId) {
    	Goods goods = goodsDao.selectByGoodsSeqId(goodsSeqId);
    	return goods;
    }
}
