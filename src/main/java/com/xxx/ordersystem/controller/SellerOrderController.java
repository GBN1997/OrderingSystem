package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.service.OrderDetailService;
import com.xxx.ordersystem.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/27 9:31
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    OrderMasterService orderMasterService;

    /**
     * 商家获取所有订单
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")int page,
                             @RequestParam(value = "size", defaultValue = "8")int size,
                             Map<String, Object> map){
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest request = PageRequest.of(page - 1, size, sort);
        Page<OrderDTO> orderDTOPage = orderMasterService.findAll(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        return new ModelAndView("/order/list", map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,
                               @RequestParam("currentPage")Integer currentPage,
                               HashMap<String,Object> map){
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDTO);
        } catch (OrderSystemException e) {
            log.info("【订单取消】 {}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/detail?orderId=" + orderId + "&currentPage=" + currentPage);
            return new ModelAndView("/common/error");
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/detail?orderId=" + orderId + "&currentPage=" + currentPage);
        return new ModelAndView("/common/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               @RequestParam("currentPage")Integer currentPage,
                               HashMap<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderMasterService.findOne(orderId);
        } catch (OrderSystemException e) {
            log.info("【订单详情异常】 {}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list?page=" + currentPage);
            return new ModelAndView("/common/error");
        }
        map.put("orderDTO", orderDTO);
        map.put("currentPage", currentPage);
        return new ModelAndView("/order/detail", map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param currentPage
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               @RequestParam("currentPage")Integer currentPage,
                               HashMap<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.finish(orderDTO);
        } catch (OrderSystemException e) {
            log.info("【订单完结异常】 {}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/detail?orderId=" + orderId + "&currentPage=" + currentPage);
            return new ModelAndView("/common/error");
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/detail?orderId=" + orderId + "&currentPage=" + currentPage);
        return new ModelAndView("/common/success");
    }
}
