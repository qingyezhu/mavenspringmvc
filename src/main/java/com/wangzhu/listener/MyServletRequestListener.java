package com.wangzhu.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServletRequestListener implements ServletRequestListener {

	private static final Logger logger = LoggerFactory
			.getLogger(MyServletRequestListener.class);

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		MyServletRequestListener.logger
				.info("MyServletRequestListener requestDestroyed");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		MyServletRequestListener.logger
				.info("MyServletRequestListener requestInitialized");
	}
}
