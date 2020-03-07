package com.xxx.ordersystem.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/6 14:53
 * @Version: 1.0
 * @Description:
 */
@Data
public class PayRequest {
    private String openid;

    private BigDecimal orderAmount;

    private String orderId;

    private String orderName;

}
