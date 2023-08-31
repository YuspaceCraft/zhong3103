package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.entity.Order;
import com.qf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<User> finduserByConditions(String username,String phone,String email,
                                           String location,String address) {
        String sql = "SELECT * FROM user WHERE " +
                "username LIKE CONCAT('%', ?, '%') " +
                "AND phone LIKE CONCAT('%', ?, '%') " +
                "AND email LIKE CONCAT('%', ?, '%') " +
                "AND location LIKE CONCAT('%', ?, '%') " +
                "AND address LIKE CONCAT('%', ?, '%') " ;
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return this.jdbcTemplate.query(sql, rowMapper,username,phone,email,location,address);
    }

    @Override
    public int insert(String username,String password,String phone,String account,
                      String email, String location,String address) {
        String sql = "insert into user(username,password,phone," +
                "account,email,location,address) value(?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,username,password,phone,
                account,email,location,address);
    }

    @Override
    public int deleteuser(String id) {
        return jdbcTemplate.update("delete from user where id = ?",id);
    }

}
