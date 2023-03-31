package com.aigt.code.common.constraint;

import com.aigt.code.validator.MobilePhoneValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * 
 * @description 手机号码格式校验注解
 * @author ShaoYu Email:shaoyu@co-mall.com
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @version
 * @since 2016年3月21日 下午4:22:07
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy= MobilePhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MobilePhone {

	String message() default "请填写正确手机号码!";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
