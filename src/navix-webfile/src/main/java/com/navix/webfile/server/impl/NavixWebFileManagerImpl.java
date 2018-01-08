package com.navix.webfile.server.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.navix.doc.domain.Doc;
import com.navix.doc.domain.FarmDocfile;
import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.exception.CanNoWriteException;
import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.doc.server.FarmDocOperateRightInter.POP_TYPE;
import com.navix.doc.server.FarmDocTypeInter;
import com.navix.doc.server.FarmFileIndexManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.core.auth.domain.LoginUser;
import com.navix.webfile.server.NavixWebFileManagerInter;

@Service
public class NavixWebFileManagerImpl implements NavixWebFileManagerInter {
	@Resource
	private FarmDocManagerInter farmDocManagerImpl;
	@Resource
	private FarmFileManagerInter farmFileManagerImpl;
	@Resource
	private FarmDocOperateRightInter farmDocOperateRightImpl;
	@Resource
	private FarmDocTypeInter farmDocTypeManagerImpl;
	@Resource
	private FarmFileIndexManagerInter farmFileIndexManagerImpl;

	@Override
	@Transactional
	public DocEntire creatWebFile(List<String> fileid, String typeId, String fileName, String tag, String groupId,
			String text, POP_TYPE editPop, POP_TYPE readPop, LoginUser currentUser) {
		Doc doc = new Doc();
		doc.setTitle(fileName);
		doc.setTagkey(tag);
		doc.setDomtype("5");
		doc.setDocgroupid(groupId);
		doc.setReadpop(readPop.getValue());
		doc.setWritepop(editPop.getValue());
		doc.setState("1");
		DocEntire doce = new DocEntire(doc);
		doce.setTexts(text, currentUser);
		doce.setType(farmDocTypeManagerImpl.getType(typeId));
		doce = farmDocManagerImpl.createDoc(doce, currentUser);
		for (String id : fileid) {
			farmFileManagerImpl.addFileForDoc(doce.getDoc().getId(), id, currentUser);
			farmFileManagerImpl.submitFile(id);
		}
		return doce;
	}

	@Override
	@Transactional
	public DocEntire editWebFile(String docid, List<String> fileid, String typeId, String fileName, String tag,
			String groupId, String text, POP_TYPE editPop, POP_TYPE readPop, String editNote, LoginUser currentUser) {
		DocEntire doc = farmDocManagerImpl.getDoc(docid);
		List<FarmDocfile> originalfiles=doc.getFiles();
		doc.getDoc().setTitle(fileName);
		doc.getDoc().setTagkey(tag);
		doc.getDoc().setDomtype("5");
		doc.getDoc().setDocgroupid(groupId);
		doc.getDoc().setReadpop(readPop.getValue());
		doc.getDoc().setWritepop(editPop.getValue());
		doc.setTexts(text, currentUser);
		doc.setFiles(new ArrayList<FarmDocfile>());// 重置附件_zhanghc_20150919
		for (String id : fileid) {
			doc.addFile(farmFileManagerImpl.getFile(id));
		}
		farmFileIndexManagerImpl.delFileLucenneIndexs(originalfiles,doc.getFiles(), doc);
		doc.setType(farmDocTypeManagerImpl.getType(typeId));
		try {
			farmFileManagerImpl.delFileForDoc(doc.getDoc().getId(), currentUser);
			doc = farmDocManagerImpl.editDocByUser(doc, editNote, currentUser);
		} catch (CanNoWriteException e) {
			throw new RuntimeException(e);
		}
		return doc;
	}

}
