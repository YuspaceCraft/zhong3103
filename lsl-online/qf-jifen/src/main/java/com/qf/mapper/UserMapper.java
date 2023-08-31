package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper                         // 都被封装到了BaseMapper
public interface UserMapper extends BaseMapper<User> {
    // sql熟不熟? 比较难的查询就需要写sql语句！mysql
    // @Insert("insert into ...")
    // @Update("update 表 ...")
    // @Delete("delete from ...")
    // mybatisplus  selectList()  selectById()         1,2
    @MapKey("id") // 传入主键
    @Select("select id,user from tbl_user where id in (${ids})")
    Map<Integer, User> getUser(@Param("ids") String ids);
    // 可以将List<User>--->Map<Integer, User>
    //                          id
    @Select("select * from tbl_user where username=#{username} and password=#{password}")
    User login(User user); // user.username  user.password
    // 如果形参是对象，前面不用写注解
    // 如果形参不是对象，前面要加注解@Param()
    // 如果null，账号/密码写错了
    // 如果不是null，登录成功

}
