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
	// 价格
	private String goodsPrice;
	// 库存
	private String goodsStock;
	// 删除标记
	private int delFlg;
	// 登录者
	private String CreateUser;
	// 登录时间
	private String CreateDate;
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
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
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

	@Override
    public String toString() {
		String str ="Goods ["
				+"shopGoodsInfoSeqId=" + shopGoodsInfoSeqId
				+", shopSeqId=" + shopSeqId
				+", goodsSeqId=" + goodsSeqId
				+", goodsDiySeqId=" + goodsDiySeqId
				+", goodsPrice=" + goodsPrice
				+", goodsStock=" + goodsStock
				+", delFlg=" + delFlg
				+", CreateUser=" + CreateUser
				+", CreateDate=" + CreateDate
				+", updateUser=" + updateUser
				+", updateDate=" + updateDate
				+"]";
		
       return str;
    }
}
