package com.shuaibi.shop.common.annotation;

import com.shuaibi.shop.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 10:36
 * @description: 自定义手机号参数校验注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {
    String message() default "手机格式不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
