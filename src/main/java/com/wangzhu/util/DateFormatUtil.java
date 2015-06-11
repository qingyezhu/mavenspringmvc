package com.wangzhu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatUtil {
    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同日期格式的SDF的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 返回一个ThreadLocal的sdf，每个线程只会new一次sdf
     * 
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
	ThreadLocal<SimpleDateFormat> local = sdfMap.get(pattern);
	if (local == null) {
	    synchronized (lockObj) {
		local = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
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
