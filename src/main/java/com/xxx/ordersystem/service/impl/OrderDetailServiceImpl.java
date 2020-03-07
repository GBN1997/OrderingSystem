package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.entity.OrderMaster;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.repository.OrderDetailRepository;
import com.xxx.ordersystem.repository.OrderMasterRepository;
import com.xxx.ordersystem.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/28 9:18
 * @Version: 1.0
 * @Description:
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository detailRepository;
    
    @Autowired
    OrderMasterRepository masterRepository;
    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        OrderMaster orderMaster = masterRepository.getOne(orderId);
        if(orderMaster == null){
            throw new OrderSystemException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> details = detailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(details)){
            throw new OrderSystemException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        return details;
    }
}
