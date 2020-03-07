package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.SellerInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    SellerInfoRepository repository;

    @Test
    public void getTest(){
        SellerInfo one = repository.getOne(1001);
        Assert.notNull(one,"结果为空");
    }

//    @Test
//    public void findTest(){
//        SellerInfo sellerInfo = repository.findByOpenid("zxc123");
//        Assert.notNull(sellerInfo,"结果不存在！");
//    }
}