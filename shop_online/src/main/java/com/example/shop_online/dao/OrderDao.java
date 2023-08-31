package com.example.shop_online.dao;

import com.example.shop_online.pojo.Order;
import com.example.shop_online.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    //添加
    public int save(Order order);
    //更新
    public int update(Order order);
    //删除
    public int delete(Integer id);
    //通过id查询
    public Order findById(Integer id);

    //查询所有设备信息
    public List<Order> findAll();

    public List<Order> findByStr(String name);

}
