package com.ldu.dao;

import com.ldu.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    int updateuser(String username, String password, String location,
                   String phone, Double account, String email, String address);

    int buything(Double account);

    int adduser(int id, String username, String password, String location,
                String phone, Double account, String email, String address);

    //lsl
    List<User> finduserByConditions(String username, String phone, String email,
                                    String location, String address);
    int insert(String username,String password,String phone,String account,
               String email, String location,String address);
    int deleteuser(String id);


    //zrz
    //添加
    public int save(User user);
    //更新
    public int update(User user);
    //删除
    public int delete(Integer id);
    //通过id查询
    public User findById(Integer id);

    public List<User> findByStr(String name);

    public User login(String username,String password);
}
