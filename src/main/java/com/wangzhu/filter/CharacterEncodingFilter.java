package com.wangzhu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ϊ�������Ӧͳһ���ñ���<br/>
 * ����WebӦ�õ�������������ֻ��ʼ��һ�Σ������������<br/>
 * ��WebӦ������ʱ��������<br/>
 * 
 * @author wangzhu
 * @date 2015-1-31����11:41:51
 * 
 */
public class CharacterEncodingFilter implements Filter {
	private static final Logger logger = LoggerFactory
			.getLogger(CharacterEncodingFilter.class);

	private final static String ENCODING = "UTF-8";

	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		CharacterEncodingFilter.logger.info("CharacterEncodingPage init()");
		encoding = filterConfig.getInitParameter("encoding");
		if (StringUtils.isEmpty(encoding)) {
			encoding = CharacterEncodingFilter.ENCODING;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		CharacterEncodingFilter.logger.info("CharacterEncodingPage doFilter()");

		// ��������request������Ӧ��response���ı��뷽ʽ
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);

		// ������ת����Ŀ�ĵ�
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		CharacterEncodingFilter.logger.info("CharacterEncodingPage destroy()");
	}

}
