package com.qf.config;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.springframework.stereotype.Component;

// sentinel 哨兵，监控者
//@Component  开关不在这里
public class SentinelFallback {
    // 这个方法必须是静态的
    // Throwable就是错误，就是咱们平常java中抛出来的那个异常
    public static String fallback(String flag, Throwable e) {
        System.out.println("flag----" + flag);
        if (e instanceof DegradeException) {
            // 就可以给这个降级错误一个自己的提示语
            return "熔断错误----系统繁忙请稍后----";
        } else {
            // 上线之前不要有BUG
            return "其他代码错误----系统没有做好---";
        }
    }



}
