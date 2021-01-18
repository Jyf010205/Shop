package com.shuaibi.shop.auth.security;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shuaibi.shop.auth.service.SystemUserService;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: jianyufeng
 * @date: 2021/1/16 14:53
 * @description: 登录成功处理器
 */
@Component
public class AuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    SystemUserService systemUserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);
        //生成Jwt返回值
        CommonResult<JSONObject> result = CommonResult.success(new JSONObject().set("token", token).set("tokenHead", tokenHead));
        response.setContentType(ContentType.JSON.name());
        response.setCharacterEncoding(CharsetUtil.UTF_8.name());
        //记录登录时间
        systemUserService.updateLoginTime(userDetails.getUsername());
        PrintWriter out = response.getWriter();
        out.write(JSONUtil.toJsonStr(result));
        out.flush();
        out.close();;
    }
}
