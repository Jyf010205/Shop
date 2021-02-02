package com.shuaibi.shop.system.security.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.common.entity.enums.ChannelType;
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
 * @date: 2021/1/16 13:46
 * @description: 普通登录过滤器 仿照 UsernamePasswordAuthenticationFilter改写
 */
public class CommonLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CommonLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher("/admin/login", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        //从请求头的channel中获取登录渠道
        String channelStr = request.getHeader("Channel");
        String requestBodyStr = HttpRequestUtil.readAsChars(request);
        JSONObject requestBody = JSONUtil.parseObj(requestBodyStr);
        //登录渠道
        ChannelType channel = channelStr != null ? Enum.valueOf(ChannelType.class, channelStr) : ChannelType.No_Channel;
        String username = requestBody.getStr("username");
        String password = requestBody.getStr("password");

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        CommonLoginAuthenticationToken authRequest = new CommonLoginAuthenticationToken(
                username, password);

        //把Ip,登录渠道 放入 authRequest
        setDetails(request, authRequest,channel);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request,
                              CommonLoginAuthenticationToken authRequest,
                              ChannelType channel) {
        JSONObject detail = JSONUtil.parseObj(authenticationDetailsSource.buildDetails(request));
        detail.set("channel",channel);
        authRequest.setDetails(detail);
    }

}
