package com.aigt.code.validator;

import com.aigt.code.common.constraint.JsonString;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description json字符串校验器
 */
public class JsonStringValidator implements ConstraintValidator<JsonString, String> {

	
	@Override
	public void initialize(JsonString jsonStr) {}
	
	@Override
	public boolean isValid(String jsonStr, ConstraintValidatorContext arg1) {
		if(jsonStr == null){//允许为空
			return true;
		}
		String regexp = Reg4Validator.JSONSTRING.getRegexp();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(jsonStr);
		return matcher.find();
		
	}

	
}
