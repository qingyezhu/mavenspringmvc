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
		// 加载Ehcache配置文件
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		// 列出所有的缓存名称，不包括配置文件中的defaultCache
		String[] names = cm.getCacheNames();
		Assert.assertEquals(1, names.length);
		Assert.assertEquals(names[0], "data-cache");

		// 根据指定名称查找缓存对象
		Cache cache = cm.getCache("data-cache");
		Assert.assertNotNull(cache);

		// 获取，更新Cache配置的接口
		CacheConfiguration configuration = cache.getCacheConfiguration();
		configuration.setTimeToIdleSeconds(3600);

		// 缓存在内存中的配置信息，缓存配置动态修改也会体现出来
		System.out.println(cm.getActiveConfigurationText());

		// 清除所有缓存的数据，但是缓存本身仍然存在
		cm.clearAll();

		// 从内存中删除一个缓存以及所有的数据，Cache被销毁
		cm.removeCache("data-cache");
	}

	public void testAddElementToCache() {
		// 加载Ehcache配置文件
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

		// 把数据从内存刷到DiskStore，从DiskStore刷新到Disk中
		cache.flush();
	}

	public void testUpdateElementToCache() {
		// 加载Ehcache配置文件
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
		// 加载Ehcache配置文件
		InputStream in = EhcacheTest.class.getClassLoader()
				.getResourceAsStream("ehcache.xml");
		CacheManager cm = CacheManager.create(in);
		Cache cache = cm.getCache("data-cache");

		// Ehcache中的数据类型是Element，它包含Key，Value和一个版本信息
		Element e = new Element(1000, 10000, 1);
		cache.put(e);

		e = new Element(2000, 20000, 1);
		cache.put(e);

		// 缓存中有两条数据
		Assert.assertEquals(2, cache.getSize());

		// 通过get方法获取key对应的数据
		e = cache.get(1000);
		Assert.assertEquals(10000, e.getObjectValue());

		Query q = cache.createQuery();

		// 属性是泛型类，得到key对应的查询属性对象：根据默认提供的可查询属性key进行查询
		Attribute<Integer> keyAttribute = cache.getSearchAttribute("key");

		// 构造查询条件，这是一个链式写法，一个Query对象可以写多个查询条件，创建查找key的值为2000的查询
		q = q.addCriteria(keyAttribute.eq(2000));

		// 如果不includeKeys和includeValues，则测试结果集中不包括Keys和Values信息
		q.includeKeys();
		q.includeValues();

		// 执行查询
		Results results = q.execute();
		Assert.assertNotNull(results);
		Assert.assertEquals(1, results.size());

		// 列出所有查询结果
		List<Result> resultList = results.all();
		Result result = resultList.get(0);
		Assert.assertEquals(2000, result.getKey());
		Assert.assertEquals(20000, result.getValue());

	}

	@Test
	public void testRemoveElementToCache() {
		// 加载Ehcache配置文件
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
		// e1已经被删除，因此操作返回false
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