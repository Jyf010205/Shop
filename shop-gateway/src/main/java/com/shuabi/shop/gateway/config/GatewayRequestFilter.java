package com.shuabi.shop.gateway.config;

import cn.hutool.json.JSONUtil;
import com.shuabi.shop.gateway.properties.ShopGatewayProperties;
import com.shuaibi.shop.common.constant.JwtConstant;
import com.shuaibi.shop.common.entity.result.CommonResult;
import com.shuaibi.shop.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: jianyufeng
 * @date: 2021/2/2 11:16
 * @description: 网关过滤器
 */
@Slf4j
@Component
public class GatewayRequestFilter implements GlobalFilter {
    @Autowired
    private ShopGatewayProperties properties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //校验url是否在白名单中
        String url = request.getPath().toString();
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        if (anonUrls != null && ArrayUtils.isNotEmpty(anonUrls)) {
            for (String u : anonUrls) {
                //url在白名单中，无需校验token
                if (pathMatcher.match(u, url)) {
                    return chain.filter(exchange);
                }
            }
        }

        String authHeader = request.getHeaders().getFirst(JwtConstant.tokenHeader);
        if (authHeader != null && authHeader.startsWith(JwtConstant.tokenHead)){
            String authToken = authHeader.substring(JwtConstant.tokenHead.length());
            String userId = jwtTokenUtil.getUserIdFromToken(authToken);
            //解析token成功
            if (userId != null && !jwtTokenUtil.isTokenExpired(authToken)){
                return chain.filter(exchange);
            }
        }
        //返回错误结果
        return makeResponse(response);
    }

    private Mono<Void> makeResponse(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(CommonResult.unauthorized(null)).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
