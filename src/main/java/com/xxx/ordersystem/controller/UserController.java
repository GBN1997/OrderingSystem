package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.entity.User;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.form.UserForm;
import com.xxx.ordersystem.repository.UserRepository;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:13
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepository repository;

    //
    @PostMapping("/save")
    public ResultVO create(@Valid UserForm userForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("参数不正确, userForm={}", userForm);
            throw new OrderSystemException(ResultEnum.ORDER_PARAM_FAILURE.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User userOld = repository.findUserByOpenid(userForm.getOpenid());
        User user = new User();
        if (userOld != null) {
            user.setId(userOld.getId());
        }
        user.setUsername(userForm.getUsername());
        user.setOpenid(userForm.getOpenid());
        user.setPhone(userForm.getPhone());
        user.setZhuohao(userForm.getZhuohao());
        user.setRenshu(userForm.getRenshu());

        return ResultVOUtil.success(repository.save(user));
    }

    @GetMapping("/getUserInfo")
    public ResultVO getUserInfo(@RequestParam("openid") String openid) {
        User user = repository.findUserByOpenid(openid);
        return ResultVOUtil.success(user);
    }

}
