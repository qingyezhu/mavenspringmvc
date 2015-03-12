package com.wangzhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	@RequestMapping("/redirect")
	public String redirect() {
		return "redirect:finalPage.do";
	}

	@RequestMapping("/finalPage")
	public String finalPage() {
		return "final";
	}

}
