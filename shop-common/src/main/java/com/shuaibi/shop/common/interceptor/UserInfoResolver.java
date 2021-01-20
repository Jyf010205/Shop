package com.shuaibi.shop.common.interceptor;

import com.shuaibi.shop.common.annotation.UserId;
import com.shuaibi.shop.common.constant.JwtConstant;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import com.shuaibi.shop.common.utils.SpringContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jianyufeng
 * @date: 2021/1/20 21:48
 * @description:
 */
public class UserInfoResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        JwtTokenUtil jwtTokenUtil = SpringContextHolder.getBean(JwtTokenUtil.class);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader(JwtConstant.tokenHeader).replace(JwtConstant.tokenHead,"");
        return jwtTokenUtil.getUserIdFromToken(token);
    }
}
