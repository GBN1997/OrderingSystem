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
 * @Date: 2020/2/16 14:52
 * @Version: 1.0
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Comment {

    @Id
    @GeneratedValue
    private int commentId;
    private String openid;
    private String name;
    private String avatarUrl;//头像
    private String content;
    private Date createTime;
}
