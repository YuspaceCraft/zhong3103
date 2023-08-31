package com.ldu.dao;

import com.ldu.entity.Commodity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CommodityDao {
    List<Commodity> findAll();

    List<Commodity> Fuzzyfind(String str);
}
