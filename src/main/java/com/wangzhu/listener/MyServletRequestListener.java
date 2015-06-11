package com.wangzhu.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("MyServletRequestListener requestDestroyed");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("MyServletRequestListener requestInitialized");
	}
}
