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
@TableName("tbl_carbon")
public class Carbon {
    @TableId(type = IdType.AUTO) // 主键是 自动递增
    public Integer id;
    public String carbon;
    public Integer num;
    public Integer cid;
    @TableField(exist = false)
    public String company;

}
