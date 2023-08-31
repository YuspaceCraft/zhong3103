package com.qf.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public Integer id;
    public String username;
    public String comments;
    public int cid;
}

