package com.ldu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    public Integer id;
    public int soldnum;
    public String label;
    public String img;
    public Double price;
    public String description;
}
