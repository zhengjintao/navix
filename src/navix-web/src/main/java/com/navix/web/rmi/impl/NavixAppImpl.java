package com.navix.web.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.navix.doc.domain.ex.DocEntire;
import com.navix.doc.domain.ex.TypeBrief;
import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter.POP_TYPE;
import com.navix.doc.server.FarmDocTypeInter;
import com.navix.doc.server.FarmFileIndexManagerInter;
import com.navix.doc.server.FarmFileManagerInter;
import com.navix.api.NavixAppInter;
import com.navix.api.exception.DocCreatErrorExcepiton;
import com.navix.authority.FarmAuthorityService;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.sql.result.DataResult;
import com.navix.core.util.spring.BeanFactory;
import com.navix.domain.Results;
import com.navix.know.service.KnowServiceInter;

public class NavixAppImpl extends UnicastRemoteObject implements NavixAppInter {
	public NavixAppImpl() throws RemoteException {
		super();
	}

	private FarmFileIndexManagerInter farmFileIndexManagerImpl = (FarmFileIndexManagerInter) BeanFactory
			.getBean("farmFileIndexManagerImpl");

	private FarmFileManagerInter farmFileManagerImpl = (FarmFileManagerInter) BeanFactory
			.getBean("farmFileManagerImpl");

	private FarmDocManagerInter farmDocManagerImpl = (FarmDocManagerInter) BeanFactory.getBean("farmDocManagerImpl");
	private KnowServiceInter KnowServiceImpl = (KnowServiceInter) BeanFactory.getBean("knowServiceImpl");

	private FarmDocTypeInter farmDocTypeManagerImpl = (FarmDocTypeInter) BeanFactory.getBean("farmDocTypeManagerImpl");

	private static final long serialVersionUID = 1L;

	@Override
	public String creatHTMLKnow(String knowtitle, String knowtypeId, String text, String knowtag, String currentUserId)
			throws RemoteException, DocCreatErrorExcepiton {
		DocEntire doc = null;
		try {
			doc = KnowServiceImpl.creatKnow(knowtitle, knowtypeId, text, knowtag, POP_TYPE.PUB, POP_TYPE.PUB, null,
					null);
		} catch (Exception e) {
			throw new DocCreatErrorExcepiton(e);
		}
		return doc.getDoc().getId();
	}

	@Override
	public Results getTypeDocs(String typeid, int pagesize, int currentpage, String loginname) throws RemoteException {
		LoginUser user = FarmAuthorityService.getInstance().getUserByLoginName(loginname);
		DataResult docs = farmDocTypeManagerImpl.getTypeDocs(user, typeid, pagesize, currentpage);
		Results result = new Results();
		result.setList(docs.getResultList());
		result.setCurrentpage(currentpage);
		result.setPagesize(pagesize);
		result.setTotalsize(docs.getTotalSize());
		return result;
	}

	@Override
	public Results getAllTypes(String loginname) throws RemoteException {
		LoginUser user = FarmAuthorityService.getInstance().getUserByLoginName(loginname);
		List<TypeBrief> types = farmDocTypeManagerImpl.getPopTypesForReadDoc(user);
		Results result = new Results();
		for (TypeBrief node : types) {
			Map<String, Object> map = new HashMap<>();
			map.put("NAME", node.getName());
			map.put("ID", node.getId());
			map.put("PARENTID", node.getParentid());
			map.put("NUM", node.getNum());
			map.put("TYPE", node.getType());
			result.getList().add(map);
		}
		result.setCurrentpage(0);
		result.setPagesize(1);
		result.setTotalsize(types.size());
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void runLuceneIndex(String fileid, String docid, String text) throws RemoteException {
		farmFileIndexManagerImpl.addFileLuceneIndex(fileid, farmDocManagerImpl.getDoc(docid), text);
	}

}
