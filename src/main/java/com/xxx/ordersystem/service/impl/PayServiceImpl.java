package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.converter.OrderMaster2OrderDto;
import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderMaster;
import com.xxx.ordersystem.enums.OrderStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.repository.OrderMasterRepository;
import com.xxx.ordersystem.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/25 21:34
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    //    @Autowired
    //    private OrderService orderService;

    public OrderDTO goPay(OrderDTO orderDTO) {
        //判断订单状态
        OrderMaster orderMaster = orderMasterRepository.getOne(orderDTO.getOrderId());
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderMaster.getOrderId(),
                    orderMaster.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        //OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderStatus(OrderStatusEnum.NEW_PAYED.getCode());//已支付
//        orderMaster.setPayStatus(OrderStatusEnum.NEW_PAYED.getCode());//已支付
        //BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】更新失败, orderMaster={}", orderMaster);
            throw new OrderSystemException(ResultEnum.ORDER_PAY_FAILURE);
        }
        return orderDTO;
    }


    //退款
//    public RefundResponse refund(OrderDTO orderDTO) {
        //        RefundRequest refundRequest = new RefundRequest();
        //        refundRequest.setOrderId(orderDTO.getOrderId());
        //        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        //        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        //        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
        //
        //        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        //        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));

//        return null;
//    }
}
