package com.aigt.code.validator;

import com.aigt.code.common.constraint.Address;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 详细地址校验器
 */
public class AddressValidator implements ConstraintValidator<Address, String> {

	
	@Override
	public void initialize(Address address) {}
	
	@Override
	public boolean isValid(String address, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(address)){//允许为空
			return true;
		}
		int length = address.length();
		if(length > 255){//字符串长度不能超过255
			return false;
		}
		//校验详细地址格式是以$$间隔，不能以$$结尾
		String regexp = Reg4Validator.ADDRESS.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(address);  
	    
		return matcher.find();
	}

}
