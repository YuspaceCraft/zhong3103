package com.qf.dao;


import com.qf.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll();

    List<Comment> getid(int id);

    int addcomment(int id, String username, String comments);


    int deleteComment(String cid);

    int updatecomment(String username,String comments,int cid);
}
