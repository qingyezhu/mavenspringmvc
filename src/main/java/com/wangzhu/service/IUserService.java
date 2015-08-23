package com.wangzhu.service;

import com.wangzhu.entity.User;

public interface IUserService {

	void saveOrUpdate(User user);

	User find(String id);
}
