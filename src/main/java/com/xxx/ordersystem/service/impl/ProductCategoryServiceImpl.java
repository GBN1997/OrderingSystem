package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.ProductCategory;
import com.xxx.ordersystem.repository.ProductCategoryRepository;
import com.xxx.ordersystem.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryID) {
        return repository.getOne(categoryID);
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }
    @Override
    public ProductCategory save(ProductCategory category) {
        return repository.save(category);
    }
}
