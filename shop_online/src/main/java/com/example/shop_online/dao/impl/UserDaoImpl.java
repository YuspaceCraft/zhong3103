package com.example.shop_online.dao.impl;

import com.example.shop_online.dao.UserDao;
import com.example.shop_online.pojo.User;
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
    public List<User> findByStr(String name) {
        String sql = "select * from equipment where (eId like '%" +name+"%') or(eName like '%" +name+"%') or(eNo like '%" +name+"%')" +
                "or(eagreement like '%" +name+"%')or(epower like '%" +name+"%')or(etime like '%" +name+"%')or(eaddr like '%" +name+"%')or(eupload like '%" +name+"%')";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    //添加
    @Override
    public int save(User user) {
        String sql = "insert into equipment(eId,eName,eNo,eagreement,epower,etime,eaddr,eupload) value(?,?,?,?,?,?,?,?)";
        Object[] obj = new Object[] {

        };
        return jdbcTemplate.update(sql,obj);
    }

    //更新
    @Override
    public int update(User user) {
        String sql = "update user1 set username = ? ,password = ? ,phone = ? ,age = ? ,address = ? where id = ?";
        Object[] params = new Object[]{
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                user.getAge(),
                user.getAddress(),
                user.getId()
        };
        int num = jdbcTemplate.update(sql, params);
        return num;
    }

    //删除
    @Override
    public int delete(Integer id) {
        String sql = "delete from equipment where eId = ?";
        int num = jdbcTemplate.update(sql, id);
        return num;
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from user1 where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,id);
        return user;
//        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    @Override
    public List<User> findAll() {
        String sql = "select * from equipment";
        RowMapper<User> rowMapper;
        rowMapper = new BeanPropertyRowMapper<User>(User.class);
        // 执行静态的SQL查询，并通过RowMapper返回结果
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User login(String username, String password) {
        String sql = "select * from user1 where username = ? and password = ?";
//        String sql = "select * from equipment where (eId like '%" +name+"%') or(eNo like '%" +name+"%')" +
//                "or(eagreement like '%" +name+"%')or(eaddr like '%" +name+"%')or(eupload like '%" +name+"%')";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,username,password);
        return user;
    }

}
