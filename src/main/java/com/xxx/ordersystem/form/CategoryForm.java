package com.xxx.ordersystem.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/31 13:16
 * @Version: 1.0
 * @Description:
 */
@Data
public class CategoryForm {
    private Integer categoryId;
    @NotBlank
    private String categoryName;
    @NotNull
    private Integer categoryType;

}
