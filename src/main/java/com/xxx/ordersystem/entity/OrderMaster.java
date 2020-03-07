package com.xxx.ordersystem.entity;

import com.xxx.ordersystem.enums.OrderStatusEnum;
//import com.xxx.ordersystem.enums.PayStatusEnum;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Proxy(lazy = false)
@DynamicUpdate
@ToString
public class OrderMaster {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;
    /** 微信openID */
    private String buyerOpenid;

    private BigDecimal orderAmount;
    /** 订单状态 默认为0 新订单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态 默认为0 未支付 */
//    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
