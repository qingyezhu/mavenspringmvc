package com.wangzhu.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangzhu.email.SendMailUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringEmailTest {

	@Resource
	private SendMailUtils mailUtils;

	@Test
	public void testSendEmail() {

		mailUtils.sendTxtMail();
	}
}
