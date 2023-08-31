package com.qf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.api.JifenApi;
import com.qf.config.HttpController;
import com.qf.config.Record;
import com.qf.entity.Activity;
import com.qf.entity.Commodity;
import com.qf.entity.User;
import com.qf.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity/activity")
public class ActivityController extends HttpController<ActivityMapper, Activity> {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    JifenApi jifenApi; // 如果使用api报错，就是开关没打开
    // 下拉选项Record   <用户, "张三", "124837">
    //              <用户, "李四", "124810">
    //              <用户, "王五", "124817">
    @RequestMapping("/uid")
    public Record uid() {
        // 要提供一种数据类型 Map<重复Key, Value, Key>
        Record r = new Record();
        // 数组？就是为了for！绝对正确
//        for (User user : jifenApi.allUser()) {
//            r.add("user", user.user, user.id);
//        }
        return r;
    }

    // 都是SpringBoot的内容
    // 测试  /commodity/activity       //直接获取数据表中数据
    //      /commodity/activity/数字    //按照主键(数字)删除
    //      /commodity/activity  传入对象    //添加数据到表中
    //      /commodity/activity/数字   传入对象  //修改数据
    //      /commodity/activity/list    //你自己写的地址



    @Override // 直接覆盖原有代码，地址也不会变？地址是什么？
    public List<Activity> delete(@PathVariable String id) {
        if (id != null && id.contains("page")) {
            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("id");
            int page = Integer.valueOf(id.replace("page",""));
            List<Activity> list = mapper.selectPage(new Page<>(page,10),wrapper).getRecords();
            String ids = ""; //       条件 ? 语句1 : 语句2
            for (Activity activity : list) { // 三目运算符
                ids += ("".equals(ids) ? "" : ",") + activity.uid; // 1,2
            }
            System.out.println(ids); // feign是远程调用，消耗的时间比较多 1,2,3,4
            Map<Integer, User> map = jifenApi.getUser(ids); // 只有一次远程调用
            System.out.println(map); // {"1":"张三","2":"李四"}
//            for (Activity activity : list) {
//                activity.user = map.get(activity.uid).user; // 人id
//            }
            return list; // 系统调优，如果慢的话，可以使用Sentinel慢调用监控
        } else {
            mapper.deleteById(id);
            return null;
        }
    }



}
