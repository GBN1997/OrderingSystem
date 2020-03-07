package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.constant.CookieConstant;
import com.xxx.ordersystem.constant.RedisConstant;
import com.xxx.ordersystem.entity.SellerInfo;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.form.SellerForm;
import com.xxx.ordersystem.repository.SellerInfoRepository;
import com.xxx.ordersystem.service.SellerService;
import com.xxx.ordersystem.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/31 15:38
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class SellerController {
    @Autowired
    SellerInfoRepository repository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpServletResponse response) {
        SellerInfo sellerInfo = repository.findByUsername(username);
        log.info("商家信息={}", sellerInfo);
        if (sellerInfo != null && sellerInfo.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            log.info("登录成功的token={}", token);
            Integer expire = RedisConstant.EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token)
                    , username, expire, TimeUnit.SECONDS);
            //设置token至cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            return "redirect:/seller/order/list";
        } else {
            return "/login/error";
//            throw new OrderSystemException(ResultEnum.LOGIN_FAIL);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除redis
            redisTemplate.opsForValue().getOperations()
                    .delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/sell/index");
        return new ModelAndView("common/success", map);
    }

    /*
     * 页面相关
     * */

//    @GetMapping("/list")
//    public ModelAndView list(Map<String, Object> map) {
//        List<SellerInfo> categoryList = repository.findAll();
//        map.put("categoryList", categoryList);
//        return new ModelAndView("admin/list", map);
//    }
//
//    @GetMapping("/index")
//    public ModelAndView index(@RequestParam(value = "sellerId", required = false) Integer sellerId,
//                              Map<String, Object> map) {
//        SellerInfo sellerInfo = repository.findBySellerId(sellerId);
//        map.put("category", sellerInfo);
//
//        return new ModelAndView("admin/index", map);
//    }
//
//    /**
//     * 保存/更新
//     */
//    @PostMapping("/save")
//    public ModelAndView save(@Valid SellerForm form,
//                             BindingResult bindingResult,
//                             Map<String, Object> map) {
//        log.info("SellerForm={}", form);
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/sell/admin/index");
//            return new ModelAndView("common/error", map);
//        }
//        SellerInfo sellerInfo = new SellerInfo();
//        try {
//            if (form.getSellerId() != null) {
//                sellerInfo = repository.findBySellerId(form.getSellerId());
//            }
//            BeanUtils.copyProperties(form, sellerInfo);
//            repository.save(sellerInfo);
//        } catch (OrderSystemException e) {
//            map.put("msg", e.getMessage());
//            map.put("url", "/sell/admin/index");
//            return new ModelAndView("common/error", map);
//        }
//
//        map.put("url", "/sell/admin/list");
//        return new ModelAndView("common/success", map);
//    }
}
