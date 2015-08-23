package com.wangzhu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhu.dao.IHelperDao;
import com.wangzhu.service.IHelperService;

@Service
public class HelperServiceImpl implements IHelperService {

	@Autowired
	private IHelperDao helperDao;

	@Override
	public List<Map<String, Object>> queryFinancial() {
		return helperDao.queryFinancial();
	}

}
