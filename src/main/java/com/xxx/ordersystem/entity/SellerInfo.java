package com.xxx.ordersystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/27 8:52
 * @Version: 1.0
 * @Description:
 */
@Data
@Entity
public class SellerInfo {

    @Id
    @GeneratedValue
    private Integer sellerId;

    private String username;

    private String password;

    private String phone;

    private Date createTime;

    private Date updateTime;
}
