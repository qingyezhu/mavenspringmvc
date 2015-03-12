package com.wangzhu.test;

import java.io.InputStream;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.junit.Assert;
import org.junit.Test;

public class EhcacheTest {
	public void testCacheManager() {
		// ����Ehcache�����ļ�
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		// �г����еĻ������ƣ������������ļ��е�defaultCache
		String[] names = cm.getCacheNames();
		Assert.assertEquals(1, names.length);
		Assert.assertEquals(names[0], "data-cache");

		// ����ָ�����Ʋ��һ������
		Cache cache = cm.getCache("data-cache");
		Assert.assertNotNull(cache);

		// ��ȡ������Cache���õĽӿ�
		CacheConfiguration configuration = cache.getCacheConfiguration();
		configuration.setTimeToIdleSeconds(3600);

		// �������ڴ��е�������Ϣ���������ö�̬�޸�Ҳ�����ֳ���
		System.out.println(cm.getActiveConfigurationText());

		// ������л�������ݣ����ǻ��汾����Ȼ����
		cm.clearAll();

		// ���ڴ���ɾ��һ�������Լ����е����ݣ�Cache������
		cm.removeCache("data-cache");
	}

	public void testAddElementToCache() {
		// ����Ehcache�����ļ�
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("data-cache");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);

		cache.putIfAbsent(new Element(p1, p1, 1));
		cache.put(new Element(p2, p2, 1));
		cache.putIfAbsent(new Element(p2, p1, 1));

		Element e = cache.get(p2);
		System.out.println(e.getObjectKey() + "=========" + e.getObjectValue());
		Assert.assertEquals(p2, e.getObjectValue());

		// �����ݴ��ڴ�ˢ��DiskStore����DiskStoreˢ�µ�Disk��
		cache.flush();
	}

	public void testUpdateElementToCache() {
		// ����Ehcache�����ļ�
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("data-cache");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);

		Element e1 = new Element(p1, p1, 1);
		cache.putIfAbsent(e1);
		Element e2 = new Element(p2, p2, 1);
		cache.put(e2);
		e2 = new Element(p2, p1, 1);
		cache.replace(e2);
		Assert.assertEquals(p1, e2.getObjectValue());
	}

	public void testQueryElementToCache() {
		// ����Ehcache�����ļ�
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("data-cache");

		// Ehcache�е�����������Element��������Key��Value��һ���汾��Ϣ
		Element e = new Element(1000, 10000, 1);
		cache.put(e);

		e = new Element(2000, 20000, 1);
		cache.put(e);

		// ����������������
		Assert.assertEquals(2, cache.getSize());

		// ͨ��get������ȡkey��Ӧ������
		e = cache.get(1000);
		Assert.assertEquals(10000, e.getObjectValue());

		Query q = cache.createQuery();

		// �����Ƿ����࣬�õ�key��Ӧ�Ĳ�ѯ���Զ��󣺸���Ĭ���ṩ�Ŀɲ�ѯ����key���в�ѯ
		Attribute<Integer> keyAttribute = cache.getSearchAttribute("key");

		// �����ѯ����������һ����ʽд����һ��Query�������д�����ѯ��������������key��ֵΪ2000�Ĳ�ѯ
		q = q.addCriteria(keyAttribute.eq(2000));

		// �����includeKeys��includeValues������Խ�����в�����Keys��Values��Ϣ
		q.includeKeys();
		q.includeValues();

		// ִ�в�ѯ
		Results results = q.execute();
		Assert.assertNotNull(results);
		Assert.assertEquals(1, results.size());

		// �г����в�ѯ���
		List<Result> resultList = results.all();
		Result result = resultList.get(0);
		Assert.assertEquals(2000, result.getKey());
		Assert.assertEquals(20000, result.getValue());

	}

	@Test
	public void testRemoveElementToCache() {
		// ����Ehcache�����ļ�
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("data-cache");

		Person p1 = new Person(1, "Jack", 21);
		Person p2 = new Person(2, "Mike", 73);
		Element e1 = new Element(p1, p1, 1);
		cache.putIfAbsent(e1);
		Element e2 = new Element(p2, p2, 1);
		cache.put(e2);

		cache.remove(p1);

		boolean isSucc = cache.removeElement(e1);
		// e1�Ѿ���ɾ������˲�������false
		Assert.assertFalse(isSucc);

		cache.put(e1);

		cache.removeAll();

		Assert.assertEquals(0, cache.getSize());
	}
}

class Person {
	private int id;
	private String name;
	private int age;

	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + age;
		result = (prime * result) + id;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (age != other.age) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", age=" + age + "}";
	}

}