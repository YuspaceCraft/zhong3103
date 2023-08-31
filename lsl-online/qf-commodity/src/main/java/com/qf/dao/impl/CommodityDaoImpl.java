package com.qf.dao.impl;

import com.qf.dao.CommodityDao;
import com.qf.entity.Commodity;
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
    public List<Commodity> findByConditions(String label) {
        String sql = "SELECT * FROM commodity WHERE " +
                "label LIKE CONCAT('%', ?, '%') " ;
        RowMapper<Commodity> rowMapper = new BeanPropertyRowMapper<>(Commodity.class);
        return this.jdbcTemplate.query(sql, rowMapper,label);
    }
    @Override
    public int updatecommodity(String label,String img,String price,String description,String id){
        return jdbcTemplate.update("update commodity set label=? ," +
                "img=? ," +
                "price=? ," +
                "description=?  " +
                "where id=?",label, img, price,description,id);
    }

    @Override
    public int insert(String label,String img,String price,String description) {
        String sql = "insert into commodity(label,img,price,description,soldnum)" +
                " value(?,?,?,?,'0')";

        return jdbcTemplate.update(sql,label,img,price,description);
    }

    @Override
    public int deleteCommodity(String id) {
        return jdbcTemplate.update("delete from commodity where id = ?",id);
    }
    @Override
    public List<Commodity> Fuzzyfind(String str) {
        String sql = "select * from  commodity where (id like '%"+str+"%') or (label like '%"+str+"%') or (description like '%"+str+"%')";
        RowMapper<Commodity> rowMapper = new BeanPropertyRowMapper<Commodity>(Commodity.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}
