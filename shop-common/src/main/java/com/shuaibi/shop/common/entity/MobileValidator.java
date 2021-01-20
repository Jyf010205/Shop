package com.shuaibi.shop.common.entity;

import com.shuaibi.shop.common.annotation.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 10:38
 * @description: 手机号校验逻辑
 */
public class MobileValidator implements ConstraintValidator<Mobile, Long> {
    @Override
    public void initialize(Mobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,1,3,6-8])|(18[0-9])|(19[8,9])|(166))[0-9]{8}$");
        Matcher m = p.matcher(String.valueOf(value));
        return m.matches();
    }
}
