package com.aigt.code.common.constraint;

import com.aigt.code.validator.IDCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 
 * @description 身份证格式校验注解
 * @author ShaoYu Email:shaoyu@co-mall.com
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @version
 * @since 2016年3月21日 下午4:20:46
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy= IDCardValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IDCard {
	String message() default "{com.comall.cybershop.common.constraint.IDCard.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}