package com.xxx.ordersystem.service;

import com.xxx.ordersystem.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryID);

    Page<ProductCategory> findAll(Pageable pageable);

    List<ProductCategory> findAll();
    /**
     * 查询指定类型商品
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory category);
}
