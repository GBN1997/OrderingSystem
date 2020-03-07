package com.xxx.ordersystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.enums.OrderStatusEnum;
//import com.xxx.ordersystem.enums.PayStatusEnum;
import com.xxx.ordersystem.utils.EnumUtil;
import com.xxx.ordersystem.utils.serializer.Date2LongSerializer;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Data
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;
    /** 微信openID */
    private String buyerOpenid;

    private BigDecimal orderAmount;
    /** 订单状态 默认为0 新订单未支付 */
    private Integer orderStatus;
//    /** 支付状态 默认为0 未支付 */
//    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetails;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(Integer code){
        return EnumUtil.getByCode(code,OrderStatusEnum.class);
    }
//    @JsonIgnore
//    public PayStatusEnum getPayStatusEnum(Integer code){
//        return EnumUtil.getByCode(code,PayStatusEnum.class);
//    }
}
