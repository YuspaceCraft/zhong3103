package com.ldu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Browse {
    public Integer id;
    public String label;
    public String price;
    public int soldnum;
    public String description;
}