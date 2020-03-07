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
 * @Date: 2020/2/17 16:55
 * @Version: 1.0
 * @Description:
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Picture {

    @Id
    @GeneratedValue
    private Integer picId;
    private String picUrl;
    private String picMessage;

    private Date createTime;
    private Date updateTime;
}