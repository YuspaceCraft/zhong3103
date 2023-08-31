package com.example.shop_online.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Browse {
    public Integer id;
    public String name;
    public String address;
    public String shopname;
    public String classification;
}
