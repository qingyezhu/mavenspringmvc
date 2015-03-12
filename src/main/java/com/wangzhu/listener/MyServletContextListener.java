package com.wangzhu.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Servlet�ļ�����Listener<br/>
 * ��WebӦ�õ�������������ֻ��ʼ��һ�Σ�����WebӦ�õ�ֹͣ������<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1����12:20:42
 * 
 */
public class MyServletContextListener implements ServletContextListener {

	/**
	 * Ӧ�ü������ĳ�ʼ������
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener contextInitialized");
		try {
			/**
			 * �ֲ����ã�����ĿMETA-INFO�����½�context.xml�ļ�<br/>
			 * jndi(java Naming and
			 * DirectoryInterface,java������Ŀ¼�ӿ�)��һ����JavaӦ���з���������Ŀ¼�����API��<br/>
			 * �����������ƺͶ�����ϵ������ʹ�����ǿ��������Ʒ��ʶ���Ŀ¼������һ���������������ַ�������󲻵������ƣ��������ԡ�<br/>
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
	 * Ӧ�ü����������ٷ���
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener contextDestroyed");
	}

}
