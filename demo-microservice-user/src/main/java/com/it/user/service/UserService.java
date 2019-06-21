package com.it.user.service;

import com.it.user.mapper.UserMapper;
import com.it.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

}
