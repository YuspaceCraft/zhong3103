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
@TableName("orders")
public class Order {
    @TableId(type = IdType.AUTO)
    public Integer id;
    public String  label;
    public String  img;
    public String  price;
    public String  amount;
    public String  user;
    public String  issend;



}
