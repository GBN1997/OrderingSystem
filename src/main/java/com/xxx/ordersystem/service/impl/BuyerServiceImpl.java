package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.service.BuyerService;
import com.xxx.ordersystem.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/6 13:36
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderMasterService orderMasterService;

    @Override
    public OrderDTO findOneOrder(String orderId, String openid) {
        OrderDTO orderDTO = checkOpenid(orderId, openid);
        return orderMasterService.findOne(orderId);
    }

    @Override
    public OrderDTO cancelOneOrder(String orderId, String openid) {
        OrderDTO orderDTO = checkOpenid(orderId, openid);
        if (orderDTO == null){
           log.error("【取消订单】 订单不存在 ，orderId={}", orderId);
           throw new OrderSystemException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMasterService.cancel(orderDTO);
    }
    @Override
    public List<OrderDTO> findOrderList(String openid, Integer status) {
        List<OrderDTO> list = new ArrayList<>();
        list.clear();

        List<OrderDTO> listStats = orderMasterService.findListStats(openid, status);
        listStats.forEach((orderDTO) -> {
            String orderId = orderDTO.getOrderId();
            OrderDTO one = orderMasterService.findOne(orderId);
            list.add(one);
        });
        Collections.reverse(list);
        return list;
    }

    public OrderDTO checkOpenid(String orderId, String openid){
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【操作订单】 订单不属于该用户！");
            throw new OrderSystemException(ResultEnum.OPENID_ERROR);
        }
        return orderDTO;
    }
}
