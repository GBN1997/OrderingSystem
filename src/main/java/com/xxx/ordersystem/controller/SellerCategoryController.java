package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.entity.ProductCategory;
import com.xxx.ordersystem.enums.ProductStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.form.CategoryForm;
import com.xxx.ordersystem.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/30 15:25
 * @Version: 1.0
 * @Description:
 */
@RequestMapping("/seller/category")
@Controller
public class SellerCategoryController {
    @Autowired
    ProductCategoryService categoryService;

    /**
     * 类目列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView categoryList(@RequestParam(name = "page", defaultValue = "1")Integer page,
                                     @RequestParam(name = "size", defaultValue = "10")Integer size,
                                     HashMap<String, Object> map){
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductCategory> category = categoryService.findAll(request);
        map.put("categoryPage", category);
        map.put("currentPage", page);
        return new ModelAndView("/category/list", map);
    }
    /**
     * 跳转至更新/添加类目页面
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView toUpdate(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                                 HashMap<String, Object> map){
        ProductCategory category = new ProductCategory();
        if (categoryId != null){
            category = categoryService.findOne(categoryId);
        }
        map.put("category", category);
        return new ModelAndView("/category/index", map);
    }


    @PostMapping("/save")
    public ModelAndView update(@Valid CategoryForm categoryForm,
                               BindingResult bindingResult,
                               HashMap<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("/common/error");
        }
        ProductCategory category = new ProductCategory();
//        if (categoryForm.getCategoryId() != null){
//            category = categoryService.findOne(categoryForm.getCategoryId());
//        }
        BeanUtils.copyProperties(categoryForm, category);
        categoryService.save(category);
        map.put("msg", ResultEnum.CATEGORY_SAVE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("/common/success",map);
    }
}
