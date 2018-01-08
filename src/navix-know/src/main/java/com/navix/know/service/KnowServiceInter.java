package com.navix.know.service;

import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.exception.CanNoWriteException;
import com.navix.doc.server.FarmDocOperateRightInter.POP_TYPE;
import com.navix.authority.domain.User;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.query.DataQuery;

/**
 * 商品管理
 * 
 * @author MAC_wd
 * 
 */
public interface KnowServiceInter {
	public static final String LUCENE_DIR = "KNOW";

	/**
	 * 用户创建一条商品
	 * 
	 * @param knowtitle
	 *            商品标题
	 * @param knowtype
	 *            商品分类
	 * @param text
	 *            商品内容
	 * @param knowtag
	 *            商品标签
	 * @param pop_type_edit
	 *            编辑权限
	 * @param pop_type_read
	 *            阅读权限
	 * @param groupId
	 *            店铺id（可以为空）
	 * @param currentUser
	 *            当前操作用户
	 * @return 文档对象
	 */
	public DocEntire creatKnow(String knowtitle, String knowtypeId, String text, String knowtag, POP_TYPE pop_type_edit,
			POP_TYPE pop_type_read, String groupId, LoginUser currentUser);

	/**
	 * 高级用户修改一条商品
	/**
	 * @param id
	 * @param knowtitle
	 * @param knowtype
	 * @param text
	 * @param knowtag
	 * @param pop_type_edit
	 * @param pop_type_read
	 * @param groupId
	 * @param currentUser
	 * @param editNote
	 * @return
	 * @throws CanNoWriteException
	 */
	public DocEntire editKnow(String id, String knowtitle, String knowtype, String text, String knowtag,
			POP_TYPE pop_type_edit, POP_TYPE pop_type_read, String groupId, LoginUser currentUser, String editNote)
					throws CanNoWriteException;

	/**
	 * 普通用户修改商品标签和内容
	 * 
	 * @param docid
	 *            商品主键
	 * @param text
	 *            内容
	 * @param knowtag
	 * @param currentUser
	 * @param editNote
	 *            修改备注
	 * @return
	 */
	public DocEntire editKnow(String docid, String text, String knowtag, LoginUser currentUser, String editNote)
			throws CanNoWriteException;


	/**
	 * 由网页获得一条商品
	 * 
	 * @param url
	 * @return
	 */
	public DocEntire getDocByWeb(String url, LoginUser user);

	/**
	 * 展示当前用户的商品
	 * 
	 * @param query
	 * @return
	 */
	public DataQuery getMyDocQuery(DataQuery dataQuery, User user);
}