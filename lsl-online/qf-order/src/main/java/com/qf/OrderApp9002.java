package com.qf;

import com.qf.config.GatewayInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient // nacos服务发现开关
@EnableFeignClients("com.qf.api") // 扫描开关
// nacos配置了mysql数据库开关
public class OrderApp9002 implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp9002.class, args);
        System.out.println("订单模块----启动成功");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor());
    }
}
