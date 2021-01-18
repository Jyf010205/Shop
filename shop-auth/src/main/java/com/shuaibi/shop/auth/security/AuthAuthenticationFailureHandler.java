package com.shuaibi.shop.auth.security;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.exception.SmsLoginExpection;
import io.netty.util.CharsetUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: jianyufeng
 * @date: 2021/1/16 14:54
 * @description: 登录失败处理器
 */
@Component
public class AuthAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        CommonResult<String> result = null;
        if(exception instanceof BadCredentialsException){
            result = CommonResult.failed("用户名或密码错误");
        }else if (exception instanceof SmsLoginExpection){
            result = CommonResult.failed(exception.getMessage());
        }
        response.setContentType(ContentType.JSON.name());
        response.setCharacterEncoding(CharsetUtil.UTF_8.name());
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(result));
        out.flush();
        out.close();
    }
}
