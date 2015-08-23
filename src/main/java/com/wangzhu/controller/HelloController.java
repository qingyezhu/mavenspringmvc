package com.wangzhu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wangzhu.util.DateFormatUtil;

@Controller
public class HelloController {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss SSS");

	public static Date parseDate() throws ParseException {
		return HelloController.sdf.parse("2015-05-15 17:05:30 333");
	}

	@RequestMapping("/hello")
	public String printHello(ModelMap model) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println(HelloController.parseDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "hello world! Spring MVC Framework!");
		return "hello";
	}

	private static String[] patternArr = { "yyyy-MM-dd", "yyyy-MM" };
	private static int i = 0;

	@RequestMapping("/hello2")
	public ModelAndView printHello2() {
		HelloController.i++;
		System.out.println(DateFormatUtil.format(new Date(),
				HelloController.patternArr[HelloController.i & 1]));
		return new ModelAndView("hello", "message",
				"hello world! Spring MVC Framework!");
	}

	@RequestMapping(value = "/errorMessage", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> errorMessage(@RequestParam String error) {
		Map<String, Object> ret = new LinkedHashMap<String, Object>();
		ret.put("status", 500);
		ret.put("statusText", error);
		return ret;
	}
}
