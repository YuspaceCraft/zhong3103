package com.ldu.api;

import com.ldu.entity.Jifen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ldu-jifen")
public interface JifenApi {
    @RequestMapping("/jifen/add/{score}")
    Jifen add(@PathVariable int score);
    @RequestMapping("/jifen/search")
    Jifen search(@RequestParam("uid") int uid,
                 @RequestParam("score") int score);
}
