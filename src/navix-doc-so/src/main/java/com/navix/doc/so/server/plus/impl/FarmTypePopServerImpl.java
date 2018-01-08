package com.navix.doc.so.server.plus.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.navix.core.auth.domain.LoginUser;
import com.navix.doc.so.server.plus.FarmTypePopServerInter;
import com.navix.doc.so.server.plus.domain.DocAudit;
import com.navix.doc.so.server.plus.domain.Doctypepop;

@Service
public class FarmTypePopServerImpl implements FarmTypePopServerInter {
	@Override
	public DocAudit auditHandleForDocCreate(String docid, String textid, LoginUser user) {
		return new DocAudit();
	}

	@Override
	public String getMessageForNeedAudit(String title) {
		return new String();
	}

	@Override
	public String getMessageTitleForNeedAudit(String title) {
		return new String();
	}

	@Override
	public DocAudit auditHandleForDocEdit(String docid, String textid, LoginUser user) {
		return new DocAudit();
	}

	@Override
	public void delAuditInfo(String docid) {
	}

	@Override
	public void getTypePopsHandle(List<Doctypepop> poplist, String typereadpop, String typewritepop, String auditpop,
			String typeid) {
	}

	@Override
	public void delTypePop(String typeId) {
	}

}
