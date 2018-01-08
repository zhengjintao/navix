package com.navix.tag.doc;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.navix.doc.server.FarmDocManagerInter;
import com.navix.doc.server.FarmDocOperateRightInter;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.util.spring.BeanFactory;
import com.navix.core.web.constant.FarmConstant;

public class NoWriteIsShowForUser extends TagSupport {
	private String docId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static FarmDocOperateRightInter aloneIMP = (FarmDocOperateRightInter) BeanFactory
			.getBean("farmDocOperateRightImpl");
	private final static FarmDocManagerInter docIMP = (FarmDocManagerInter) BeanFactory
			.getBean("farmDocManagerImpl");

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) super.pageContext
				.getRequest();
		LoginUser user = (LoginUser) request.getSession().getAttribute(
				FarmConstant.SESSION_USEROBJ);
		// EVAL_BODY_INCLUDE
		// 则执行自定义标签的标签体；
		// 返回SKIP_BODY则忽略自定义标签的标签体，直接解释执行自定义标签的结果标记。
		if (!aloneIMP.isWrite(user, docIMP.getDocOnlyBean(docId))) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

}
