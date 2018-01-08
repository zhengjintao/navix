package com.navix.authority;

import javax.servlet.http.HttpSession;

import com.navix.authority.domain.Organization;
import com.navix.authority.service.UserServiceInter;
import com.navix.core.auth.domain.LoginUser;
import com.navix.core.util.spring.BeanFactory;
import com.navix.core.web.constant.FarmConstant;

public class AuthUtils {
	public static Organization getCurrentOrganization(HttpSession session) {
		UserServiceInter users = (UserServiceInter) BeanFactory.getBean("userServiceImpl");
		LoginUser user = (LoginUser) session.getAttribute(FarmConstant.SESSION_USEROBJ);
		return users.getOrg(user.getId());
	}
}
