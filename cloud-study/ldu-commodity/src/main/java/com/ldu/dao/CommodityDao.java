package com.ldu.dao;

import com.ldu.entity.Commodity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CommodityDao {
    List<Commodity> findAll();

    List<Commodity> Fuzzyfind(String str);
    List<Commodity> findBycommId(int id);

    List<Commodity> findByConditions(String label);
    int updatecommodity(String label,String img,String price,String description,String id);
    int insert(String label,String img,String price,String description);
    int deleteCommodity(String id);
}
