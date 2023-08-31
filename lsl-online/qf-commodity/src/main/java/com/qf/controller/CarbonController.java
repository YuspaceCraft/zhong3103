package com.qf.controller;

import com.qf.api.JifenApi;
import com.qf.config.HttpController;
import com.qf.entity.Carbon;
import com.qf.entity.Company;
import com.qf.mapper.CarbonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity/carbon")
public class CarbonController extends HttpController<CarbonMapper, Carbon> {
    @Autowired
    CarbonMapper carbonMapper;
    @Autowired
    JifenApi jifenApi;

    //  查询  /commodity/carbon
    //  删除 /commodity/carbon/数字
    //  添加  /commodity/carbon  发post对象
    //  修改 /commodity/carbon/数字   发post对象
    //    /commodity/carbon/list

    @RequestMapping("/list") // 自己的个性化定制
    public List<Carbon> list() {
        List<Carbon> list = carbonMapper.selectList(null);
        String ids = "";
        for (Carbon carbon : list) {
            ids += ("".equals(ids) ? "" : ",") + carbon.cid;
        } // 3,4,1,2,...
        // 远程调用，得到别人项目的数据
        Map<Integer, Company> map = jifenApi.getCompany(ids);
        for (Carbon carbon : list) {
            carbon.company = map.get(carbon.cid).company;
        }
        return list; // 最后将处理好的list返回
    }

}
