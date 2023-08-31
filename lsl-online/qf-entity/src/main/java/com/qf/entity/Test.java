package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_test")
public class Test {
    // Integer和id有什么区别
    @TableId(type = IdType.AUTO)
    public Integer id; // 主键
    public String title;
    public String test;
    public String content;

}
