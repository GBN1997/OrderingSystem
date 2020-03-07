package com.xxx.ordersystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单未支付"),
    NEW_PAYED(1, "新订单已支付"),
    CANCEL(2, "已取消"),
    FINISHED(3, "完结"),
    COMMENT(4, "已评价"),
    ;

    private Integer code;

    private String msg;
}
