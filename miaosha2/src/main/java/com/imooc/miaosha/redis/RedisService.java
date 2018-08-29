package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.domain.MiaoshaUser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	@Autowired
	JedisPool jedisPool;
	
	/**
	 *  获取单个对象
	 * */
	public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
//		JedisPool jPool = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 生成真正的key
			String realKey = prefix.getPrefix() + key;
			String string = jedis.get(realKey);
			T t = StringToBean(string, clazz);
			return t;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 *  设置对象，存到redis缓存中
	 * */
	public <T> boolean set(KeyPrefix prefix, String key, T value) {
//		JedisPool jPool = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String string = beanToString(value);
			if(string == null || string.length() <= 0) {
				return false;
			}
			String realKey = prefix.getPrefix() + key;
			int seconds =prefix.expireSeconds();
			if(seconds <= 0) {
				jedis.set(realKey, string);
			} else {
				jedis.setex(realKey, seconds, string);
			}
			
			return true;
		} finally {
			returnToPool(jedis);
		}
	}
	/**
	 *  判断key是否存在
	 * */
	public <T> boolean existKey(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			return jedis.exists(realKey);
			// return true;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 *  增加值
	 * */
	public <T> Long incr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			return jedis.incr(realKey);
			// return true;
		} finally {
			returnToPool(jedis);
		}
	}
	/**
	 *  减少值
	 * */
	public <T> Long decr(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			return jedis.decr(realKey);
			//return true;
		} finally {
			returnToPool(jedis);
		}
	}
	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T StringToBean(String string, Class<T> clazz) {
		if(string == null || string.length() <= 0 || clazz == null) {
			return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(string);
		} else if(clazz == String.class) {
			return (T) string;
		} else if(clazz == long.class || clazz == Long.class) {
			return (T) Long.valueOf(string);
		} else {
			return JSON.toJavaObject(JSON.parseObject(string), clazz);
		}
	}

	private <T> String beanToString(T value) {
		if(value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			return ""+value;
		} else if(clazz == String.class) {
			return (String)value;
		} else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		} else {
			return JSON.toJSONString(value);
		}
	}	
}
