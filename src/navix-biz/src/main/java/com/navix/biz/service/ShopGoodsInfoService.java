package com.navix.biz.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.navix.biz.dao.ShopGoodsInfoDao;
import com.navix.biz.entity.ShopGoodsInfo;

/**
 * 店铺商品信息Service
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */

@Service
public class ShopGoodsInfoService {
	//@Autowired
    private ShopGoodsInfoDao shopGoodsInfoDao;
   
    /**
     * 店铺商品信息新规追加
     * @param shopGoodsInfo
     */
    public void insert(ShopGoodsInfo shopGoodsInfo) {
    	shopGoodsInfoDao.insert(shopGoodsInfo);
    }
    
    /**
     * 店铺商品信息更新
     * @param shopGoodsInfo
     */
    public void update(ShopGoodsInfo shopGoodsInfo) {
    	shopGoodsInfoDao.update(shopGoodsInfo);
    }
    /**
     * 店铺商品信息删除
     * @param shopGoodsInfoSeqId
     */
    public void delete(String shopGoodsInfoSeqId) {
    	shopGoodsInfoDao.delete(shopGoodsInfoSeqId);
    }      
    
    /**
     * 店铺商品信息查询（根据【店铺管理番号】）
     * @param shopSeqId
     * @return 店铺商品信息
     */
    public List<ShopGoodsInfo> selectByGoodsId(String shopSeqId) {
    	List<ShopGoodsInfo> goodsList = shopGoodsInfoDao.selectByShopSeqId(shopSeqId);
    	return goodsList;
    }    
    
    /**
     * 店铺商品信息查询（根据【商品管理番号】）
     * @param goodsSeqId
     * @return 店铺商品信息
     */
    public List<ShopGoodsInfo> selectByGoodsSeqId(String goodsSeqId) {
    	List<ShopGoodsInfo> goodsList = shopGoodsInfoDao.selectByGoodsSeqId(goodsSeqId);
    	return goodsList;
    }
}
