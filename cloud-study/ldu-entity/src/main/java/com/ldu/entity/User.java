package com.ldu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public Integer id;
    public String username;
    public String password;
    public String location;
    public String phone;
    public String account;
    public String email;
    public String address;
    public Integer age;
}
