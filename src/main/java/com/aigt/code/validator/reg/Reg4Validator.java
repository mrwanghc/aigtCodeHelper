package com.aigt.code.validator.reg;

/**
 * <p>数据校验正则</p>
 */
public enum Reg4Validator {

	/**
	 * 地址正则
	 */
	ADDRESS("^(([^$]{1,})\\$\\$)*([^$]{1,})$"),
	
	/**
	 * 颜色格式
	 */
	COLOR("^([0-9a-fA-F]){6}$"),
	
	/**
	 * 身份证格式
	 */
	IDCARD("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$"),
	
	/**
	 * ip格式
	 */
	IP("^((2[0-4]\\d|25[0-5]|[1]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[1]?\\d\\d?)$"),
	
	/**
	 * 手机号码格式(只限制1开头，后面数字均不限制)
	 */
	MOBILEPHONE("^1\\d{10}$"),
	
	/**
	 * 固话和传真号码格式
	 */
	PHONE("^\\+86\\d{3,4}\\d{7,8}([-]\\d{1,4})?$"),
	
	/**
	 * 邮编格式
	 */
	ZIPCODE("^\\d{5}[-]\\d{4}$|^\\d{6}$"),
	
	/**
	 * 纯数字格式
	 */
	PURENUMBER("^[0-9]*$"),

	/**
	 * JSON字符串
	 */
	JSONSTRING("^(\\{|\\[)[\\u0391-\\uFFE5a-zA-Z0-9'\"_#-@%,~.:;=!?|+<>$\\u4e00-\\u9fa5\\s\\{\\}\\[\\]/]+(\\}|\\])$"),

	/**
	 * 中文
	 */
	CHINESE("([\u4e00-\u9fa5]+)");


	private String regexp;
	
	Reg4Validator(String regexp){
		this.regexp = regexp;
	}
	
	public String getRegexp() {
		return regexp;
	}
}
