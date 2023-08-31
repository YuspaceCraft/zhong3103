package com.ldu.dao.Impl;


import com.ldu.dao.CommodityDao;
import com.ldu.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommodityDaoImpl implements CommodityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Commodity> findAll() {
        String sql = "select * from commodity";
        RowMapper<Commodity> rowMapper;
        rowMapper = new BeanPropertyRowMapper<Commodity>(Commodity.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Commodity> Fuzzyfind(String str) {
        String sql = "select * from  commodity where (id like '%"+str+"%') or (label like '%"+str+"%') or (description like '%"+str+"%')";
        RowMapper<Commodity> rowMapper = new BeanPropertyRowMapper<Commodity>(Commodity.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}
