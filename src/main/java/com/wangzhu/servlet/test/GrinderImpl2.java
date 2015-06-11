package com.wangzhu.servlet.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrinderImpl2 implements IGrinder {
	private static final Logger logger = LoggerFactory
			.getLogger(GrinderImpl2.class);

	private static GrinderImpl2 instance = new GrinderImpl2();

	private StringBuffer sAccum = new StringBuffer();
	private StringBuffer sItemAccum = new StringBuffer();

	private GrinderImpl2() {
	}

	public static GrinderImpl2 getGrinder() {
		return GrinderImpl2.instance;
	}

	@Override
	public synchronized String grindCPU(int level) {
		sAccum.setLength(0);
		sItemAccum.setLength(0);
		sItemAccum.append(IGrinder.randstr);
		for (int i = 0; i < level; i++) {
			sAccum.append(sItemAccum).append("@");
			this.reverse();
		}
		return sAccum.toString();
	}

	private void reverse() {
		sItemAccum = new StringBuffer(sAccum);
		sItemAccum.reverse();
	}
}