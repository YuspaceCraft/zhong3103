package com.ldu.controller;

import com.ldu.api.OrderApi;
import com.ldu.dao.CommodityDao;
import com.ldu.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommmdityController {
    @Value("${server.port}")
    String serverPort;

    @Autowired
    OrderApi orderApi;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public CommodityDao commodityDao;

    @RequestMapping("/test")
    public List<Commodity> test(){
        return commodityDao.findAll();
    }

    @RequestMapping(value = "/fuzzy", method = RequestMethod.GET)
    public List<Commodity> fuzzy(@RequestParam("fuzzystr") String fuzzystr){
        List<Commodity> all = commodityDao.Fuzzyfind(fuzzystr);
        return all;
    }

    @RequestMapping("/detail/{id}")
    public Commodity detail(@PathVariable int id){
        return new Commodity(id,2,"牛仔裤","23sd",19.99,"便宜卖");
    }
    @RequestMapping("/get")
    public String get(){
        String data =  restTemplate.getForObject("http://ldu-order/order/sendmsg",String.class);
        System.out.println(data);
        return data;
    }

    @RequestMapping("/getmsgByOrderApi")
    public String getmsgByOrderApi(){
        return orderApi.sendmsg();
    }
}
