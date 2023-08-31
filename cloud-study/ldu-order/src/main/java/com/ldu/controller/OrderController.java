package com.ldu.controller;

import com.ldu.api.CommodityApi;
import com.ldu.api.JifenApi;
import com.ldu.dao.OrderDao;
import com.ldu.entity.Commodity;
import com.ldu.entity.Jifen;
import com.ldu.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JifenApi jifenApi;
    @Autowired
    CommodityApi commodityApi;
    @Autowired
    OrderDao orderDao;


    @RequestMapping("/getorders")
    public List<Orders> getorders(){
        return orderDao.findAll();
    }

    @RequestMapping(value = "/addtocart", method = RequestMethod.GET)
    public String addtocart(@RequestParam("id") int id,@RequestParam("img") String img,
                            @RequestParam("label") String label, @RequestParam("price") Double price,
                            @RequestParam("num") int num){
        List<Orders> temp1 = orderDao.getid(id);
        if (temp1.isEmpty()){
            orderDao.addtocart(id,img,label,price,num);
        }
        else{
            orderDao.updateorders(id, temp1.get(0).amount+num);
        }
        return "Success";
    }

    @RequestMapping(value = "/updatecart", method = RequestMethod.GET)
    public String upatecart(@RequestParam("id") int id, @RequestParam("num") int num){
        orderDao.updateorders(id, num);
        return "Success!";
    }

    @RequestMapping(value = "/deletecart", method = RequestMethod.GET)
    public String deletecart(@RequestParam("id") int id){
        orderDao.deleteorder(id);
        return "Success!";
    }

    @RequestMapping("/sendmsg")
    public int sendmsg(){
        return orderDao.deleteorder(12);
    }

    //
    @RequestMapping("/getJifenByApi")
    public Jifen getJifenByApi(){
        return jifenApi.search(12,100);
    }

    @RequestMapping("/getcommodityByApi")
    public Commodity getCommodity(){
        return commodityApi.detail(1998);
    }
    /*
    * jifenapi
    * 1.Autowired
    * 2.@EnableFeignClients("com.ldu.api") 启动类
    * 3. <dependency></dependency>
    * */
}
