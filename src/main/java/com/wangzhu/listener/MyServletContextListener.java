package com.wangzhu.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Servlet的监听器Listener<br/>
 * 随Web应用的启动而启动，只初始化一次，并随Web应用的停止而销毁<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1上午12:20:42
 * 
 */
public class MyServletContextListener implements ServletContextListener {

	/**
	 * 应用监听器的初始化方法
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener contextInitialized");
		try {
			/**
			 * 局部配置：在项目META-INFO下面新建context.xml文件<br/>
			 * jndi(java Naming and
			 * DirectoryInterface,java命名和目录接口)是一组在Java应用中访问命名和目录服务的API。<br/>
			 * 命名服务将名称和对象联系起来，使得我们可以用名称访问对象。目录服务是一种命名服务，在这种服务里，对象不但有名称，还有属性。<br/>
			 */
			Context ct = new InitialContext();
			DataSource ds = (DataSource) ct
					.lookup("java:comp/env/jdbc/test_jndi_oracle");
			System.out.println(ds.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 应用监听器的销毁方法
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener contextDestroyed");
	}

}
