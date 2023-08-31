package com.ldu;

import com.ldu.api.com.ldu.config.GatewayInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
public class Jifen9004 implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Jifen9004.class,args);
        System.out.println("积分模块----启动成功");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor());
    }
}
