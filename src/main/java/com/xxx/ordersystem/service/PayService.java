package com.xxx.ordersystem.service;

import com.xxx.ordersystem.dto.OrderDTO;
//import com.xxx.ordersystem.entity.PayRequest;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/25 21:33
 * @Version: 1.0
 * @Description:
 */
public interface PayService {
    OrderDTO goPay(OrderDTO orderDTO);
}
