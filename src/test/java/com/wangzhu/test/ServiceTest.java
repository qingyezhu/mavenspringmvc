package com.wangzhu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.wangzhu.service.IHelperService;

@RunWith(BlockJUnit4ClassRunner.class)
public class ServiceTest extends UnitTestBase {
	public ServiceTest() {
		super("classpath*:applicationContext.xml");
	}

	@Test
	public void test() {
		IHelperService service = this.getBean(IHelperService.class);
		System.out.println(service.queryFinancial());
	}
}
