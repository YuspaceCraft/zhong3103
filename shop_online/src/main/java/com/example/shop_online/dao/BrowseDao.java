package com.example.shop_online.dao;

import com.example.shop_online.pojo.Browse;
import com.example.shop_online.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrowseDao {

    public List<Browse> findAll();
}
