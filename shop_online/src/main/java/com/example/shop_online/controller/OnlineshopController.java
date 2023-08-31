package com.example.shop_online.controller;

import com.example.shop_online.dao.BrowseDao;
import com.example.shop_online.pojo.Browse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
