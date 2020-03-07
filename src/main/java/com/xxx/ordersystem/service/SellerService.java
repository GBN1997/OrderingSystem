package com.xxx.ordersystem.service;

import com.xxx.ordersystem.entity.SellerInfo;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/31 14:27
 * @Version: 1.0
 * @Description:
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @return
     */
    SellerInfo findSellerInfoByUsername(String username);

}
