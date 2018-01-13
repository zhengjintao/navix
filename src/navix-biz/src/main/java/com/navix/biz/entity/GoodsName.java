package com.navix.biz.entity;

/**
 * 商品entity
 * 
 * @author fkm
 * @CreateDate 2018/01/10
 * @UpdateDate 
 */
public class GoodsName {
	//管理番号
    private String seqId;
    //语言区分
    private String langKbn;
    //商品名称
    private String goodsNm;
    //商品简述
    private String goodsSimpleEpx;
    //商品内容
    private String goodsContext;
    //商品成分
    private String goodsComposition;
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
    
    public GoodsName(){
        
    }  
    
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getLangKbn() {
		return langKbn;
	}
	public void setLangKbn(String langKbn) {
		this.langKbn = langKbn;
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
                + ", langKbn = "+langKbn
                + ", goodsNm = "+goodsNm
                + ", goodsSimpleEpx = "+goodsSimpleEpx
                + ", goodsContext = "+goodsContext
                + ", goodsComposition = "+goodsComposition
                + ", delFlg = "+delFlg
                + ", createUser = "+createUser
                + ", creadeDate = "+creadeDate
                + ", updateUser = "+updateUser
                + ", updateDate = "+updateDate
				+"]";
				         
       return str;
    }
}
