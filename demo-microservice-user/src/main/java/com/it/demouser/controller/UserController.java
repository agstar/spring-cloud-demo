package com.it.demouser.controller;

import com.it.demouser.pojo.User;
import com.it.demouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * usercontroller
 *
 * @author rcl
 * @date 2018/6/9 22:24
 */
@RestController
@RefreshScope  //微服务客户端配置自动更新
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 对外提供接口服务，根据账号查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return this.userService.findByUsername(username);
    }

}
