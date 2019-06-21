package com.it.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
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
