package com.xxx.ordersystem.exception;

import com.xxx.ordersystem.enums.ResultEnum;
import lombok.Data;

@Data
public class OrderSystemException extends RuntimeException {

    private Integer code;

    public OrderSystemException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public OrderSystemException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
