package com.qf.controller;

import com.qf.dao.CommentDao;
import com.qf.entity.Comment;
import com.qf.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/comment")
public class CommentController {
    @Autowired
    CommentDao commentDao;

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


}
