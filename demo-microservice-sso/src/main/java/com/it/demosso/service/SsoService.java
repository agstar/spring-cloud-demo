package com.it.demosso.service;

import com.it.demosso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SsoService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * 当调用失败调用容错方法
     * @author rcl
     * @date 2018/6/10 10:09
     */
    //@HystrixCommand(fallbackMethod = "checkUserFallbackMethod")
    public User checkUser(String username, String password) {
        // 进行容错处理,以服务名称的方式调用
        String serviceId = "demo-microservice-user";
        User user = this.restTemplate.getForObject("http://" + serviceId + "/user/" + username, User.class);
        if (user != null) {
            String pwd = user.getPassword();
            if (pwd.equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 请求失败执行的方法
     *
     * @author rcl
     * @date 2018/6/10 0:02
     */
    public User checkUserFallbackMethod(String username, String password) {
        return new User(null, "获取用户信息出错", null, null, null, null, null, null, null);
    }


}
