package com.aigt.code.validator;

import com.aigt.code.common.constraint.IDCard;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 	身份证格式校验
 */
public class IDCardValidator implements ConstraintValidator<IDCard, String> {

	@Override
	public void initialize(IDCard idCard) {}

	@Override
	public boolean isValid(String idCard, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(idCard)){//允许为空
			return true;
		}
		//校验身份证是否为18位数字或者为15位数字，18位最后一位可以是“X”,不区分大小写
		String regexp = Reg4Validator.IDCARD.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(idCard);  
	    
		return matcher.find();
	}

}
