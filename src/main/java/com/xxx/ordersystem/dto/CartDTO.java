package com.xxx.ordersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/19 15:10
 * @Version: 1.0
 * @Description: 购物车
 */
@Data
@AllArgsConstructor
public class CartDTO {
    private String productId;

    private Integer productQuantity;
}
