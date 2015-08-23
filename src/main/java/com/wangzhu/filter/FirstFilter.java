package com.wangzhu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(FirstFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		FirstFilter.logger.info("FirstFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FirstFilter.logger.info("FirstFilter doFilter start");
		chain.doFilter(request, response);
		FirstFilter.logger.info("FirstFilter doFilter end");
	}

	@Override
	public void destroy() {
		FirstFilter.logger.info("FirstFilter init");
	}
}
