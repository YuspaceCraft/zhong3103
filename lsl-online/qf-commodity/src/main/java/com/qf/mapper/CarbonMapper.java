package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Carbon;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 只改了一个单词 Activity-->Carbon
public interface CarbonMapper extends BaseMapper<Carbon> {


}
