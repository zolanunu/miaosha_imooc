package com.imooc.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	// ͨ��ģ��
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "�������쳣");
	
	
	// ��¼ģ�飺5002**
	
	
	// ��Ʒģ�飺5003**
	
	
	// ����ģ�飺5004**
	
	
	// ��ɱģ�飺5005**
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
