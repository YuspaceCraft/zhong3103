package com.example.shop_online.dao;

import com.example.shop_online.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    //添加
    public int save(User user);
    //更新
    public int update(User user);
    //删除
    public int delete(Integer id);
    //通过id查询
    public User findById(Integer id);

    //查询所有设备信息
    public List<User> findAll();

    public List<User> findByStr(String name);

    public User login(String username,String password);
}
