package com.xxx.ordersystem.converter;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/20 20:47
 * @Version: 1.0
 * @Description:
 */
public class OrderMaster2OrderDto {
    public static OrderDTO convert(OrderMaster master){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> masters){
        return masters.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
