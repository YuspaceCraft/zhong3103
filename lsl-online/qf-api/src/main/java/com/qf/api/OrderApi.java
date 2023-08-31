package com.qf.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("qf-order") // 中的OrderController
public interface OrderApi {
    String controller = "/order"; // 好用

    @RequestMapping(controller+"/create")
    String create(@RequestParam("msg") String msg);

}
