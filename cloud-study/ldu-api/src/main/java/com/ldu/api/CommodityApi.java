package com.ldu.api;

import com.ldu.entity.Commodity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ldu-commodity")
public interface CommodityApi {
    @RequestMapping("/commodity/detail/{id}")
    public Commodity detail(@PathVariable int id);
    @RequestMapping("/commodity/get")
    public String get();
}
