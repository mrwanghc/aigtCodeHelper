package com.aigt.code.common.constraint;

import com.aigt.code.validator.NotBlankAndLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 * @description 字符串非空和长度校验注解
 * @author ShaoYu Email:shaoyu@co-mall.com
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @version
 * @since 2016年3月21日 下午4:22:30
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy= NotBlankAndLengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotBlankAndLength {
	
	int min() default 0;

	int max() default Integer.MAX_VALUE;

	String message() default "{com.comall.cybershop.common.constraint.NotBlankAndLength.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotBlankAndLength[] value();
	}
}
