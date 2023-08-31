package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 打开MyBatis开关
public interface ActivityMapper extends BaseMapper<Activity> {

}
