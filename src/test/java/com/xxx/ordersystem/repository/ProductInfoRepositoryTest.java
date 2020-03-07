package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository repository;

    @Test
    public void save(){
        ProductInfo product = new ProductInfo("0001","天椒皇堡"
                ,new BigDecimal(25.00),99,"GOOD",
                "/xxx/xxx.jpg",0,1);
        repository.save(product);
    }

    @Test
    public void findTest(){
        System.out.println(repository.getOne("0001"));
    }

    @Test
    void findByProductStatus() {
        List<ProductInfo> products = repository.findByProductStatus(0);
        Assert.notEmpty(products,"Collection is Empty");
    }
}