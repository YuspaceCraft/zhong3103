package com.qf.dao.impl;

import com.qf.dao.OrderDao;
import com.qf.entity.Commodity;
import com.qf.entity.Order;
import org.aspectj.weaver.ast.Or;
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
    public List<Order> findorderByConditions(String label) {
        String sql = "SELECT * FROM orders WHERE " +
                "label LIKE CONCAT('%', ?, '%') " ;
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        return this.jdbcTemplate.query(sql, rowMapper,label);
    }
    @Override
    public int deleteorders(String id) {
        return jdbcTemplate.update("delete from orders where id = ?",id);

    }
    @Override
    public int updatesend(String id){
        return jdbcTemplate.update("update orders set issend = '是' where id=?",id);
    }


}
