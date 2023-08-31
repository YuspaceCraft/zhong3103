package com.qf.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kv { // 断言需要的数据结构
    public String key;   // 键
    public String value; // 值

    //  Need=age,18
    //  Need=键,值
}
