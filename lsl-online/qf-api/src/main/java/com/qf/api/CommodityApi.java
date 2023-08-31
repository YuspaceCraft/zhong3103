package com.qf.api;

import com.qf.entity.Commodity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("qf-commodity")
public interface CommodityApi {
    String controller = "/commodity";

    @RequestMapping(controller+"/detail/{id}")
    Commodity detail(@PathVariable int id);

}
