package com.example.shop_online.controller;


import com.example.shop_online.dao.OrderDao;
import com.example.shop_online.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @GetMapping("/findall")
    public List<Order> getData(){
        List<Order> orders=orderDao.findAll();
//        System.out.println(equipments);
        return orders;
    }

    @GetMapping("/dimfind")
    public List<Order> dimorders(@RequestParam("orders") String name){
        List<Order> orders= orderDao.findByStr(name);
        System.out.println(orders);
        return orders;
    }

    @GetMapping("/delete")
    public Integer delete(@RequestParam("orderid") Integer id) throws IOException {
        System.out.println("delete方法执行");
        return orderDao.delete(id);
    }

    @RequestMapping(value = "/updatecomment", method = RequestMethod.GET)
    public int update(@RequestParam("orderid") Integer id,@RequestParam("comment") String comment) {
        System.out.println(id + "     " +comment);
        System.out.println(comment.getClass());
        Order order = orderDao.findById(id);

        System.out.println(order.getComment());
        order.setComment(comment);

        System.out.println(order.getComment());
        System.out.println("更改成功");
        return orderDao.update(order);
    }

}
