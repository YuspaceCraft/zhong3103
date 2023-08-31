package com.qf.dao;

import com.qf.entity.User;

import java.util.List;

public interface UserDao {
    List<User> finduserByConditions(String username, String phone, String email,
                                    String location, String address);
    int insert(String username,String password,String phone,String account,
               String email, String location,String address);
    int deleteuser(String id);
}
