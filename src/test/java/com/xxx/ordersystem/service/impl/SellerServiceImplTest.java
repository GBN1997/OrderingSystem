package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.SellerInfo;
import com.xxx.ordersystem.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SellerServiceImplTest {
    private String openId = "zxc123";

    @Autowired
    SellerService sellerService;
    @Test
    void findSellerInfoByOpenId() {
//        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId(openId);
//        Assert.notNull(sellerInfo, "result is null");
    }
}