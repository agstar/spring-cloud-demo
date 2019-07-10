package com.it.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
