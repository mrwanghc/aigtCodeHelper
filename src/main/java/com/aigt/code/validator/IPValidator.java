package com.aigt.code.validator;

import com.aigt.code.common.constraint.IP;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description IP地址格式校验
 */
public class IPValidator implements ConstraintValidator<IP, String> {

	@Override
	public void initialize(IP ip) {}

	@Override
	public boolean isValid(String ip, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(ip)){//允许为空
			return true;
		}
		//校验IP格式，每位数为0~255,如：255.255.255.255
		String regexp = Reg4Validator.IP.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(ip);  
	    
		return matcher.find();
	}

}
