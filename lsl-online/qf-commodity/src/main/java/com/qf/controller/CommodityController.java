package com.qf.controller;

import com.qf.config.HttpController;
import com.qf.dao.CommodityDao;
import com.qf.entity.Commodity;
import com.qf.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController extends HttpController<CommodityMapper, Commodity> {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CommodityDao commodityDao;

    @RequestMapping("/allcommodity")
    public List<Commodity> allcommodity(){
        return commodityMapper.selectList(null);
    }

    @GetMapping("/findCommodity")
    public List<Commodity> findCommodity(@RequestParam("label") String label){
        List<Commodity> commoditylist = commodityDao.findByConditions(label);
        System.out.println(commoditylist);
        return commoditylist;
    }
    @GetMapping("/updateCommodity")
    public List<Commodity> updateCommodity(@RequestParam("label") String label,
                                           @RequestParam("img") String img,
                                           @RequestParam("price") String price,
                                           @RequestParam("description") String description,
                                           @RequestParam("id") String id){
        int flag = commodityDao.updatecommodity(label,img,price,description,id);
        List<Commodity> commodityList =commodityMapper.selectList(null);
        System.out.println(commodityList);
        System.out.println(flag);
        return commodityList;

    }
    @GetMapping("/addCommodity")
    public List<Commodity>  addCommodity(
                               @RequestParam("label") String label,
                               @RequestParam("img") String img,
                               @RequestParam("price") String price,
                               @RequestParam("description") String description){
                commodityDao.insert(label,img,price,description);
                    List<Commodity> commodityList =commodityMapper.selectList(null);
                    return commodityList;
    }
    @GetMapping("/delCommodity")
    public List<Commodity> delCommodity(@RequestParam("id") String id){
        commodityDao.deleteCommodity(id);
        List<Commodity> commodityList = commodityMapper.selectList(null);
        return commodityList;


    }

    @RequestMapping(value = "/fuzzy", method = RequestMethod.GET)
    public List<Commodity> fuzzy(@RequestParam("fuzzystr") String fuzzystr){
        List<Commodity> all = commodityDao.Fuzzyfind(fuzzystr);
        return all;
    }


}
