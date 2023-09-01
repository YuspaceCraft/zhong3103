package com.ldu.dao;

import com.ldu.entity.Order1;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    //zrz
    //添加
    public int save(Order1 order);
    //更新
    public int update(Order1 order);
    //删除
    public int delete(Integer id);
    //通过id查询
    public Order1 findById(Integer id);

    public List<Order1> findByStr(String name);
    List<Order1> findAll();

    int addorder1(int orderid, String date, String name, String address, int num,Double price,String comment);

}