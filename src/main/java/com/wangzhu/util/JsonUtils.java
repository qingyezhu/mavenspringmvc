package com.wangzhu.util;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectMapper.DefaultTyping;

public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		JsonUtils.mapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
	}

	public static String toJson(Object obj) {
		try {
			String json = JsonUtils.mapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return JsonUtils.mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new JsonException(e);
		}
	}

	public static void main(String[] args) {
		User user = new User(123, "Li Lei", 99.09D, false);
		System.out.println("before user=" + user);
		String json = JsonUtils.toJson(user);
		System.out.println("json=" + json);
		System.out
				.println("after user=" + JsonUtils.fromJson(json, User.class));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aid", 1L);
		map.put("age", 22);
		map.put("name", "java");
		map.put("score", 99.09D);
		map.put("gender", false);
		map.put("user", user);
		Map<String, Object> one = new HashMap<String, Object>();
		one.put("otherUser", user);
		one.put("oid", 0);
		map.put("other", one);
		System.out.println("before map=" + map);
		json = JsonUtils.toJson(map);
		System.out.println("json=" + json);
		System.out.println("after map=" + JsonUtils.fromJson(json, Map.class));
	}

	/**
	 * 需要无参构造函数与set/get方法<br/>
	 * 
	 * @author wangzhu
	 * @date 2015-5-21下午11:08:04
	 * 
	 */
	static class User {
		private Integer id;
		private String name;
		private Double score;
		private Boolean gender;

		public User() {
		}

		public User(Integer id, String name, Double score, Boolean gender) {
			this.id = id;
			this.name = name;
			this.score = score;
			this.gender = gender;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getScore() {
			return score;
		}

		public void setScore(Double score) {
			this.score = score;
		}

		public Boolean getGender() {
			return gender;
		}

		public void setGender(Boolean gender) {
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "{id=" + id + ", name=" + name + ", score=" + score
					+ ", gender=" + gender + "}";
		}

	}
}

@SuppressWarnings("serial")
class JsonException extends RuntimeException {

	public JsonException(Exception e) {
		super(e);
	}

}