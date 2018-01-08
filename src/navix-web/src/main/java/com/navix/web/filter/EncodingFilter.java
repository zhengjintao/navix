package com.navix.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.navix.core.config.AppConfig;

/**
 * 根据配置文件设置字符集
 * 
 * @author teikintou
 * @date 2017.1.2
 * 
 */
public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
	}
	
	@Override
	/**
	 * dofilter
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encode = AppConfig.getString("config.filter.encode");
		request.setCharacterEncoding(encode);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}