package com.aigt.code.validator;


import com.aigt.code.common.constraint.MobilePhone;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 	移动电话号码格式校验
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone,String> {
	
	@Override
	public void initialize(MobilePhone mobilePhone) {}

	@Override
	public boolean isValid(String mobilePhone, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(mobilePhone)){//允许为空
			return true;
		}
		//手机号码为11位的数字
		String regexp = Reg4Validator.MOBILEPHONE.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(mobilePhone);  
	    
		return matcher.find();
	}

}
