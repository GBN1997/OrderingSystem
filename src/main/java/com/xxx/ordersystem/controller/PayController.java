package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderMaster;
import com.xxx.ordersystem.enums.OrderStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.service.BuyerService;
import com.xxx.ordersystem.service.OrderMasterService;
import com.xxx.ordersystem.service.PayService;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/25 21:26
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    OrderMasterService orderMasterService;

    @Autowired
    PayService payService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    private OrderMasterService orderService;

    @GetMapping("/goPay")
    public ResultVO<Boolean> goPay(@RequestParam("orderId") String orderId) {
        //1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new OrderSystemException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        OrderDTO orderDTO1 = payService.goPay(orderDTO);
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_PAYED.getCode())) {
            log.error("【取消订单】订单状态不正确,  orderStatus={}", orderDTO1.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_STATUS_ERROR);
        }
        return ResultVOUtil.success(true);
    }
}
