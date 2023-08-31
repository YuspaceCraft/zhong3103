package com.example.shop_online.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    public Integer orderid;
    public String date;
    public String name;
    public String address;
    public Integer num;
    public Double price;
    public String comment;
}
