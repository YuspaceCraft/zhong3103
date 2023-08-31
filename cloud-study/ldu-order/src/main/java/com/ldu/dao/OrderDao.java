package com.ldu.dao;


import com.ldu.entity.Orders;

import java.util.List;

public interface OrderDao {
    List<Orders> findAll();

    List<Orders> getid(int id);

    int updateorders(int id, int num);

    int addtocart(int id, String img, String label, Double price, int num);

    int deleteorder(int id);
}
