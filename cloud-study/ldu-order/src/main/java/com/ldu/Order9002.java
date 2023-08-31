package com.ldu;

import com.ldu.api.com.ldu.config.GatewayInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ldu.api")
public class Order9002 implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Order9002.class, args);
        System.out.println("订单模块----启动成功!");
    }
    @Bean
    @LoadBalanced
   public RestTemplate getRestTemplate(){
        return  new RestTemplate();
   }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor());
    }
}
