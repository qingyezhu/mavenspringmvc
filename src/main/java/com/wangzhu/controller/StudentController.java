package com.wangzhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangzhu.exception.SpringException;
import com.wangzhu.model.Student;

@Controller
public class StudentController {

	@RequestMapping("/addStudent")
	public String addStudent(@ModelAttribute("SpringWeb") Student student,
			ModelMap model) {
		String name = student.getName();
		Integer age = student.getAge();
		Integer id = student.getId();
		if ((name == null) || (name.length() > 10)) {
			throw new SpringException("name为NULL或太长了");
		}
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("id", id);
		return "result";
	}
}
