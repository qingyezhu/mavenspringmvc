package com.wangzhu.servlet.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrinderImpl1 implements IGrinder {
	private static final Logger logger = LoggerFactory
			.getLogger(GrinderImpl1.class);

	private static GrinderImpl1 instance = new GrinderImpl1();

	private GrinderImpl1() {
	}

	public static GrinderImpl1 getGrinder() {
		return GrinderImpl1.instance;
	}

	@Override
	public synchronized String grindCPU(int level) {
		StringBuffer accum = new StringBuffer();
		String s = IGrinder.randstr;
		for (int i = 0; i < level; i++) {
			accum.append(s).append("@");
			s = this.getReverse(accum.toString());
		}
		return accum.toString();
	}

	private String getReverse(String string) {
		StringBuffer accum = new StringBuffer(string);
		accum.reverse();
		return accum.toString();
	}

}
