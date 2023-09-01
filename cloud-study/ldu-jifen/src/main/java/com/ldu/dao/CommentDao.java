package com.ldu.dao;

import com.ldu.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll();

    List<Comment> getid(int id);

    int addcomment(int id, String username, String comments);

    List<Comment> Fuzzyfind(String str);

    int deleteComment(String cid);

    int updatecomment(String username,String comments,int cid);
}
