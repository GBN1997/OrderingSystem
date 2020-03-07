package com.xxx.ordersystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/4 15:34
 * @Version: 1.0
 * @Description:
 */
@Data
public class OrderForm {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "电话不能为空")
    private String phone;

    @NotEmpty(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
