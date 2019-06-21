package com.it.demosso.service;

import com.it.demosso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SsoService {

    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private com.it.demosso.client.UserClient userclient;

    @Value("${server.port}")
    private int port;

    /**
     * 当调用失败调用容错方法
     *
     * @author rcl
     * @date 2018/6/10 10:09
     */
    public User checkUser(String username, String password) {
        User userByUsername = userclient.getUserByUsername(username);
        if (userByUsername != null) {
            System.out.println(userByUsername);
        }
        // 进行容错处理,以服务名称的方式调用
//        String serviceId = "demo-microservice-user";
//        User user = this.restTemplate.getForObject("http://" + serviceId + "/user/" + username, User.class);
        User user = userByUsername;
        user.setCode(port + "");
        if (user != null) {
            user.setCode(port + "");
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
