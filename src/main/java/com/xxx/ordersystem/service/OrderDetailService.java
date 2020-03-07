package com.xxx.ordersystem.service;

import com.xxx.ordersystem.entity.OrderDetail;

import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/28 9:17
 * @Version: 1.0
 * @Description:
 */
public interface OrderDetailService {
    List<OrderDetail> findByOrderId(String orderId);
}
