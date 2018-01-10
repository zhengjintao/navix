package com.navix.biz.entity;

/**
 * 商品entity
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public class Goods {
	// 管理番号
	private String goodsSeqId;
	// 商品编号
	private String goodsId;
	// 商品区分
	private String goodsKbn;
	// 商品图片
	private String goodsImg;
	// 商品品牌
	private String goodsBland;
	// 参照价格
	private int goodsRefPrice;
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
	
    public Goods(){
      
    }  

    public String getGoodsSeqId() {
		return goodsSeqId;
	}
	public void setGoodsSeqId(String goodsSeqId) {
		this.goodsSeqId = goodsSeqId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsKbn() {
		return goodsKbn;
	}
	public void setGoodsKbn(String goodsKbn) {
		this.goodsKbn = goodsKbn;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsBland() {
		return goodsBland;
	}
	public void setGoodsBland(String goodsBland) {
		this.goodsBland = goodsBland;
	}
	public int getGoodsRefPrice() {
		return goodsRefPrice;
	}
	public void setGoodsRefPrice(int goodsRefPrice) {
		this.goodsRefPrice = goodsRefPrice;
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
				+"goodsSeqId=" + goodsSeqId
				+", goodsId=" + goodsId
				+", goodsKbn=" + goodsKbn
				+", goodsImg=" + goodsImg
				+", goodsBland=" + goodsBland
				+", goodsRefPrice=" + goodsRefPrice
				+", delFlg=" + delFlg
				+", CreateUser=" + CreateUser
				+", CreateDate=" + CreateDate
				+", updateUser=" + updateUser
				+", updateDate=" + updateDate
				+"]";
				         
       return str;
    }
}
