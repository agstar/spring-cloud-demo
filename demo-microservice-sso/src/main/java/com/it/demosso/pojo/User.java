package com.it.demosso.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private String name;

    private String email;

    private Date birthday;

    private String sex;

    private Integer state;

    private String code;

}
