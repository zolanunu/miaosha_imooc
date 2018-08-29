package com.imooc.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	// 通用模块
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器异常");
	
	
	// 登录模块：5002**
	
	
	// 商品模块：5003**
	
	
	// 订单模块：5004**
	
	
	// 秒杀模块：5005**
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
}
