package com.wangzhu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 为请求或响应统一设置编码<br/>
 * 其随Web应用的启动而启动，只初始化一次，拦截相关请求<br/>
 * 当Web应用销毁时而被销毁<br/>
 * 
 * @author wangzhu
 * @date 2015-1-31下午11:41:51
 * 
 */
public class CharacterEncodingFilter implements Filter {

	private final static String ENCODING = "UTF-8";

	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CharacterEncodingPage init()");
		encoding = filterConfig.getInitParameter("encoding");
		if (StringUtils.isEmpty(encoding)) {
			encoding = CharacterEncodingFilter.ENCODING;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("CharacterEncodingPage doFilter()");

		// 设置请求【request】与响应【response】的编码方式
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);

		// 将请求转发到目的地
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("CharacterEncodingPage destroy()");
	}

}
