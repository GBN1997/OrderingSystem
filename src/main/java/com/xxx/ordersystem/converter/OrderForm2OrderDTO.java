package com.xxx.ordersystem.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/4 15:48
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new OrderSystemException(ResultEnum.ORDER_PARAM_FAILURE);
        }
        orderDTO.setOrderDetails(orderDetailList);

        return orderDTO;
    }
}
