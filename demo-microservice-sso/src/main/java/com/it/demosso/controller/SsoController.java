package com.it.demosso.controller;

import com.it.demosso.pojo.OptResult;
import com.it.demosso.pojo.User;
import com.it.demosso.service.FlowSocket;
import com.it.demosso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class SsoController {


    @Autowired
    private SsoService ssoService;
    @Autowired
    private FlowSocket flowSocket;



    /**
     * 登录接口
     *
     * @param username 账号
     * @param password 密码
     * @return OptResult
     */
    @GetMapping(value = "login/{username}/{password}")
    public OptResult queryItemById(@PathVariable("username") String username, @PathVariable("password") String password) {
        User user = ssoService.checkUser(username, password);
        if (user == null || user.getId() == null) {
            return new OptResult(1, "登录失败", user);
        }
        return new OptResult(1, "登录成功", user);
    }

    @PostMapping("msg")
    public String postMsg(String msg) {
        System.out.println(msg);
        flowSocket.sendLightRecord(msg);
        return "get";
    }
    @LogAop(content = "阈值配置页面")
    @RequestMapping("test1")
    public String test1(String msg) {
        System.out.println(msg);
        return "get";
    }
    @LogAop(content = "test2")
    @RequestMapping("test2")
    public String test2(String msg) {
        System.out.println(msg);
        return "get";
    }

}
