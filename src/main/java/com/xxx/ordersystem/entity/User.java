package com.xxx.ordersystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:14
 * @Version: 1.0
 * @Description:
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String phone;
    private String openid;
    private String zhuohao;//桌号
    private String renshu;//用餐人数

    private Date createTime;
    private Date updateTime;
}