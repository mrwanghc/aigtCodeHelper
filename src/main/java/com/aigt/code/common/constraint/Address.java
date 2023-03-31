package com.aigt.code.common.constraint;

import com.aigt.code.validator.AddressValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * 
 * @description 详细地址校验注解
 * @author ShaoYu Email:shaoyu@co-mall.com
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @version
 * @since 2016年3月21日 下午4:19:48
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy= AddressValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Address {
	String message() default "{com.comall.cybershop.common.constraint.Address.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
