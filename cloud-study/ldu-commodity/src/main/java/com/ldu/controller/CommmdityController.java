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

    @RequestMapping("/allcommodity")
    public List<Commodity> allcommodity(){
        return commodityDao.findAll();
    }

    @RequestMapping("/findBycommId")
    public List<Commodity> findBycommId(@RequestParam("id") int id){
        List<Commodity> commoditylist = commodityDao.findBycommId(id);
        return commoditylist;
    }

    @GetMapping("/findCommodity")
    public List<Commodity> findCommodity(@RequestParam("label") String label){
        List<Commodity> commoditylist = commodityDao.findByConditions(label);
        return commoditylist;
    }
    @GetMapping("/updateCommodity")
    public List<Commodity> updateCommodity(@RequestParam("label") String label,
                                           @RequestParam("img") String img,
                                           @RequestParam("price") String price,
                                           @RequestParam("description") String description,
                                           @RequestParam("id") String id){
        int flag = commodityDao.updatecommodity(label,img,price,description,id);
        List<Commodity> commodityList = commodityDao.findAll();
        System.out.println(commodityList);
        System.out.println(flag);
        return commodityList;

    }
    @GetMapping("/addCommodity")
    public List<Commodity>  addCommodity(
            @RequestParam("label") String label,
            @RequestParam("img") String img,
            @RequestParam("price") String price,
            @RequestParam("description") String description){
        commodityDao.insert(label,img,price,description);
        List<Commodity> commodityList = commodityDao.findAll();
        return commodityList;
    }
    @GetMapping("/delCommodity")
    public List<Commodity> delCommodity(@RequestParam("id") String id){
        commodityDao.deleteCommodity(id);
        List<Commodity> commodityList = commodityDao.findAll();
        return commodityList;


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
