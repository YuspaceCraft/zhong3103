package com.ldu.dao.Impl;

import com.ldu.dao.OrderDao;
import com.ldu.entity.Order1;
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
    public List<Order1> findByStr(String name) {
        String sql = "select * from order1 where (orderid like '%" +name+"%') or(date like '%" +name+"%') or(name like '%" +name+"%')" +
                "or(address like '%" +name+"%')or(num like '%" +name+"%')or(price like '%" +name+"%')";

        RowMapper<Order1> rowMapper = new BeanPropertyRowMapper<Order1>(Order1.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    //添加
    @Override
    public int save(Order1 order) {
        String sql = "insert into equipment(eId,eName,eNo,eagreement,epower,etime,eaddr,eupload) value(?,?,?,?,?,?,?,?)";
        Object[] obj = new Object[] {

        };
        return jdbcTemplate.update(sql,obj);
    }

    //更新
    @Override
    public int update(Order1 order) {
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
    public Order1 findById(Integer id) {
        String sql = "select * from order1 where orderid = ?";
        RowMapper<Order1> rowMapper = new BeanPropertyRowMapper<Order1>(Order1.class);
        Order1 order = jdbcTemplate.queryForObject(sql,rowMapper,id);
        return order;
//        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    @Override
    public List<Order1> findAll() {
        String sql = "select * from order1";
        RowMapper<Order1> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Order1>(Order1.class);
        // 执行静态的SQL查询，并通过RowMapper返回结果
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int addorder1(int orderid, String date, String name, String address, int num,Double price,String comment) {
        String sql = "insert into order1(orderid,date,name,address,num,price,comment) value(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,orderid,date,name,address,num,price,comment);
    }


}