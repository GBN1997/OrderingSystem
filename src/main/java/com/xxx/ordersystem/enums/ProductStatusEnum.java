package com.xxx.ordersystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在售"),
    DOWN(1, "停售");

    private Integer code;

    private String msg;
}
