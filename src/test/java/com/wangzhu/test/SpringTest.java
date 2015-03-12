package com.wangzhu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class SpringTest extends UnitTestBase {

	public SpringTest() {
		super("classpath*:applicationContext*.xml");
	}

	@Test
	public void test() {

	}
}
