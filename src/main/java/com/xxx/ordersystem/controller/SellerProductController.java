package com.xxx.ordersystem.controller;

import com.sun.org.apache.xml.internal.security.keys.KeyUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xxx.ordersystem.entity.ProductCategory;
import com.xxx.ordersystem.entity.ProductInfo;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.form.ProductForm;
import com.xxx.ordersystem.service.ProductCategoryService;
import com.xxx.ordersystem.service.ProductInfoService;
import com.xxx.ordersystem.utils.RandomKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/28 16:05
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    ProductCategoryService categoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView getAllProduct(@RequestParam(name = "page", defaultValue = "1")Integer page,
                                      @RequestParam(name = "size", defaultValue = "5")Integer size,
                                      HashMap<String, Object> map){
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> pageInfo = productInfoService.findAll(request);
        map.put("productPage",pageInfo);
        map.put("currentPage",page);
        return new ModelAndView("/product/list", map);
    }

    /**
     * 商品下架
     * @param productId
     * @param currentPage
     * @return
     */
    @GetMapping("/offsale")
    public ModelAndView offSale(@RequestParam("productId")String productId,
                                @RequestParam("currentPage")Integer currentPage,
                                HashMap<String, Object> map){
        try {
            productInfoService.offSale(productId);
        } catch (Exception e) {
            log.info("【订单下架】 {}",e.getMessage());
            map.put("msg", ResultEnum.PRODUCT_NOT_EXIST.getMsg());
            map.put("url","/sell/seller/product/list?page=" + currentPage);
            return new ModelAndView("/common/error");
        }
        map.put("msg", ResultEnum.OFF_SALE_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list?page=" + currentPage);
        return new ModelAndView("/common/success");
    }

    /**
     * 商品上架
     * @param productId
     * @param currentPage
     * @param map
     * @return
     */
    @GetMapping("/onsale")
    public ModelAndView onSale(@RequestParam("productId")String productId,
                               @RequestParam(name = "currentPage", required = false)Integer currentPage,
                               HashMap<String, Object> map){
        try {
            productInfoService.onSale(productId);
        } catch (Exception e) {
            log.info("【订单下架】 {}",e.getMessage());
            map.put("msg", ResultEnum.PRODUCT_NOT_EXIST.getMsg());
            map.put("url","/sell/seller/product/list?page=" + currentPage);
            return new ModelAndView("/common/error");
        }
        map.put("msg", ResultEnum.ON_SALE_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/list?page=" + currentPage);
        return new ModelAndView("/common/success");
    }

    /**
     * 跳转至更新/添加商品页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView toUpdate(@RequestParam(name = "productId", required = false)String productId,
                                 @RequestParam(name ="currentPage", required = false)Integer currentPage,
                                 HashMap<String, Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo product = productInfoService.findOne(productId);
            map.put("product", product);
        }
        List<ProductCategory> category = categoryService.findAll();
        map.put("currentPage", currentPage);
        map.put("category", category);
        return new ModelAndView("/product/index",map);
    }

    /**
     * 更新/保存商品
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             @RequestParam(name = "currentPage", required = false )Integer currentPage,
                             HashMap<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error");
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            if (!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productInfoService.findOne(productForm.getProductId());
            }else {
                productForm.setProductId(RandomKeyUtil.genUniqueKey4Product());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error");
        }
        map.put("msg", ResultEnum.PRODUCT_SAVE_SUCCESS.getMsg());
        if (currentPage == null){
            map.put("url","/sell/seller/product/list");
        }else {
            map.put("url","/sell/seller/product/list?page=" + currentPage);
        }
        return new ModelAndView("/common/success");
    }
}
