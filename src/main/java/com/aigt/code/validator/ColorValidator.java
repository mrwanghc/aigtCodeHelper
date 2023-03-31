package com.aigt.code.validator;

import com.aigt.code.common.constraint.Color;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 颜色格式校验：(FFFFFF)
 */
public class ColorValidator implements ConstraintValidator<Color, String> {
	
	public void initialize(Color color) {}

	public boolean isValid(String color, ConstraintValidatorContext arg1) {
		if(StringUtil.isBlank(color)){//允许为空
			return true;
		}
		//校验颜色以16进制6位数表示，不区分大小写,如：ffffff
		String regexp = Reg4Validator.COLOR.getRegexp();
		Pattern pattern = Pattern.compile(regexp);  
	    Matcher matcher = pattern.matcher(color);  
	    
		return matcher.find();
	}

}
