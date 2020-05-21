package com.yangyang.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.yangyang.gateway.constant.GateWayConstant;
import com.yangyang.gateway.pojo.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * @author gzy
 * @date 2020/5/8 17:22
 */
@Slf4j
@Component
public class TokenFilter implements GatewayFilter, Ordered {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String pathUrl = exchange.getRequest().getPath().toString();

        if (StringUtils.contains("/swagger-resources",pathUrl) || StringUtils.contains("/v2/api-docs",pathUrl)) {
            chain.filter(exchange);
        }

        if (StringUtils.equals(pathUrl, GateWayConstant.OATUH_URL)) {
            chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        String accessToken = exchange.getRequest().getHeaders().getFirst(GateWayConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(accessToken)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            ResultResponse resultResponse = new ResultResponse();
            resultResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            resultResponse.setSuccess(false);
            resultResponse.setMsg("Access Token 不能为空");
            return responseMono(response, JSON.toJSONString(resultResponse));
        }

        if (!StringUtils.startsWith(accessToken,GateWayConstant.BEARER_PREFIX)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            ResultResponse resultResponse = new ResultResponse();
            resultResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            resultResponse.setSuccess(false);
            resultResponse.setMsg("Access Token 格式不正确");
            return responseMono(response,JSON.toJSONString(resultResponse));
        }

        String formatKey = StringUtils.replace(accessToken, GateWayConstant.BEARER_PREFIX, GateWayConstant.ACCESS_PREFIX);
        if (!redisTemplate.hasKey(formatKey)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            ResultResponse resultResponse = new ResultResponse();
            resultResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            resultResponse.setSuccess(false);
            resultResponse.setMsg("Access Token 无效");
            return responseMono(response,JSON.toJSONString(resultResponse));
        }
        // 检查 access_token 过期时间
        final long expire = redisTemplate.getExpire(formatKey);
        if (0 >= expire) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            ResultResponse resultResponse = new ResultResponse();
            resultResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            resultResponse.setSuccess(false);
            resultResponse.setMsg("Access Token 已过期");
            return responseMono(response,JSON.toJSONString(resultResponse));
        }
        log.info("进入 ai-mall-gateway 服务，执行 TokenFilter 过滤器，检查 Access Token 完成");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> responseMono(ServerHttpResponse response, String jsonString) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] data = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(data);
        return response.writeWith(Mono.just(buffer));
    }
}
