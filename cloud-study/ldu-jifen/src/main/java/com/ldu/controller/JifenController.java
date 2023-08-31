package com.ldu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ldu.config.SentinelFallback;
import com.ldu.dao.CommentDao;
import com.ldu.dao.UserDao;
import com.ldu.entity.Comment;
import com.ldu.entity.Jifen;
import com.ldu.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jifen")
public class JifenController {
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;

    @RequestMapping("/getuser")
    public List<User> getuser(){
        return userDao.findAll();
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String updateuser(@RequestParam("username") String username, @RequestParam("password") String password
            , @RequestParam("location") String location, @RequestParam("phone") String phone
            , @RequestParam("account") Double account, @RequestParam("email") String email
            , @RequestParam("address") String address){
        userDao.updateuser(username,password,location,phone,account,email,address);
        return "Success!";
    }

    @RequestMapping("/adduser")
    public int adduser(@RequestParam("username") String username, @RequestParam("password") String password
            , @RequestParam("location") String location, @RequestParam("phone") String phone
            , @RequestParam("account") Double account, @RequestParam("email") String email
            , @RequestParam("address") String address) {
        return userDao.adduser(2,username,password,location,phone,account,email,address);
    }

//    @RequestMapping("/userlogin")
//    public int userlogin(@RequestParam("username") String username, @RequestParam("password") String password){
//        List<User> tmp = userDao.findAll();
//        for(int i=0; i<tmp.size();i++){
//            if()
//        }
//    }

    @RequestMapping(value = "/buything", method = RequestMethod.GET)
    public String buything( @RequestParam("account") Double account){
        userDao.buything(account);
        return "Success!";
    }

    @RequestMapping("/getcomment")
    public List<Comment> getcomment(){
        return commentDao.findAll();
    }

    @RequestMapping("/getcommentById")
    public List<Comment> getcommentById(@RequestParam("id") int id){
        return commentDao.getid(id);
    }

    @RequestMapping("/addcomment")
    public int addcomment(@RequestParam("id") int id,
                          @RequestParam("username") String username, @RequestParam("comments") String comments){
        return commentDao.addcomment(id,username,comments);
    }




    //localhost:9004/add/100
    @RequestMapping("/add/{score}")
    public Jifen add(@PathVariable int score) {
        return new Jifen(1, 10, score);
    }
    //  localhost:9004/search?uid=2&score=200
    @RequestMapping("search")
    public Jifen search(int uid, int score) {
        return new Jifen(2, uid, score);
    }

    @RequestMapping("/test")
    public String test(){

        return String.valueOf(userDao.findAll());
    }

    @RequestMapping("/sleep")
    @SentinelResource(value = "sleep",
            fallbackClass = SentinelFallback.class,
            fallback = "fallback"
    )
    public String sleep(String s){
        if(StringUtils.isNotBlank(s)){
            try{
                int sec = Integer.parseInt(s);
                Thread.sleep(sec*1000L);//睡3秒
            }catch (Exception e){
                return "success";
            }
        }
        return "success";
    }
    @RequestMapping("/ex")
    public String ex()throws Exception{
        throw new Exception("我是错误");
    }
}
