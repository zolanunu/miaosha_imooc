package com.imooc.miaosha.result;

public class CodeMsg {

	private int code;
	private String msg;

	// 通用模块
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器异常");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

	// 登录模块：5002**
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在已经失效");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "电话号码不能为空");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "电话号码格式错误");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "电话号码不存在");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");

	
	// 秒杀模块 5005**
	public static CodeMsg GOODSSTOCK_ERROR = new CodeMsg(500500, "商品秒杀完毕,已无库存");
	public static CodeMsg REPEATE_ERROR = new CodeMsg(500501, "不能重复秒杀");
	
	private CodeMsg() {
	}

	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}

}
