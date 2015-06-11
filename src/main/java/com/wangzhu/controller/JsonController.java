package com.wangzhu.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/json")
public class JsonController {

	@Value("#{jdbc['mysql.url']}")
	private String url;
	@Value("#{jdbc['mysql.user']}")
	private String user;
	@Value("#{jdbc['mysql.pwd']}")
	private String pwd;

	@Value("#{app.time}")
	private Integer time;

	@RequestMapping(value = "getJson", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getJson(final HttpServletRequest request,
			final HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("age", 22);
		data.put("gender", "female");
		data.put("nick", "紫竹林");
		data.put("desc", "既不回头，何须不忘");
		data.put("url", url);
		data.put("user", user);
		data.put("pwd", pwd);
		data.put("time", time);

		Map<String, Object> ret = new LinkedHashMap<String, Object>();
		ret.put("status", 0);
		ret.put("statusText", "ok");
		ret.put("data", data);
		return ret;
	}
}
