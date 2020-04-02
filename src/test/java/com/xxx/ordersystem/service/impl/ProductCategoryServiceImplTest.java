package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.ProductCategory;
import com.xxx.ordersystem.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl service;
    @Test
    void findOne() {
        ProductCategory category = service.findOne(1);
        Assert.notNull(category,"对象为空");
    }

    @Test
    void findAll()
    {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<ProductCategory> categories = service.findAll(pageRequest);
        Assert.notNull(categories,"对象为空！");
    }

    @Test
    void findByCategoryTypeIn() {

    }

    @Test
    @Transactional
    void save() {
        service.save(new ProductCategory("xxx",10));
    }
}