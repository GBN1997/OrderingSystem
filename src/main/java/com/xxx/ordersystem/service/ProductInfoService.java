package com.xxx.ordersystem.service;

import com.xxx.ordersystem.dto.CartDTO;
import com.xxx.ordersystem.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    ProductInfo add();

    Page<ProductInfo> findAll(Pageable pageable);
    /**
     * 查询所有在售商品
     */
    List<ProductInfo> findAllInSell();

    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
    //下架商品
    ProductInfo offSale(String productId);
    //上架商品
    ProductInfo onSale(String productId);
}
