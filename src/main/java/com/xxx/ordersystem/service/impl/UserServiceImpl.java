package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.User;
import com.xxx.ordersystem.repository.UserRepository;
import com.xxx.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:20
 * @Version: 1.0
 * @Description:
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByOpenid(String openid) {
        return userRepository.findUserByOpenid(openid);
    }
}
