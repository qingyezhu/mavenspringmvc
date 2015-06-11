package com.wangzhu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatUtil {
    /**
     * ������
     */
    private static final Object lockObj = new Object();

    /**
     * ��Ų�ͬ���ڸ�ʽ��SDF��Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * ����һ��ThreadLocal��sdf��ÿ���߳�ֻ��newһ��sdf
     * 
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
	ThreadLocal<SimpleDateFormat> local = sdfMap.get(pattern);
	if (local == null) {
	    synchronized (lockObj) {
		local = sdfMap.get(pattern);

		// �˴���˫���жϺ�ͬ����Ϊ�˷�ֹsdfMap������������put�ظ���sdf
		if (local == null) {
		    System.out.println("put new sdf of pattern " + pattern
			    + " to map");

		    local = new ThreadLocal<SimpleDateFormat>() {
			@Override
			protected SimpleDateFormat initialValue() {
			    System.out.println("thread: "
				    + Thread.currentThread()
				    + " init pattern: " + pattern);
			    return new SimpleDateFormat(pattern);
			}
		    };
		    sdfMap.put(pattern, local);
		}
	    }
	}
	return local.get();
    }

    public static String format(Date date, String pattern) {
	return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern)
	    throws ParseException {
	return getSdf(pattern).parse(dateStr);
    }

}
