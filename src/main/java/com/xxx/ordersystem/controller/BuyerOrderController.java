package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.converter.OrderForm2OrderDTO;
import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.form.OrderForm;
import com.xxx.ordersystem.service.BuyerService;
import com.xxx.ordersystem.service.OrderMasterService;
import com.xxx.ordersystem.service.PayService;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/4 15:29
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    OrderMasterService orderMasterService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    PayService payService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           log.error("【创建订单】 参数不正确 ,orderform=[]",orderForm);
           throw new OrderSystemException(ResultEnum.ORDER_PARAM_FAILURE.getCode(),
                   bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【创建订单】购物车不能为空");
            throw new OrderSystemException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderMasterService.create(orderDTO);
        payService.goPay(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
    //订单列表
//    @GetMapping("/list")
//    public ResultVO<List<OrderDTO>> list(@RequestParam("openid")String openid,
//                                         @RequestParam(value = "page", defaultValue = "1")Integer page,
//                                         @RequestParam(value = "size", defaultValue = "11")Integer size){
//        if(StringUtils.isEmpty(openid)){
//            log.error("【订单列表】 openID为空");
//            throw new OrderSystemException(ResultEnum.OPENID_NOT_EXIST);
//        }
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<OrderDTO> orderDTOPage = orderMasterService.findByBuyerOpenid(openid, pageRequest);
//        return ResultVOUtil.success(orderDTOPage.getContent());
//    }

    @GetMapping("/listByStatus")
    public ResultVO<List<OrderDTO>> listByStatus(@RequestParam("openid") String openid,
                                                 @RequestParam(value = "orderStatus", defaultValue = "0") Integer orderStatus) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new OrderSystemException(ResultEnum.ORDER_PARAM_FAILURE);
        }

        List<OrderDTO> orderList = buyerService.findOrderList(openid, orderStatus);
        return ResultVOUtil.success(orderList);
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("orderId")String orderId,
                           @RequestParam("openid")String openid){
        OrderDTO orderDTO = buyerService.findOneOrder(orderId, openid);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("orderId")String orderId,
                           @RequestParam("openid")String openid){
        buyerService.cancelOneOrder(orderId, openid);
        return ResultVOUtil.success();
    }
}
