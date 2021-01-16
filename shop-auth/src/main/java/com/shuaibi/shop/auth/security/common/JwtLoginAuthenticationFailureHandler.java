package com.shuaibi.shop.auth.security.common;

import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.common.entity.result.CommonResult;
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
public class JwtLoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        CommonResult<String> result = CommonResult.failed(exception.getMessage());
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(result));
        out.flush();
        out.close();;
    }
}
