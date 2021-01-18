package com.shuaibi.shop.auth.security.sms;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.common.utils.HttpRequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jianyufeng
 * @date: 2021/1/18 14:47
 * @description: 短信登录过滤器
 */
public class SmsLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected SmsLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher("/admin/sms/login", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String requestBodyStr = HttpRequestUtil.readAsChars(request);
        JSONObject requestBody = JSONUtil.parseObj(requestBodyStr);
        Long mobile = requestBody.getLong("mobile");
        String code = requestBody.getStr("code");

        SmsLoginAuthenticationToken authRequest = new SmsLoginAuthenticationToken(
                mobile, code);

        //把Ip Session放入 authRequest
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request,
                              SmsLoginAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
