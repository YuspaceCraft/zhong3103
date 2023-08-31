package com.ldu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldu.config.Kv;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.K;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway9005 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9005.class,args);
        System.out.println("网关模块----启动成功");
    }

    //所有的请求只要经过网关就会被这个过滤器拦截,然后这些请求就会带上请求头 gateway=123456
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



//    @Bean
//    public GlobalFilter getGlobalFilter(){
//        return  new GlobalFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange web, GatewayFilterChain chain) {
//                String v = web.getRequest().getQueryParams().getFirst("age");
//                if(StringUtils.isBlank(v)){
//                    //没有传入年龄
//                    return response(web.getResponse(),"请传入年龄");
//                }
//                try {
//                    if (Integer.parseInt(v) < 18) {
//                        //未成年
//                        Map<String,String> map = new HashMap<String,String>(){{
//                            put("code","404");
//                            put("msg","未满18禁止进入");
//                        }};
//                        return response(web.getResponse(),map);
//                    }
//                }catch (Exception e){
//                        //年龄非法,不是数字
//                    Kv kv = new Kv("年龄输入不正确","请输入正确的年龄");
//                    return response(web.getResponse(),kv);
//                }
//                return chain.filter(web);//放行
//            }
//        };
//    }

    // 直接复制粘贴
    private Mono<Void> response(ServerHttpResponse response, Object obj){
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
//404
//不放行,可以给网页一个友好提示,称为服务降级
