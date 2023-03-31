package com.aigt.code.validator;

import com.aigt.code.common.constraint.NotBlankAndLength;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @description 字符串非空与长度校验
 */
public class NotBlankAndLengthValidator implements ConstraintValidator<NotBlankAndLength,String> {

	private int min;
	private int max;

	public void initialize(NotBlankAndLength parameters) {
		min = parameters.min();
		max = parameters.max();
		validateParameters();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.trim() == ""){
			return false;
		}
		int length = value.length();
		boolean lengthOk = length >= min && length <= max;
		boolean blankOk = value.trim().length() > 0;

		return lengthOk && blankOk;
	}

	private void validateParameters() {
		if ( min < 0 ) {
			System.out.println("The min parameter cannot be negative.");
		}
		if ( max < 0 ) {
			System.out.println("The max parameter cannot be negative.");
		}
		if ( max < min ) {
			System.out.println("The length cannot be negative.");
		}
	}
}
