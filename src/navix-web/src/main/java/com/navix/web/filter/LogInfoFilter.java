package com.navix.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.navix.core.auth.domain.LoginUser;
import com.navix.core.web.constant.FarmConstant;

/**
 * 日志记录项目设置
 * MDC信息添加
 * "IP" ； 客户端IP地址
 * "USERID" ； 登录用户ID（未登录时设置未 anonymous）
 * @author teikintou
 * @date 2017.1.2
 * 
 */
public class LogInfoFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse reponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		MDC.put("IP", request.getRemoteAddr());
		
		String userID = "anonymous";
		HttpSession session = req.getSession();
		if (session != null) {
			LoginUser user = (LoginUser) session
					.getAttribute(FarmConstant.SESSION_USEROBJ);
			if (user != null) {
				userID = user.getId();
			}
		}
		MDC.put("USERID", userID);
		
		chain.doFilter(request, reponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
