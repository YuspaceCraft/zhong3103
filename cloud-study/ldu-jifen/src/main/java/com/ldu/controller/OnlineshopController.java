package com.ldu.controller;


import com.ldu.dao.BrowseDao;
import com.ldu.entity.Browse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/onlineshop")
public class OnlineshopController {
    @Autowired
    private BrowseDao browseDao;

    @GetMapping("/browse")
    public List<Browse> getData(){
        List<Browse> browse=browseDao.findAll();
//        System.out.println(equipments);
        return browse;
    }

}
