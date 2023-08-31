package com.qf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.qf.config.GatewayInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // 内核是SpringBoot
@EnableDiscoveryClient // 打开nacos注册开关  才可以把这个服务注册到nacos中
@EnableFeignClients("com.qf.api") // 扫描
public class CommodityApp9001 implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApp9001.class, args);
        System.out.println("商品模块----启动成功");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor());
    }

    @Override // 上传图片开关
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/commodity/**").addResourceLocations("file:D:/JavaWorkspace/commodity/");
    }

    @Bean // mybatis分页开关
    public MybatisPlusInterceptor page() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }



}
