package com.ldu.dao.Impl;

import com.ldu.dao.OrderDao;
import com.ldu.entity.Orders;
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
    public List<Orders> findAll() {
        String sql = "select * from orders";
        RowMapper<Orders> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Orders>(Orders.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Orders> getid(int id) {
        String sql = "select * from orders where id="+id;
        RowMapper<Orders> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Orders>(Orders.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int updateorders(int id, int num) {
        String sql = "update orders set amount=? where id like'%"+id+"%'";
        return jdbcTemplate.update(sql,num);
    }

    @Override
    public int addtocart(int id,String img,String label,Double price, int num) {
        String sql = "insert into orders(id,img,label,price,amount) value(?,?,?,?,?)";
        return jdbcTemplate.update(sql,id,img,label,price,num);
    }

    @Override
    public int deleteorder(int id) {
        String sql = "delete from orders where id=?";
        return jdbcTemplate.update(sql,id);
    }

}
