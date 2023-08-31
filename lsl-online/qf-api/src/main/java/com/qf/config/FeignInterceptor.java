package com.qf.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

// 当我们使用Feign时会自动带上 gateway=123456
@Component // 打开开关
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("gateway", "123456");
    }
    // 1. 通过网关进入到对应的服务，网关会自动带 gateway=123456
    // 2. 从网关--->order--->Feign(自动带gateway=123456)--->Commodity

    // 1天下来，有60%时间在 沟通
    // 链路追踪
    // 网关--->order--->commodity--->jifen--->cart--->collect
    // 分布式事务
    // 服务 <--> 服务

    // 最重要：Nacos
    //   1.远程调用Feign
    //   2.网关

    // 基于事务
    // 事务：转钱   张三-100   李四+100
    // 两个功能同时执行，只要有1个失败了，另一个要回滚

}
