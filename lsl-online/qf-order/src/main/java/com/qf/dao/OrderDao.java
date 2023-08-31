package com.qf.dao;

import com.qf.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findorderByConditions(String label);
    int deleteorders(String id);
    int updatesend(String id);
}
