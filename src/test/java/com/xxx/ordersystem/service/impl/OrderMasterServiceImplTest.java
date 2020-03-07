package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.dto.CartDTO;
import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.service.OrderMasterService;
import com.xxx.ordersystem.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class OrderMasterServiceImplTest {
    @Autowired
    OrderMasterService service;


    private final String BUYER_OPENID = "abc123" ;
    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerPhone("13888888888");
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerAddress("518");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("0001");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("0002");
        o2.setProductQuantity(3);
        orderDetails.add(o1);
        orderDetails.add(o2);

        orderDTO.setOrderDetails(orderDetails);
        OrderDTO result = service.create(orderDTO);
        log.info("【创建订单】 result{}", result);
        Assert.notNull(result,"结果为空");
    }

    @Test
    void findOne() {
        OrderDTO orderDTO = service.findOne("15768125427747");
        log.info("【订单信息】 result{}", orderDTO);
    }

    @Test
    void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0,1);
        List<OrderDTO> content = service.findByBuyerOpenid("abc123", request).getContent();
        Assert.notEmpty(content,"结果为空");
    }

    @Test
    void finish() {
        OrderDTO orderDTO = service.findOne("1576811674862811640");
        OrderDTO result = service.finish(orderDTO);
        Assert.notNull(result,"对象为空");
    }

    @Test
    void pay() {
        OrderDTO orderDTO = service.findOne("1576811674862811640");
        OrderDTO result = service.pay(orderDTO);
        Assert.notNull(result,"对象为空");
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = service.findOne("1576811674862811640");
        OrderDTO result = service.cancel(orderDTO);
        Assert.notNull(result,"对象为空");
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> all = service.findAll(request);
        Assert.notEmpty(all.getContent(),"结果为空");
    }
}