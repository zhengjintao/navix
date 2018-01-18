package com.navix.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	@Autowired
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
    	List<ShopGoodsInfo> goodsList = shopGoodsInfoDao.selectByShopSeqId(shopSeqId, 0);
    	return goodsList;
    }    
    
    /**
     * 店铺商品信息查询（根据【商品管理番号】）
     * @param goodsSeqId
     * @return 店铺商品信息
     */
    public List<ShopGoodsInfo> selectByGoodsSeqId(String goodsSeqId) {
    	List<ShopGoodsInfo> goodsList = shopGoodsInfoDao.selectByGoodsSeqId(goodsSeqId, 0, "C");
    	return goodsList;
    }
    
    /**
     * 分页店铺商品信息查询
     * @param goodsSeqId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<ShopGoodsInfo> queryByPage(String goodsSeqId, Integer pageNo,Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<ShopGoodsInfo> goodsList = shopGoodsInfoDao.selectByGoodsSeqId(goodsSeqId, 0, "C");
        //用PageInfo对结果进行包装
        PageInfo<ShopGoodsInfo> page = new PageInfo<ShopGoodsInfo>(goodsList);
        //测试PageInfo全部属性
        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getFirstPage());
        System.out.println(page.getLastPage());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
        return page;
    }
}
