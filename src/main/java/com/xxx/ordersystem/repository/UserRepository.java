package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:15
 * @Version: 1.0
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByOpenid(String openid);
}
