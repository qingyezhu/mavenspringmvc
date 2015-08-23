package com.wangzhu.dao;

import com.wangzhu.entity.User;

public interface IUserDao {
	User get(String id);

	void save(User user);

	void update(User user);

	void saveOrUpdate(User user);

}
