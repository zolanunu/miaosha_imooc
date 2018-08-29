package com.imooc.miaosha.result;

public class CodeMsg {
	private int code;
	private String msg;

	// ͨ��ģ��
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "�������쳣");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "����У���쳣��%s");

	// ��¼ģ�飺5002**
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session�������Ѿ�ʧЧ");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "��¼���벻��Ϊ��");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "�绰���벻��Ϊ��");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "�绰�����ʽ����");
	public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "�绰���벻����");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "�������");
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
