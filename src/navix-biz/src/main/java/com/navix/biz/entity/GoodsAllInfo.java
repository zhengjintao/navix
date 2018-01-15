package com.navix.biz.entity;

/**
 * 商品全信息entity
 * @author fangkangming
 *
 */
public class GoodsAllInfo {
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
    //商品名称
    private String goodsNm;
    //商品简述
    private String goodsSimpleEpx;
    //商品内容
    private String goodsContext;
    //商品成分
    private String goodsComposition;
    //品牌编号
    private String brandId;
    //品牌名称
    private String brandNm;
    //商品分类编号
    private String goodsClassId;
    //商品父类编号
    private String goodsFclassId;

    public GoodsAllInfo(){
        
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
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getGoodsSimpleEpx() {
		return goodsSimpleEpx;
	}
	public void setGoodsSimpleEpx(String goodsSimpleEpx) {
		this.goodsSimpleEpx = goodsSimpleEpx;
	}
	public String getGoodsContext() {
		return goodsContext;
	}
	public void setGoodsContext(String goodsContext) {
		this.goodsContext = goodsContext;
	}
	public String getGoodsComposition() {
		return goodsComposition;
	}
	public void setGoodsComposition(String goodsComposition) {
		this.goodsComposition = goodsComposition;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandNm() {
		return brandNm;
	}
	public void setBrandNm(String brandNm) {
		this.brandNm = brandNm;
	}
	public String getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(String goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public String getGoodsFclassId() {
		return goodsFclassId;
	}
	public void setGoodsFclassId(String goodsFclassId) {
		this.goodsFclassId = goodsFclassId;
	}
	
	@Override
    public String toString() {
		String str ="GoodsAllInfo ["
                + "seqId = "+seqId
                + ", goodsId = "+goodsId
                + ", goodsClassSeqId = "+goodsClassSeqId
                + ", goodsImg = "+goodsImg
                + ", goodsBrandSeqId = "+goodsBrandSeqId
                + ", goodsRefPrice = "+goodsRefPrice
                + ", goodsTaxFlg = "+goodsTaxFlg
                + ", goodsSize = "+goodsSize
                + ", goodsNm = "+goodsNm
                + ", goodsSimpleEpx = "+goodsSimpleEpx
                + ", goodsContext = "+goodsContext
                + ", goodsComposition = "+goodsComposition
                + ", brandId = "+brandId
                + ", brandNm = "+brandNm
                + ", goodsClassId = "+goodsClassId
                + ", goodsFclassId = "+goodsFclassId
				+"]";
				         
       return str;
    }
}
