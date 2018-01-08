package com.navix.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.navix.api.exception.DocCreatErrorExcepiton;
import com.navix.domain.Results;

public interface NavixAppInter extends Remote {

	/**
	 * 创建html商品信息
	 * 
	 * @param knowtitle
	 *            标题
	 * @param knowtypeId
	 *            分类
	 * @param text
	 *            内容
	 * @param knowtag
	 *            tag
	 * @param groupId
	 *            店铺
	 * @param fileId
	 *            内容图
	 * @param currentUserId
	 *            创建用户
	 * @return
	 * @throws RemoteException
	 */
	public String creatHTMLKnow(String knowtitle, String knowtypeId, String text, String knowtag, String currentUserId)
			throws RemoteException, DocCreatErrorExcepiton;

	/**
	 * 对附件做索引(同时会保存附件文本内容在数据库中方便后续重建索引)
	 * 
	 * @param fileid
	 *            附件id
	 * @param docid
	 *            商品id
	 * @param text
	 *            索引文字
	 * @throws ErrorTypeException
	 * @throws RemoteException
	 */
	public void runLuceneIndex(String fileid, String docid, String text) throws RemoteException;

	/**
	 * 获得分类下的商品
	 * 
	 * @param typeid
	 * @param pagesize
	 * @param pagenum
	 * @return
	 */
	public Results getTypeDocs(String typeid, int pagesize, int currentpage, String loginname)throws RemoteException;

	/**
	 * 获得所有商品分类
	 * 
	 * @param typeid
	 * @param pagesize
	 * @param currentpage
	 * @return
	 */
	public Results getAllTypes(String loginname)throws RemoteException;
}
