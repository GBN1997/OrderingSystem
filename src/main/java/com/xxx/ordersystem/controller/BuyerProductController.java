package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.entity.ProductCategory;
import com.xxx.ordersystem.entity.ProductInfo;
import com.xxx.ordersystem.service.ProductCategoryService;
import com.xxx.ordersystem.service.ProductInfoService;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ProductInfoVO;
import com.xxx.ordersystem.viewobject.ProductVO;
import com.xxx.ordersystem.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //查询在售商品
        List<ProductInfo> allInSell = productInfoService.findAllInSell();

        //查询类目
        /*List<Integer> categoryIds = new ArrayList<>();
        for (ProductInfo productInfo : allInSell) {
            categoryIds.add(productInfo.getCategoryType());
        }*/
        //lambda
        List<Integer> categoryIds = allInSell.stream()
                                    .map(ProductInfo::getCategoryType)
                                    .collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryIds);

        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : allInSell) {
                if (productInfo.getCategoryType() == category.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            ProductVO productVO = new ProductVO(
                    category.getCategoryName(),
                    category.getCategoryId(),
                    productInfoVOList
            );
            productVOList.add(productVO);
        }
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMessage("成功");
//        ProductInfoVO product = new ProductInfoVO("1","aaa",new BigDecimal(2.3),
//                "goof","/xxx.jps");
//        List<ProductInfoVO> list = new ArrayList<>();
//        list.add(product);
//        resultVO.setData(Arrays.asList(new ProductVO("热销",1, list)));
//        resultVO.setData(productVOList);
        return ResultVOUtil.success(productVOList);
    }
}
