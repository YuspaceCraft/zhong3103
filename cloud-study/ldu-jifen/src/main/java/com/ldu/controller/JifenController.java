package com.ldu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ldu.config.SentinelFallback;
import com.ldu.dao.BrowseDao;
import com.ldu.dao.CommentDao;
import com.ldu.dao.OrderDao;
import com.ldu.dao.UserDao;
import com.ldu.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jifen")
public class JifenController {
    @Autowired
    UserDao userDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    private BrowseDao browseDao;
    @Autowired
    private OrderDao orderDao;

    private int uid;
    private String uname;

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

    //lsl
    @RequestMapping("/allUser")
    public List<User> allUser() {
        return userDao.findAll();
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
        List<User> userList = userDao.findAll();
        return userList;
    }

    @GetMapping("/delUser")
    public List<User> delUser(@RequestParam("id") String id){
        userDao.deleteuser(id);
        List<User> userList = userDao.findAll();
        return userList;


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

    @RequestMapping("/addbrowse")
    public int addbrowse(@RequestParam("id") int id,
                          @RequestParam("label") String label, @RequestParam("price") Double price,
                         @RequestParam("soldnum") int soldnum, @RequestParam("description") String description){
        return browseDao.addbrowse(id,label,price,soldnum,description);
    }

    @RequestMapping("/addorder1")
    public int addorder1(@RequestParam("orderid") int orderid,
                         @RequestParam("date") String date, @RequestParam("name") String name,
                         @RequestParam("address") String address, @RequestParam("num") int num,
                         @RequestParam("price") Double price, @RequestParam("comment") String comment){
        return orderDao.addorder1(orderid,date,name,address,num,price,comment);
    }

    //lsl
    @RequestMapping("/deleteComment")
    public List<Comment> deleteComment(@RequestParam("cid") String cid){
        commentDao.deleteComment(cid);
        return commentDao.findAll();
    }
    @GetMapping("/updateComment")
    public List<Comment> updateComment(@RequestParam("username") String username,
                                       @RequestParam("comments") String comments,
                                       @RequestParam("cid") int cid){
        commentDao.updatecomment(username, comments,cid);
        return  commentDao.findAll();
    }

    //zrz
    @GetMapping("/browse")
    public List<Browse> getData(){
        List<Browse> browse=browseDao.findAll();
//        System.out.println(equipments);
        return browse;
    }
    @GetMapping("/findall")
    public List<Order1> getdata(){
        List<Order1> orders=orderDao.findAll();
//        System.out.println(equipments);
        return orders;
    }

    @GetMapping("/dimfind")
    public List<Order1> dimorders(@RequestParam("orders") String name){
        List<Order1> orders= orderDao.findByStr(name);
        System.out.println(orders);
        return orders;
    }

    @GetMapping("/delete")
    public Integer delete(@RequestParam("orderid") Integer id) throws IOException {
        System.out.println("delete方法执行");
        return orderDao.delete(id);
    }

    @RequestMapping(value = "/updatecomment", method = RequestMethod.GET)
    public int update(@RequestParam("orderid") Integer id,@RequestParam("comment") String comment) {
        System.out.println(id + "     " +comment);
        System.out.println(comment.getClass());
        Order1 order = orderDao.findById(id);

        System.out.println(order.getComment());
        order.setComment(comment);

        System.out.println(order.getComment());
        System.out.println("更改成功");
        return orderDao.update(order);
    }

    @GetMapping("/finduser")
    public User finduser(@RequestParam("id") Integer id){
        User user = userDao.findById(id);
        System.out.println(user);
        return user;
    }

    @GetMapping("/updateuser1")
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
