package com.wangzhu.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangzhu.dao.IHelperDao;
import com.wangzhu.entity.User;

@Repository
public class HelperDaoImpl implements IHelperDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Map<String, Object>> queryFinancial() {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from financial")
				.addScalar("fid", StandardBasicTypes.INTEGER)
				.addScalar("fname", StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		Integer fid = (Integer) list.get(0).get("fid");
		System.out.println(fid);
		query = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from user").addEntity(User.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		return list;
	}

}
