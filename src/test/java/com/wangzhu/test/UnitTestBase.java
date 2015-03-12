package com.wangzhu.test;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class UnitTestBase {

	private String configLocation;
	private ClassPathXmlApplicationContext context;

	public UnitTestBase() {
	}

	public UnitTestBase(String configLocation) {
		this.configLocation = configLocation;
	}

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext(configLocation);
		context.start();
	}

	@After
	public void after() {
		String[] beanNameArr = context
				.getBeanNamesForAnnotation(Component.class);
		System.out.println(Arrays.toString(beanNameArr));
		context.stop();
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getBean(Class clazz) {
		return (T) context.getBean(clazz);
	}

}
