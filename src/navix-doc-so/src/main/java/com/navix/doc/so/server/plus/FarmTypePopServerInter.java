package com.navix.doc.so.server.plus;

import java.util.List;

import com.navix.core.auth.domain.LoginUser;
import com.navix.doc.so.server.plus.domain.DocAudit;
import com.navix.doc.so.server.plus.domain.Doctypepop;

public interface FarmTypePopServerInter {
	public DocAudit auditHandleForDocCreate(String docid, String textid, LoginUser user);

	public String getMessageForNeedAudit(String title);

	public String getMessageTitleForNeedAudit(String title);

	public DocAudit auditHandleForDocEdit(String docid, String textid, LoginUser user);

	public void delAuditInfo(String docid);

	public void getTypePopsHandle(List<Doctypepop> poplist, String typereadpop, String typewritepop, String auditpop,
			String typeid);

	public void delTypePop(String typeId);
}
