package com.navix.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navix.biz.dao.GoodsAllInfoDao;
import com.navix.biz.entity.GoodsAllInfo;

/**
 * 商品全信息Service
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */

@Service
public class GoodsAllInfoService {
	@Autowired
    private GoodsAllInfoDao goodsAllInfoDao;

    /**
     * 商品全信息查询（根据商品编号查询）
     * @param goodsId
     * @return 商品信息
     */
    public GoodsAllInfo selectGoodsAllInfoByGoodsId(String goodsId,String delFlg,String langKbn) {
    	GoodsAllInfo goodsAllInfo = goodsAllInfoDao.selectGoodsAllInfoByGoodsId(goodsId, delFlg, langKbn);
    	return goodsAllInfo;
    }    
}
