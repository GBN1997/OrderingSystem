package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.SellerInfo;
import com.xxx.ordersystem.repository.SellerInfoRepository;
import com.xxx.ordersystem.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/31 14:28
 * @Version: 1.0
 * @Description:
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return repository.findByUsername(username);
    }
}
