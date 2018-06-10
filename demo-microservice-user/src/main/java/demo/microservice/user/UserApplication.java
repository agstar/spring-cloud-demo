package demo.microservice.user;

import demo.microservice.user.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户微服务
 *
 * @author rcl
 * @date 2018/6/9 21:26
 */
@EnableEurekaClient
@ComponentScan(basePackages = {"demo.microservice.user"})
@MapperScan("demo.microservice.user.mapper")
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(UserApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
    }
}
