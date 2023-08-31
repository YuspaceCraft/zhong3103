package com.qf;

import com.qf.config.GatewayInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient // 将服务注册到Nacos中，开关
@EnableFeignClients("com.qf.api") // 扫描
public class JifenApp9004 implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(JifenApp9004.class, args);
        System.out.println("用户模块----启动成功");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor());
    }


}
