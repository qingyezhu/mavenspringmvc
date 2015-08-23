package com.wangzhu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHttpSessionAttributeListener implements
		HttpSessionAttributeListener {
	private static final Logger logger = LoggerFactory
			.getLogger(MyHttpSessionAttributeListener.class);

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		MyHttpSessionAttributeListener.logger
				.info("MyHttpSessionAttributeListener attributeAdded "
						+ se.getName());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		MyHttpSessionAttributeListener.logger
				.info("MyHttpSessionAttributeListener attributeRemoved "
						+ se.getName());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		MyHttpSessionAttributeListener.logger
				.info("MyHttpSessionAttributeListener attributeReplaced "
						+ se.getName());
	}
}
