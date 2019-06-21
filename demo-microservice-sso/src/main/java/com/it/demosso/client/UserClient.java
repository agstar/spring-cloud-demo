package com.it.demosso.client;

import com.it.demosso.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "demo-microservice-user",fallback =UserClientFallback.class )
public interface UserClient  {

    @GetMapping(value = "user/{username}")
    User getUserByUsername(@PathVariable("username") String username);


}
