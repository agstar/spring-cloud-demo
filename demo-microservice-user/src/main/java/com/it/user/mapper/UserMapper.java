package com.it.user.mapper;

import com.it.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author rcl
 * @date 2018/6/9 22:02
 */
@Repository
public interface UserMapper extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
