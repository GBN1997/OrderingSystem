package com.xxx.ordersystem.service;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderMasterService {
    /** 新建订单 */
    OrderDTO create(OrderDTO orderDTO);
    /** 查询单个订单 */
    OrderDTO findOne(String orderId);
    /** 查询用户的订单*/
    Page<OrderDTO> findByBuyerOpenid(String openid, Pageable pageable);

    /** 完成、支付、取消订单*/
    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO pay(OrderDTO orderDTO);

    OrderDTO cancel(OrderDTO orderDTO);
    /** 查询所有订单*/
    Page<OrderDTO> findAll(Pageable pageable);

    List<OrderDTO> findListStats(String openid, Integer status);
}
