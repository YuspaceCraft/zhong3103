package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_activity") // 这个类跟这个表对应
public class Activity {
    // 跟tbl_activity表是对应的
    @TableId(type = IdType.AUTO) // 主键为 自动递增类型
    public Integer id;
    public String activity;
    public Integer num;
    public Integer money;
    @NotNull // 不允许为空 必须下拉选1个人
    public Integer uid;

    public String picture; // 因为数据表多了一个字段
    // 它不是数据表中的字段，请加注解
    @TableField(exist = false)
    public String user;
}
