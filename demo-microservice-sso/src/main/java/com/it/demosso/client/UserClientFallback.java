package com.it.demosso.client;

import com.it.demosso.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User getUserByUsername(String username) {
        return new User(null, "获取用户信息出错", null, null, null, null, null, null, null);
    }
}
