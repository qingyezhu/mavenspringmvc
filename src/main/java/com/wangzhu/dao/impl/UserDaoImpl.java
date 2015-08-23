package com.wangzhu.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangzhu.dao.IUserDao;
import com.wangzhu.entity.User;

@Repository
public class UserDaoImpl implements IUserDao {
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User get(String id) {
		User user = (User) this.getSession().get(User.class, id);
		UserDaoImpl.logger.info("id={},user={}", id, user);
		return user;
	}

	@Override
	public void save(User user) {
		UserDaoImpl.logger.info("save before user={}", user);
		this.getSession().save(user);
		UserDaoImpl.logger.info("save after user={}", user);
	}

	@Override
	public void update(User user) {
		UserDaoImpl.logger.info("update before user={}", user);
		this.getSession().update(user);
		UserDaoImpl.logger.info("update after user={}", user);
	}

	@Override
	public void saveOrUpdate(User user) {
		UserDaoImpl.logger.info("saveOrUpdate before user={}", user);
		this.getSession().saveOrUpdate(user);
		UserDaoImpl.logger.info("saveOrUpdate after user={}", user);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
