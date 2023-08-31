package com.qf.dao.impl;

import com.qf.dao.CommentDao;
import com.qf.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> findAll() {
        String sql = "select * from comment";
        RowMapper<Comment> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Comment> getid(int id) {
        String sql = "select * from comment where id="+id;
        RowMapper<Comment> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int addcomment(int id, String username, String comments) {
        String sql = "insert into comment(id,username,comments) value(?,?,?)";
        return jdbcTemplate.update(sql,id,username,comments);
    }

    @Override
    public int deleteComment(String cid) {
        return jdbcTemplate.update("delete from comment where cid = ?",cid);
    }
    @Override
    public int updatecomment(String username,String comments,int cid){
        return jdbcTemplate.update("update comment set username=? ," +
                "comments=? " +
                "where cid=?",username, comments,cid);
    }
}
