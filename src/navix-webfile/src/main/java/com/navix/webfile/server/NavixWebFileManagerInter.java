package com.navix.webfile.server;

import java.util.List;

import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.server.FarmDocOperateRightInter.POP_TYPE;
import com.navix.core.auth.domain.LoginUser;

public interface NavixWebFileManagerInter {
	public static final String LUCENE_DIR = "WEBFILE";

	/**
	 * 创建一个网络资源文件
	 * 
	 * @param fileid
	 *            文件id
	 * @param typeId
	 *            分类
	 * @param fileName
	 *            名称
	 * @param tag
	 *            标签
	 * @param groupId
	 *            店铺id
	 * @param editPop
	 *            修改权限类型
	 * @param readPop
	 *            阅读权限类型
	 * @return 资源文件ID
	 */
	public DocEntire creatWebFile(List<String> fileid, String typeId, String fileName, String tag, String groupId, String text,
			POP_TYPE editPop, POP_TYPE readPop, LoginUser currentUser);

	/**
	 * 修改一个网络资源文件
	 * 
	 * @param docid
	 *            资源文件ID
	 * @param fileid
	 *            文件id
	 * @param typeId
	 *            分类
	 * @param fileName
	 *            名称
	 * @param tag
	 *            标签
	 * @param groupId
	 *            店铺id
	 * @param editPop
	 *            修改权限类型
	 * @param readPop
	 *            阅读权限类型
	 * @return 资源文件ID
	 * @return
	 */
	public DocEntire editWebFile(String docid, List<String> fileid, String typeId, String fileName, String tag, String groupId,
			String text, POP_TYPE editPop, POP_TYPE readPop, String editNote, LoginUser currentUser);

}
