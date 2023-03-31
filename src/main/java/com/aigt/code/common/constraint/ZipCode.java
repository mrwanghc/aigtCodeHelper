package com.aigt.code.common.constraint;

import com.aigt.code.validator.ZipCodeValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy= ZipCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZipCode {
	String message() default "{com.comall.cybershop.common.constraint.ZipCode.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
