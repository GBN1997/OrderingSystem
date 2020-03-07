package com.xxx.ordersystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xxx.ordersystem.enums.ProductStatusEnum;
import com.xxx.ordersystem.utils.EnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@DynamicUpdate
@NoArgsConstructor
@Proxy(lazy =false)
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;
    //小图
    private String productIcon;
    //0：正常 1：下架
    private int productStatus;

    private int categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductInfo(String productId, String productName, BigDecimal productPrice,
                       Integer productStock, String productDescription, String productIcon,
                       int productStatus, int categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(Integer code){
        return EnumUtil.getByCode(code, ProductStatusEnum.class);
    }

}
