package com.xxx.ordersystem.form;

import com.xxx.ordersystem.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/31 9:37
 * @Version: 1.0
 * @Description:
 */
@Data
public class ProductForm {
    @Id
    private String productId;
    @NotBlank
    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;
    //小图
    private String productIcon;
    //0：正常 1：下架
    private int productStatus = ProductStatusEnum.UP.getCode();

    private int categoryType;
}
