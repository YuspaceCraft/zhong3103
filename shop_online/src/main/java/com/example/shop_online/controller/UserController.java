package com.example.shop_online.controller;

import com.example.shop_online.dao.UserDao;
import com.example.shop_online.pojo.Order;
import com.example.shop_online.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    public UserDao userDao;

    private int uid;
    private String uname;

    @GetMapping("/finduser")
    public User finduser(@RequestParam("id") Integer id){
        User user = userDao.findById(id);
        System.out.println(user);
        return user;
    }

    @GetMapping("/updateuser")
    public int finduser(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("age") Integer age,
                         @RequestParam("phone") String phone,@RequestParam("address") String address){
        User user = userDao.findById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone(phone);
        user.setAddress(address);
        System.out.println(user);
        return userDao.update(user);
    }

    @RequestMapping(value = "/loginverify", method = RequestMethod.GET)
    public int login(@RequestParam("username") String username
            ,@RequestParam("password") String password){
        System.out.println(username +"  "+ password);
        try{
            User user = userDao.login(username,password);
            uid=user.getId();
            uname=username;
            System.out.println(user.getId());
            return user.getId();

        }catch (Exception e){
            return 0;
        }
    }

    @GetMapping("/returnid")
    public int returnid(){
        return uid;
    }

    @GetMapping("/returnname")
    public String returnname(){
        return uname;
    }

}
