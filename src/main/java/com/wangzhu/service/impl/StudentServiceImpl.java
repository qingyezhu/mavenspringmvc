package com.wangzhu.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangzhu.dataset.StudentDateSet;
import com.wangzhu.service.StudentService;
import com.wangzhu.util.DateUtils;
import com.wangzhu.vo.StudentListVo;
import com.wangzhu.vo.StudentVo;

@Component("studentService")
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceImpl.class);
	@Autowired
	private StudentDateSet studentDateSet;

	/**
	 * url:http://<host>:<port>/<appcontext>/services/V1/studentService/status
	 */
	@Override
	@GET
	@Path("/status")
	public String getStatus() {
		StudentServiceImpl.logger.info("StudentServiceImpl getStatus time:{}",
				DateUtils.getYMDHMST());
		return "getStatus";
	}

	/**
	 * url:http://<host>:<port>/<appcontext>/services/V1/studentService/students
	 * /{ index}
	 */
	@Override
	@GET
	@Path("/students/{index}")
	public StudentVo getStudentById(@PathParam("index") Integer id) {
		List<StudentVo> studentList = StudentDateSet.getList();
		StudentVo vo = null;
		if (studentList.size() > id) {
			vo = studentList.get(id - 1);
		}
		StudentServiceImpl.logger.info(
				"StudentServiceImpl getStudentById time:{},student:{}",
				new Object[] { DateUtils.getYMDHMST(), vo });
		return vo;
	}

	/**
	 * url:http://<host>:<port>/<appcontext>/services/V1/studentService/students
	 */
	@Override
	@GET
	@Path("/students")
	public StudentListVo getStudentList() {
		List<StudentVo> studentList = StudentDateSet.getList();
		StudentListVo listVo = new StudentListVo(studentList);
		StudentServiceImpl.logger.info(
				"StudentServiceImpl getStudentList time:{},result:{}",
				new Object[] { DateUtils.getYMDHMST(), listVo });
		return listVo;
	}

}
