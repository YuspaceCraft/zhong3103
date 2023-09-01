package com.ldu.dao;

import com.ldu.entity.Browse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrowseDao {

    public List<Browse> findAll();

    int addbrowse(int id, String label, Double price, int soldnum, String description);
}