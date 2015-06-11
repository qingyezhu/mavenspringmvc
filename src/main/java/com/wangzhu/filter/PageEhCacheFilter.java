package com.wangzhu.filter;

import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 这里的PageEhCacheFilter继承了SimplePageCachingFilter，
 * 一般情况下SimplePageCachingFilter就够用了
 * ，这里是为了满足当前系统需求才做了覆盖操作。使用SimplePageCachingFilter需要在web
 * .xml中配置cacheName，cacheName默认是SimplePageCachingFilter，对应ehcache.xml中的cache配置。
 * 
 * @author wangzhu
 * @date 2015-1-15下午4:37:20
 * 
 */
public class PageEhCacheFilter extends SimplePageCachingFilter {

	private static final Logger logger = LoggerFactory
			.getLogger(PageEhCacheFilter.class);
	private static final String FILTER_URL_PATTERNS = "patterns";
	private static String[] cacheURLs;

	@Override
	protected void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws AlreadyGzippedException, AlreadyCommittedException,
			FilterNonReentrantException, LockTimeoutException, Exception {
		if (PageEhCacheFilter.cacheURLs == null) {
			this.init();
		}
		String url = request.getRequestURI();
		boolean flag = false;
		if ((PageEhCacheFilter.cacheURLs != null)
				&& (PageEhCacheFilter.cacheURLs.length > 0)) {
			for (String cacheURL : PageEhCacheFilter.cacheURLs) {
				if (url.contains(cacheURL.trim())) {
					flag = true;
					break;
				}
			}
		}

		if (flag) {
			String query = request.getQueryString();
			if (query != null) {
				query = "?" + query;
			}
			PageEhCacheFilter.logger.info("当前请求被缓存：{}", url + query);
			super.doFilter(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void init() {
		String patterns = filterConfig
				.getInitParameter(PageEhCacheFilter.FILTER_URL_PATTERNS);
		PageEhCacheFilter.cacheURLs = StringUtils.split(patterns, ",");
	}

	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request) {
		boolean ie6 = this.headerContains(request, "User-Agent", "MSIE 6.0");
		boolean ie7 = this.headerContains(request, "User-Agent", "MSIE 7.0");
		return this.acceptsEncoding(request, "gzip") || ie6 || ie7;
	}

	private boolean headerContains(final HttpServletRequest request,
			final String header, final String value) {
		this.logRequestHeaders(request);
		final Enumeration accepted = request.getHeaders(header);
		while (accepted.hasMoreElements()) {
			final String headerValue = (String) accepted.nextElement();
			if (headerValue.indexOf(value) != -1) {
				return true;
			}
		}
		return false;
	}

}
