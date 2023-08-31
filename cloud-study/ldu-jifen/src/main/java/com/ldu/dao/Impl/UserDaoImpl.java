package com.ldu.dao.Impl;

import com.ldu.dao.UserDao;
import com.ldu.entity.User;
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
    public List<User> findAll() {
        String sql = "select * from user";
        RowMapper<User> rowMapper;
        rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int updateuser(String username, String password, String location,
                          String phone, Double account, String email, String address) {
        String sql = "update user set username=?,password=?,location=?,phone=?,account=?,email=?,address=? where id like'%"+1+"%'";
        return jdbcTemplate.update(sql,username,password,location,phone,account,email,address);
    }

    @Override
    public int buything(Double account) {
        String sql = "update user set account=? where id like'%"+1+"%'";
        return jdbcTemplate.update(sql,account);
    }

    @Override
    public int adduser(int id, String username, String password, String location,
                       String phone, Double account, String email, String address) {
        String sql = "insert into user(id,username,password,location,phone,account,email,address) value(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,id,username,password,location,phone,account,email,address);
    }

}
