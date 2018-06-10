package demo.microservice.user.mapper;

import demo.microservice.user.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author rcl
 * @date 2018/6/9 22:02
 */
@Repository
public interface UserMapper {
    User findByUsername(String username);
}
