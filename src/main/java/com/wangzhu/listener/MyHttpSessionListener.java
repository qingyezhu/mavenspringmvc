package com.wangzhu.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory
			.getLogger(MyHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		MyHttpSessionListener.logger
				.info("MyHttpSessionListener sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		MyHttpSessionListener.logger
				.info("MyHttpSessionListener sessionDestroyed");
	}

}
