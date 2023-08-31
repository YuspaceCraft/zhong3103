package com.ldu.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ldu-order")
public interface OrderApi {
    @RequestMapping("/order/sendmsg")
    String sendmsg();
}
