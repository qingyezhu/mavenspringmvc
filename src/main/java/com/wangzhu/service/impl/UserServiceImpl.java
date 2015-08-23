package com.wangzhu.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhu.dao.IUserDao;
import com.wangzhu.entity.User;
import com.wangzhu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao dao;

	@Override
	public void saveOrUpdate(User user) {
		dao.saveOrUpdate(user);
	}

	@Override
	public User find(String id) {
		User user = dao.get(id);
		UserServiceImpl.logger.info("get user = {}", user);
		int rand = new Random().nextInt(1000);
		// user.setVersion(Math.round(100));
		User user1 = new User();
		user1.setName("no name" + rand);
		user1.setPass("no pwd" + rand);
		user1.setVersion(rand);
		UserServiceImpl.logger.info("save before user1={}", user1);
		dao.save(user1);
		UserServiceImpl.logger.info("save after user1={}", user1);
		user1.setVersion((rand * 3) % 1000);
		UserServiceImpl.logger.info("change user1={}", user1);
		return user1;
	}

}
