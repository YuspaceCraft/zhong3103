package com.ldu.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
//@Component
public class NeedRoutePredicateFactory extends
        AbstractRoutePredicateFactory<Kv> {

    //对所有服务加上断言,使用全局过滤器
    public NeedRoutePredicateFactory() {
        super(Kv.class);
    }

    @Override   //断言逻辑
    public Predicate<ServerWebExchange> apply(Kv config) {
        //年龄传入时必须大于18岁
        String key = config.getKey();
        String value = config.getValue();
        System.out.println(key);
        System.out.println(value);
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange web) {
                String v = web.getRequest().getQueryParams().getFirst(key);
                System.out.println(v);
                if(StringUtils.isBlank(v)){
                    return false; //不传年龄不行
                }
                try{
                if(Integer.valueOf(value) > Integer.valueOf(v)){
                    return false;
                }}catch (Exception e){
                    return false;
                }
                return true;//
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("key","value");
    }
}
