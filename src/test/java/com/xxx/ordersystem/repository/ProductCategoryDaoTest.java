package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory one = repository.getOne(1);
        System.out.println(one);
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory category = new ProductCategory("sss",4);
        ProductCategory save = repository.save(category);
        Assert.notNull(save,"null pointer");
    }

    @Test
    public void findCategory(){
        List<Integer> type = Arrays.asList(1,2,3);
        List<ProductCategory> cates = repository.findByCategoryTypeIn(type);
        Assert.notEmpty(cates,"Empty");
        System.out.println(cates);
    }
}