package com.qf.dao;

import com.qf.entity.Commodity;

import java.util.List;

public interface CommodityDao {
    List<Commodity> findByConditions(String label);
    int updatecommodity(String label,String img,String price,String description,String id);
    int insert(String label,String img,String price,String description);
    int deleteCommodity(String id);
    List<Commodity> Fuzzyfind(String str);
}
