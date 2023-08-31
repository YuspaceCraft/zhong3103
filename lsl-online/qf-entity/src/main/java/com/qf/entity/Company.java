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
@TableName("tbl_company")
public class Company { // 和tbl_company表对应
    @TableId(type = IdType.AUTO)
    public Integer id;
    public String company;
    public String username;
    public String password;
    public Integer carbon;
    public Integer start;

}
