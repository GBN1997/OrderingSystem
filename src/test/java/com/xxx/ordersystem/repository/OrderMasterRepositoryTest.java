package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository repository;

    @Test
    void findByBuyerOpenid() {
        //List<OrderMaster> result = repository.findByBuyerOpenid("123");
    }
}