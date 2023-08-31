package com.qf.controller;

import com.qf.config.HttpController;
import com.qf.dao.OrderDao;
import com.qf.entity.Order;
import com.qf.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends HttpController<OrderMapper, Order> {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDao orderDao;

    @RequestMapping("/allorders")
    public List<Order> allorders(){
        return orderMapper.selectList(null);
    }

    @GetMapping("/findorders")
    public List<Order> findorders(@RequestParam("label") String label){
        List<Order> orderlist = orderDao.findorderByConditions(label);
        System.out.println(orderlist);
        return orderlist;
    }
    @GetMapping("/delorders")
    public List<Order>  delorders(@RequestParam("id") String id ){
       orderDao.deleteorders(id);
        List<Order> orderlist = orderMapper.selectList(null);
        return orderlist;

    }
    @GetMapping("/updatesend")
    public List<Order> updatesend(@RequestParam("id") String id){
        orderDao.updatesend(id);
        List<Order> orderlist = orderMapper.selectList(null);
        return orderlist;
    }


}
