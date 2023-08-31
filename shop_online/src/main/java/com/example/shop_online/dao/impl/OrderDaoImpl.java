package com.example.shop_online.dao.impl;

import com.example.shop_online.dao.OrderDao;
import com.example.shop_online.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findByStr(String name) {
        String sql = "select * from order1 where (orderid like '%" +name+"%') or(date like '%" +name+"%') or(name like '%" +name+"%')" +
                "or(address like '%" +name+"%')or(num like '%" +name+"%')or(price like '%" +name+"%')";

        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<Order>(Order.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    //添加
    @Override
    public int save(Order order) {
        String sql = "insert into equipment(eId,eName,eNo,eagreement,epower,etime,eaddr,eupload) value(?,?,?,?,?,?,?,?)";
        Object[] obj = new Object[] {

        };
        return jdbcTemplate.update(sql,obj);
    }

    //更新
    @Override
    public int update(Order order) {
        String sql = "update order1 set comment = ? where orderid = ?";
        Object[] params = new Object[]{
                order.getComment(),
                order.getOrderid(),
        };
        int num = jdbcTemplate.update(sql, params);
        return num;
    }

    //删除
    @Override
    public int delete(Integer id) {
        String sql = "delete from order1 where orderid = ?";
        int num = jdbcTemplate.update(sql, id);
        return num;
    }

    @Override
    public Order findById(Integer id) {
        String sql = "select * from order1 where orderid = ?";
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<Order>(Order.class);
        Order order = jdbcTemplate.queryForObject(sql,rowMapper,id);
        return order;
//        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    @Override
    public List<Order> findAll() {
        String sql = "select * from order1";
        RowMapper<Order> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Order>(Order.class);
        // 执行静态的SQL查询，并通过RowMapper返回结果
        return this.jdbcTemplate.query(sql, rowMapper);
    }


}
