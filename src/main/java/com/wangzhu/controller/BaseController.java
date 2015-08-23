package com.wangzhu.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {

	@ExceptionHandler(value = { Exception.class })
	public ModelAndView handlerException(Exception e) {

		ModelAndView mav = new ModelAndView("forward:/errorMessage?error="
				+ e.getMessage());
		System.out.println(e.getMessage());
		return mav;
	}

}
