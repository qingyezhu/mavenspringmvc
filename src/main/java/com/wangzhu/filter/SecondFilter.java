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

public class SecondFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(SecondFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SecondFilter.logger.info("SecondFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SecondFilter.logger.info("SecondFilter doFilter start");
		chain.doFilter(request, response);
		SecondFilter.logger.info("SecondFilter doFilter end");
	}

	@Override
	public void destroy() {
		SecondFilter.logger.info("SecondFilter destroy");
	}
}
