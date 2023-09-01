package com.ldu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order1 {
    public Integer orderid;
    public String date;
    public String name;
    public String address;
    public Integer num;
    public Double price;
    public String comment;
}