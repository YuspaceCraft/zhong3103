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

    //lsl
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

    //zrz

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
        String sql = "update user set username = ? ,password = ? ,phone = ? ,age = ? ,address = ? where id = ?";
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
        String sql = "select * from user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,id);
        return user;
//        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public User login(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
//        String sql = "select * from equipment where (eId like '%" +name+"%') or(eNo like '%" +name+"%')" +
//                "or(eagreement like '%" +name+"%')or(eaddr like '%" +name+"%')or(eupload like '%" +name+"%')";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,username,password);
        return user;
    }





}
