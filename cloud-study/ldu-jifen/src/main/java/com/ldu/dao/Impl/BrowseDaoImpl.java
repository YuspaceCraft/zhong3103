package com.ldu.dao.Impl;

import com.ldu.dao.BrowseDao;
import com.ldu.entity.Browse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrowseDaoImpl implements BrowseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Browse> findAll() {
        String sql = "select * from browse";
        RowMapper<Browse> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Browse>(Browse.class);
        // 执行静态的SQL查询，并通过RowMapper返回结果
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    @Override
    public int addbrowse(int id, String label,Double price,int soldnum,String description) {
        String sql = "insert into browse(id,label,price,soldnum,description) value(?,?,?,?,?)";
        return jdbcTemplate.update(sql,id,label,price,soldnum,description);
    }
}