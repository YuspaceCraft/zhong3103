package com.qf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.config.Kv;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApp9005 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp9005.class, args);
        System.out.println("网关模块----启动成功");
    }

    // 所有的请求，只要经过网关，就会被这个过滤器拦截，所有的请求就会带上请求头 gateway=123456
    @Bean
    public GlobalFilter getGlobalFilter() {
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange web, GatewayFilterChain chain) {
                ServerHttpRequest.Builder mutate = web.getRequest().mutate();
                mutate.header("gateway", "123456");
                return chain.filter(web.mutate().request(mutate.build()).build());
            }
        };
    }

    // 直接复制粘贴
    private Mono<Void> response(ServerHttpResponse response, Object obj) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        try {
            String msg = new ObjectMapper().writeValueAsString(obj);
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Flux.just(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
