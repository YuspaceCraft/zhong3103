package com.qf.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// 这个匹配规则都是阿里和Spring定义的
// Need=age,18
// @Component // 要加这个注解，这个注解就是一个开关，和断言连在一起
public class NeedRoutePredicateFactory extends
        AbstractRoutePredicateFactory<Kv> {
    // 所有的服务都需要加断言，一个一个加(就是麻烦了一点)
    // 第二个办法就是 加 全局过滤器
    // age,18
    public NeedRoutePredicateFactory() {
        super(Kv.class); // key,value
    }
    // 访问积分模块时，校验年龄 >=18岁 才可以通过
    // 访问其他模块，没有年龄校验
    @Override // 断言逻辑
    public Predicate<ServerWebExchange> apply(Kv config) {
        // 年龄age传入时必须大于18岁
        String key = config.getKey(); // getKey() getValue()断言中写死的
        String value = config.getValue(); // value=20
        System.out.println(key); // age 断言中配置的age
        System.out.println(value);// 18 断言中配置的18
        return new Predicate<ServerWebExchange>() {
            @Override // Query=age,18不灵活
            public boolean test(ServerWebExchange web) { // 你的断言配置
                String v = web.getRequest().getQueryParams().getFirst(key);
                System.out.println(v); // 是你写地址时传入进来的
                // v啥也没有，只能说明你地址中没写age
                if (StringUtils.isBlank(v)) { // 如果v空的
                    return false; // 不传年龄不行
                }
                //     localhost:9005/jifen/add/100?age=张三
                try {
                if (Integer.valueOf(value) > Integer.valueOf(v)) {
                    return false; // 未成年，不通过
                }} catch (Exception e) {
                    return false; // 如果年龄不是数字，非法传入那也不行
                }
                return true; // true=放行  false=不让你继续访问
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("key","value");
    }
}
