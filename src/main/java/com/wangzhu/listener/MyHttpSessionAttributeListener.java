package com.wangzhu.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements
		HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("MyHttpSessionAttributeListener attributeAdded "
				+ se.getName());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("MyHttpSessionAttributeListener attributeRemoved "
				+ se.getName());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("MyHttpSessionAttributeListener attributeReplaced "
				+ se.getName());
	}
}
