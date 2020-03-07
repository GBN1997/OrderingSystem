package com.xxx.ordersystem.service;

import com.xxx.ordersystem.entity.User;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:19
 * @Version: 1.0
 * @Description:
 */
public interface UserService {
    User findUserByOpenid(String openid);
}
