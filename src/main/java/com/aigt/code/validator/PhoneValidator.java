package com.aigt.code.validator;

import com.aigt.code.common.constraint.Phone;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 电话号码或传真号码格式校验
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {

	@Override
	public void initialize(Phone phone) {}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(phone)){//允许为空
			return true;
		}
		//校验固话或传真是（+86[3,4]位数字[7,8]位数字"-"[1,4]位分机号，或者是没有分机号）的格式
		String regexp = Reg4Validator.PHONE.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(phone);  
	    
		return matcher.find();
	}

}
