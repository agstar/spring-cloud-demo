package com.it.demouser.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Date birthday;
    private String sex;
    private int state;
    private String code;



}
