package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.dto.CartDTO;
import com.xxx.ordersystem.entity.ProductInfo;
import com.xxx.ordersystem.enums.ProductStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.repository.ProductInfoRepository;
import com.xxx.ordersystem.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.getOne(productId);
    }

    @Override
    public ProductInfo add() {
        return null;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findAllInSell() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo product = repository.getOne(cartDTO.getProductId());
            if (product == null){
                throw new OrderSystemException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = product.getProductStock() + cartDTO.getProductQuantity();
            product.setProductStock(result);
            repository.save(product);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo product = repository.getOne(cartDTO.getProductId());
            if (product == null){
                throw new OrderSystemException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = product.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){
                throw new OrderSystemException(ResultEnum.STOCK_SHORTAGE);
            }
            product.setProductStock(result);
            repository.save(product);
        }
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo product = repository.getOne(productId);
        if (product == null){
            throw new OrderSystemException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        repository.save(product);
        return product;
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo product = repository.getOne(productId);
        if (product == null){
            throw new OrderSystemException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        product.setProductStatus(ProductStatusEnum.UP.getCode());
        return product;
    }

}
