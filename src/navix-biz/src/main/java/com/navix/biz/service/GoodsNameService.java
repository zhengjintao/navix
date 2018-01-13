package com.navix.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.navix.biz.dao.GoodsNameDao;
import com.navix.biz.entity.GoodsName;

/**
 * 商品名称Service
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */

@Service
public class GoodsNameService {
	@Autowired
    private GoodsNameDao goodsNameDao;
   
    /**
     * 商品名称新规追加
     * @param goods
     */
    public void insert(GoodsName goodsName) {
    	goodsNameDao.insert(goodsName);
    }
    
    /**
     * 商品名称更新
     * @param goods
     */
    public void update(GoodsName goodsName) {
    	goodsNameDao.update(goodsName);
    }
    /**
     * 商品名称删除
     * @param goodsSeqId
     */
    public void delete(String goodsSeqId) {
    	goodsNameDao.delete(goodsSeqId);
    }      
     
    /**
     * 商品名称查询（根据商品管理番号查询）
     * @param goodsSeqId
     * @return 商品信息
     */
    public GoodsName selectByGoodsSeqId(String goodsSeqId) {
    	GoodsName goodsName = goodsNameDao.selectByGoodsSeqId(goodsSeqId);
    	return goodsName;
    }
}
