package com.navix.biz.entity;

/**
 * 店铺商品信息entity
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public class ShopGoodsInfo {	
	// 管理番号
	private String shopGoodsInfoSeqId;
	// 店铺管理番号
	private String shopSeqId;
	// 商品管理番号
	private String goodsSeqId;
	// 商品自定义信息管理番号
	private String goodsDiySeqId;
	// 店铺编号
	private String shopId;
	// 店铺图片
	private String shopImg;
	// 店铺区分
	private String shopKbn;
	// 店铺名称
	private String shopName;
	// 店铺地址
	private String shopAddress;
	// 店铺简介
	private String shopSimpleEpx;
	// 价格
	private String goodsPrice;
	// 库存
	private String goodsStock;
	// 删除标记
	private int delFlg;
	// 登录者
	private String createUser;
	// 登录时间
	private String createDate;
	// 更新者
	private String updateUser;
	// 更新时间
	private String updateDate;
	
    public ShopGoodsInfo(){
      
    }
    
	public String getShopGoodsInfoSeqId() {
		return shopGoodsInfoSeqId;
	}

	public void setShopGoodsInfoSeqId(String shopGoodsInfoSeqId) {
		this.shopGoodsInfoSeqId = shopGoodsInfoSeqId;
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

	public String getGoodsDiySeqId() {
		return goodsDiySeqId;
	}

	public void setGoodsDiySeqId(String goodsDiySeqId) {
		this.goodsDiySeqId = goodsDiySeqId;
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

	public int getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	@Override
    public String toString() {
		String str ="Goods ["
				+"shopGoodsInfoSeqId=" + shopGoodsInfoSeqId
				+", shopSeqId=" + shopSeqId
				+", goodsSeqId=" + goodsSeqId
				+", goodsDiySeqId=" + goodsDiySeqId
				+", shopId=" + shopId
				+", shopImg=" + shopImg
				+", shopKbn=" + shopKbn
				+", shopName=" + shopName
				+", shopAddress=" + shopAddress
				+", shopSimpleEpx=" + shopSimpleEpx
				+", goodsPrice=" + goodsPrice
				+", goodsStock=" + goodsStock
				+", delFlg=" + delFlg
				+", createUser=" + createUser
				+", createDate=" + createDate
				+", updateUser=" + updateUser
				+", updateDate=" + updateDate
				+"]";
		
       return str;
    }
}
