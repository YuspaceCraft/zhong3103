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
}
