package com.navix.biz.entity;

/**
 * 商品entity
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public class Goods {
	//管理番号
    private String seqId;
    //商品编号
    private String goodsId;
    //商品分类管理番号
    private String goodsClassSeqId;
    //商品图片
    private String goodsImg;
    //商品品牌
    private String goodsBrandSeqId;
    //参照价格
    private String goodsRefPrice;
    //商品含税区分
    private String goodsTaxFlg;
    //商品容量规格
    private String goodsSize;
    //删除标记
    private String delFlg;
    //登录者
    private String createUser;
    //登录时间
    private String creadeDate;
    //更新者
    private String updateUser;
    //更新时间
    private String updateDate;
	
    public Goods(){
      
    }  

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsClassSeqId() {
		return goodsClassSeqId;
	}

	public void setGoodsClassSeqId(String goodsClassSeqId) {
		this.goodsClassSeqId = goodsClassSeqId;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsBrandSeqId() {
		return goodsBrandSeqId;
	}

	public void setGoodsBrandSeqId(String goodsBrandSeqId) {
		this.goodsBrandSeqId = goodsBrandSeqId;
	}

	public String getGoodsRefPrice() {
		return goodsRefPrice;
	}

	public void setGoodsRefPrice(String goodsRefPrice) {
		this.goodsRefPrice = goodsRefPrice;
	}

	public String getGoodsTaxFlg() {
		return goodsTaxFlg;
	}

	public void setGoodsTaxFlg(String goodsTaxFlg) {
		this.goodsTaxFlg = goodsTaxFlg;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreadeDate() {
		return creadeDate;
	}

	public void setCreadeDate(String creadeDate) {
		this.creadeDate = creadeDate;
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
				+ "seqId = "+seqId
                + ", goodsId = "+goodsId
                + ", goodsClassSeqId = "+goodsClassSeqId
                + ", goodsImg = "+goodsImg
                + ", goodsBrandSeqId = "+goodsBrandSeqId
                + ", goodsRefPrice = "+goodsRefPrice
                + ", goodsTaxFlg = "+goodsTaxFlg
                + ", goodsSize = "+goodsSize
                + ", delFlg = "+delFlg
                + ", createUser = "+createUser
                + ", creadeDate = "+creadeDate
                + ", updateUser = "+updateUser
                + ", updateDate = "+updateDate
				+"]";
				         
       return str;
    }
}
