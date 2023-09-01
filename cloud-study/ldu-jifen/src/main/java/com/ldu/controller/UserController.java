package com.ldu.controller;


import com.ldu.dao.UserDao;
import com.ldu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    public UserDao userDao;

    private int uid=1;
    private String uname="cosm";



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
        return userDao.updateuser(username,password,user.location,phone, Double.valueOf(user.account),user.email,address);
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
