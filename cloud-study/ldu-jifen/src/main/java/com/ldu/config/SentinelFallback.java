package com.ldu.config;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.stereotype.Component;

//哨兵 Sentinel 监控者,看一堆服务在那里玩,如果流量超了,控制一下他
//限流 Sentinel 监控流量,如果流量超了,指定限流规则
//负载均衡:对方是一个集群,轮询分摊压力,对方就不会单点垮掉
//远程调用: 服务之间的函数调用,叫做远程调用,使用组件Feign,Feign也是自带负载均衡
//网关  玄关,大门,统一入口,涉及一个安全问题,网关绕过
// 特点: 1.统一入口 2.检验身份,年龄>18 3.抗压能力强,配置路由,自带负载均衡
//降级  熔断时间内,又被访问了,就会弹出一个提示告知用户"系统繁忙"
//熔断  保护系统多少秒,这个时间内不允许再次访问
//断言  是一个判断是真是假的函数,如果为真,路由成功
//路由  选择这条路,找到对应的服务
//微服务 是一种加个Spring cloud Alibaba微服务服务架构
//集群   一堆一模一样的服务放在一起,只有IP或端口不同
//分布式   一堆服务,分别部署在不同服务器上,就构成了分布式系统
//部署 上线
//云原生 微服务项目部署上线所要用到的云计算技术,称为云原生
//优点: 可伸缩性强
//sentinel 哨兵, 监控者
public class SentinelFallback {
    public static String fallback(String flag, Throwable e){
        System.out.println("flag-----"+flag);
        if(e instanceof DegradeException){
            return "熔断错误-----系统繁忙,请稍后再试";
        }
        else{
            return "其他代码错误----系统没有做好";
        }
    }
}
