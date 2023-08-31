package com.qf.controller;

import com.qf.config.HttpController;
import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends HttpController<UserMapper, User> {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDao userDao;

    // 查 /jifen/user
    // 删 /jifen/user/数字
    // 加 /jifen/user   post对象
    // 改 /jifen/user/数字   post对象
    //   /jifen/user/getUser?ids=1,2...
    //  /jifen/user/ellogin


    @RequestMapping("/allUser")
    public List<User> allUser() {
        return userMapper.selectList(null);
    } // 在写之前可以先去父类HttpController先去查找一下，看有没有类似的代码

    @GetMapping("/finduserByConditions")
    public List<User> finduserByConditions(@RequestParam("username") String username,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("email") String email,
                                           @RequestParam("location") String location,
                                           @RequestParam("address") String address){
        List<User> userlist = userDao.finduserByConditions(username,phone,email,location,address);
        return userlist;

    }
    @GetMapping("/adduser")
    public List<User>  addCommodity(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("account") String account,
                                    @RequestParam("email") String email,
                                    @RequestParam("location") String location,
                                    @RequestParam("address") String address){
        userDao.insert(username,password,phone,
                account,email,location,address);
        List<User> userList =userMapper.selectList(null);
        return userList;
    }

    @GetMapping("/delUser")
    public List<User> delUser(@RequestParam("id") String id){
        userDao.deleteuser(id);
        List<User> userList = userMapper.selectList(null);
        return userList;


    }


}
//    // 传入人id，得到人名字
//    @RequestMapping("/getUser") // 1,2,3,4
//    public Map<Integer, User> getUser(String ids) {
//            return userMapper.getUser(ids);
//    }


//@RequestMapping("/ellogin")
//    public Map<String, Object> ellogin(User user) {
//        Map<String, Object> map = new HashMap<>();
//        User result = userMapper.login(user);
//        if (result == null) {
//            // 账号/密码错误 这是提示语
//            map.put("code", 0);
//        } else {
//            // 登录成功
//            map.put("code", 1);
//            map.put("uid", result.id);
//            map.put("user", result.user);
//            map.put("status", "游客"); // 人的身份？
//        }
//        return map; // 把结果返回给前端
//    }