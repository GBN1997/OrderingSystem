package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.ProductInfo;
import com.xxx.ordersystem.enums.ProductStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoServiceImpl service;

    @Test
    void findOne() {
        System.out.println(service.findOne("001"));
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,5);
        Page<ProductInfo> page = service.findAll(request);
        System.out.println(page.getTotalElements());
    }

    @Test
    void findAllInSell() {
        System.out.println(service.findAllInSell());
    }

    @Test
    void save() {
        service.save(new ProductInfo("0002","天椒鸡腿皇堡"
                ,new BigDecimal(25.00),99,"GOOD",
                "/xxx/sss.jpg", ProductStatusEnum.UP.getCode(),2));
    }

    @Test
    void offSale(){
//        ProductInfo productInfo = service.offSale("001");
//        Assert.notNull(productInfo,"商品不存在");
    }
}