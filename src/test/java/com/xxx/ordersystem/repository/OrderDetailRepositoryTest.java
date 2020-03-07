package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository repository;

    @Test
    void findByOrderId() {
        List<OrderDetail> details = repository.findByOrderId("201912190001");
        System.out.println(details);
    }
}