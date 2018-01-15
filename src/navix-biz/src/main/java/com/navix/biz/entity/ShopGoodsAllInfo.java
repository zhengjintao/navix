package com.navix.biz.entity;

/**
 * 店铺商品信息entity
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public class ShopGoodsAllInfo {	
	//管理番号
    private String seqId;
    //店铺编号
    private String shopId;
    //图片
    private String shopImg;
    //店铺区分
    private String shopKbn;
    //店铺名称
    private String shopNm;
    //店铺地址
    private String shopAddress;
    //店铺简介
    private String shopSimpleEpx;
    //自定义内容1
    private String diyinfoContext1;
    //自定义内容2
    private String diyinfoContext2;
    //自定义内容2
    private String diyinfoContext3;
    //店铺管理番号
    private String shopSeqId;
    //商品管理番号
    private String goodsSeqId;
    //商品自定义信息管理番号
    private String goodsDiyinfoSeqId;
    //价格
    private String goodsPrice;
    //库存
    private String goodsStock;
	
    public ShopGoodsAllInfo(){
      
    }
    
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getShopKbn() {
		return shopKbn;
	}
	public void setShopKbn(String shopKbn) {
		this.shopKbn = shopKbn;
	}
	public String getShopNm() {
		return shopNm;
	}
	public void setShopNm(String shopNm) {
		this.shopNm = shopNm;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopSimpleEpx() {
		return shopSimpleEpx;
	}
	public void setShopSimpleEpx(String shopSimpleEpx) {
		this.shopSimpleEpx = shopSimpleEpx;
	}
	public String getDiyinfoContext1() {
		return diyinfoContext1;
	}
	public void setDiyinfoContext1(String diyinfoContext1) {
		this.diyinfoContext1 = diyinfoContext1;
	}
	public String getDiyinfoContext2() {
		return diyinfoContext2;
	}
	public void setDiyinfoContext2(String diyinfoContext2) {
		this.diyinfoContext2 = diyinfoContext2;
	}
	public String getDiyinfoContext3() {
		return diyinfoContext3;
	}
	public void setDiyinfoContext3(String diyinfoContext3) {
		this.diyinfoContext3 = diyinfoContext3;
	}
	public String getShopSeqId() {
		return shopSeqId;
	}
	public void setShopSeqId(String shopSeqId) {
		this.shopSeqId = shopSeqId;
	}
	public String getGoodsSeqId() {
		return goodsSeqId;
	}
	public void setGoodsSeqId(String goodsSeqId) {
		this.goodsSeqId = goodsSeqId;
	}
	public String getGoodsDiyinfoSeqId() {
		return goodsDiyinfoSeqId;
	}
	public void setGoodsDiyinfoSeqId(String goodsDiyinfoSeqId) {
		this.goodsDiyinfoSeqId = goodsDiyinfoSeqId;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}
	
	@Override
    public String toString() {
		String str ="ShopGoodsAllInfo ["
                + "seqId = "+seqId
                + ", shopId = "+shopId
                + ", shopImg = "+shopImg
                + ", shopKbn = "+shopKbn
                + ", shopNm = "+shopNm
                + ", shopAddress = "+shopAddress
                + ", shopSimpleEpx = "+shopSimpleEpx
                + ", diyinfoContext1 = "+diyinfoContext1
                + ", diyinfoContext2 = "+diyinfoContext2
                + ", diyinfoContext3 = "+diyinfoContext3
                + ", shopSeqId = "+shopSeqId
                + ", goodsSeqId = "+goodsSeqId
                + ", goodsDiyinfoSeqId = "+goodsDiyinfoSeqId
                + ", goodsPrice = "+goodsPrice
                + ", goodsStock = "+goodsStock
				+"]";
		
       return str;
    }
}
