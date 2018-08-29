package com.imooc.miaosha.redis;

public abstract class BasePrefix implements KeyPrefix {
	private int expireSeconds;
	private String prefix;
	public BasePrefix(String prefix) {
		this(0, prefix);
	}
	public BasePrefix(int expireSeconds, String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	public int expireSeconds() {
		// 默认0代表永不过期
		
		return expireSeconds;
	}
	public String getPrefix() {
		String clasName = getClass().getSimpleName();
		return clasName + ":" + prefix;
	}
}
