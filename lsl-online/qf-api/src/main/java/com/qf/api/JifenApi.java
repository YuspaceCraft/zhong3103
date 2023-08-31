package com.qf.api;

import com.qf.entity.Company;
import com.qf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

// 这个api文件 只是对 qf-jifen 模块中的接口进行声明
@FeignClient("qf-jifen")
public interface JifenApi {
    String user = "/jifen/user";
    String company = "/jifen/company";
    // 写多了就会发现，代码写起来是有规律可循的
    @GetMapping(user+"/allUser")
    List<User> allUser();

    @RequestMapping(company+"/getCompany")
    Map<Integer, Company> getCompany(@RequestParam("ids") String ids);

    @RequestMapping(user+"/getUser")
    Map<Integer, User> getUser(@RequestParam("ids") String ids);
}
