package com.aigt.code.validator;

import com.aigt.code.common.constraint.ZipCode;
import com.aigt.code.utils.StringUtil;
import com.aigt.code.validator.reg.Reg4Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 邮编格式校验(中式:6位数字，美式:xxxxx-xxxx)
 */
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

	@Override
	public void initialize(ZipCode zipCode) {

	}

	@Override
	public boolean isValid(String zipCode, ConstraintValidatorContext arg1) {

		if(StringUtil.isBlank(zipCode)){// 允许为空
			return true;
		}
		// 校验中国邮编：6位固定数，或美国邮编：xxxxx-xxxx
		String regexp = Reg4Validator.ZIPCODE.getRegexp();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(zipCode);

		return matcher.find();
	}

}
