package com.xxx.ordersystem.form;

import lombok.Data;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 15:09
 * @Version: 1.0
 * @Description:
 */
@Data
public class SellerForm {

    private String username;
    private String password;
    private String phone;
    private Integer sellerId;
}