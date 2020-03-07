package com.xxx.ordersystem.service;

import com.xxx.ordersystem.dto.OrderDTO;

import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/6 13:34
 * @Version: 1.0
 * @Description:
 */
public interface BuyerService {
    public OrderDTO findOneOrder(String orderId, String openid);

    public OrderDTO cancelOneOrder(String orderId, String openid);

    //查询某个用户的所有订单
    List<OrderDTO> findOrderList(String openid, Integer status);
}
