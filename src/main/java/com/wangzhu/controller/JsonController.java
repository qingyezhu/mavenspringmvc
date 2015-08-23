package com.wangzhu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhu.service.IHelperService;

@Controller
@RequestMapping("/json")
public class JsonController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonController.class);

	@Value("${connection.url}")
	private String url;
	@Value("${jdbc.connection.username}")
	private String user;
	@Value("${connection.password}")
	private String pwd;

	@Value("#{app.time}")
	private Integer time;

	@Autowired
	private IHelperService helperService;

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss SSS");

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
		Date date = null;
		try {
			date = JsonController.sdf.parse("2015-06-17 22:19:22 333");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JsonController.logger.info("date = {}", date);
		data.put("date", date);

		data.put("list", helperService.queryFinancial());

		Map<String, Object> ret = new LinkedHashMap<String, Object>();
		ret.put("status", 0);
		ret.put("statusText", "ok");
		ret.put("data", data);
		return ret;
	}
}
