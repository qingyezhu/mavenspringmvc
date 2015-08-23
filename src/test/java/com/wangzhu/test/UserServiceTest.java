package com.wangzhu.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangzhu.entity.User;
import com.wangzhu.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceTest.class);
	@Resource
	private IUserService service;

	@Test
	public void testUserGet() {
		User user = new User();
		user.setName("new one user");
		user.setPass("new one pwd");
		service.saveOrUpdate(user);
		UserServiceTest.logger.info("save user = {}", user);
	}

	@Test
	public void testUserFind() {
		User user = service.find("402881e74e20dc0e014e20dc13960000");
		UserServiceTest.logger.info("find user = {}", user);
	}
}
