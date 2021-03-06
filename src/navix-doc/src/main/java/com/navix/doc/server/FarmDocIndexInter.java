package com.navix.doc.server;

import java.util.List;

import com.navix.core.sql.result.DataResult;
import com.navix.doc.domain.ex.DocBrief;
import com.navix.doc.domain.ex.DocEntire;

/**
 * 文档全文检索服务
 * 
 */
public interface FarmDocIndexInter {

	/**
	 * 查询相关商品
	 * 
	 * @param docid
	 * @param num
	 * @return
	 */
	List<DocBrief> getRelationDocs(String docid, int num);

	/**
	 * 全文检索
	 * 
	 * @param word
	 * @param pagenum
	 * @return
	 */
	DataResult search(String word, String userid, Integer pagenum) throws Exception;

	/**
	 * 删除文档索引
	 * 
	 * @param doc
	 */
	public void delLuceneIndex(DocEntire doc);
	/**
	 * 删除文档索引,用指定的id，doc是用来区分权限的
	 * 
	 * @param doc
	 */
	public void delLuceneIndex(DocEntire doc,String indexId);
	/**
	 * 删除文档索引,在分类和店铺下
	 * 
	 * @param doc
	 */
	public void delLuceneIndex(String docid, List<String> typeIdList, List<String> groupIdList);

	/**
	 * 添加文档索引
	 * 
	 * @param entity
	 */
	public void addLuceneIndex(DocEntire entity);

	/**
	 * 添加附件索引
	 * 
	 * @param entity
	 */
	public void addLuceneIndex(String fileid, String name, String text, DocEntire entity);

	/**
	 * 重做文档索引
	 * 
	 * @param entity
	 */
	public void reLuceneIndex(DocEntire olddoc, DocEntire newdoc);
}
