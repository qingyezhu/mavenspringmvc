package com.wangzhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String printHello(ModelMap model) {
		model.addAttribute("message", "»¶Ó­ Spring MVC Framework!");
		return "hello";
	}

	@RequestMapping("/hello2")
	public ModelAndView printHello2() {
		return new ModelAndView("hello", "message", "»¶Ó­ Spring MVC Framework!");
	}

	@RequestMapping(value = "/testHello")
	public String testHello(String username, Model model) {
		System.out.println(username);
		model.addAttribute("helloworld", "hello: " + username);
		return "testHello";
	}
}
