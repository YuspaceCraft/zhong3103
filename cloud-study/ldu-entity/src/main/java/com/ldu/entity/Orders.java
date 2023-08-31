package com.ldu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    public int id;
    public String label;
    public Double price;
    public String img;
    public int amount;
}
