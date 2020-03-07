package com.xxx.ordersystem.service.impl;

import com.xxx.ordersystem.converter.OrderMaster2OrderDto;
import com.xxx.ordersystem.dto.CartDTO;
import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.OrderDetail;
import com.xxx.ordersystem.entity.OrderMaster;
import com.xxx.ordersystem.entity.ProductInfo;
import com.xxx.ordersystem.enums.OrderStatusEnum;
//import com.xxx.ordersystem.enums.PayStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.repository.OrderDetailRepository;
import com.xxx.ordersystem.repository.OrderMasterRepository;
import com.xxx.ordersystem.service.OrderMasterService;
import com.xxx.ordersystem.service.ProductInfoService;
import com.xxx.ordersystem.service.WebSocket;
import com.xxx.ordersystem.utils.RandomKeyUtil;
import javassist.tools.web.Webserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    OrderMasterRepository masterRepository;

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    OrderDetailRepository detailRepository;

    @Autowired
    WebSocket webSocket;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = RandomKeyUtil.genUniqueKey();
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.查询商品数量、价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {

            ProductInfo product = new ProductInfo();
            try {
                product = productInfoService.findOne(orderDetail.getProductId());
            }catch (Exception e){
                log.error("【创建订单】 商品不存在！");
                throw new OrderSystemException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            amount = product.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(amount);
            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(RandomKeyUtil.genUniqueKey());
            BeanUtils.copyProperties(product,orderDetail);
            detailRepository.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //3.写入数据库
        OrderMaster orderMaster = new OrderMaster();
        /** 先拷贝、再设值*/
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
//        orderMaster.setPayStatus(OrderStatusEnum.NEW.getCode());
        masterRepository.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO
                .getOrderDetails()
                .stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        //发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = masterRepository.getOne(orderId);
        if (orderMaster == null){
            throw new OrderSystemException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw new OrderSystemException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenid(String openid, Pageable pageable) {
        Page<OrderMaster> orderMasterList = masterRepository.findByBuyerOpenid(openid,pageable);
        List<OrderDTO> orderDTOs = OrderMaster2OrderDto.convert(orderMasterList.getContent());
        return new PageImpl<>(orderDTOs, pageable, orderMasterList.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_PAYED.getCode())){
            log.error("【取消订单】订单状态不正确 orderId={} status={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【完结订单】完结订单失败 orderId = {}", orderMaster.getOrderId());
            throw new OrderSystemException(ResultEnum.ORDER_FINISH_FAILURE);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】订单状态不正确 orderId={} status={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】订单支付状态不正确 orderId={} status={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_PAY_FAILURE);
        }
        //修改支付状态
        orderDTO.setOrderStatus(OrderStatusEnum.NEW_PAYED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【支付订单】支付订单失败 orderId = {}", orderMaster.getOrderId());
            throw new OrderSystemException(ResultEnum.ORDER_FINISH_FAILURE);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) && !orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_PAYED.getCode())){
            log.error("【取消订单】订单状态不正确 orderId={} status={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new OrderSystemException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        /** 先修改状态再拷贝 */
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单失败】 orderId = {}", orderMaster.getOrderId());
            throw new OrderSystemException(ResultEnum.ORDER_CANCEL_FAILURE);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetails())){
            log.error("【取消订单】 订单中无商品 orderDTO={}",orderDTO);
            //throw new OrderSystemException(ResultEnum.ORDER_NO_PRODUCT);
        }
        List<CartDTO> cartDTOS = orderDTO.getOrderDetails()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOS);
        //如果已支付则退款
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_PAYED.getCode())){
            //TODO 退款
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> allOrder = masterRepository.findAll(pageable);
        List<OrderDTO> allOrderDTOs = OrderMaster2OrderDto.convert(allOrder.getContent());
        return new PageImpl<>(allOrderDTOs, pageable, allOrder.getTotalElements());
    }

    //查询不同订单状态列表
    @Override
    public List<OrderDTO> findListStats(String buyerOpenid, Integer orderStatus) {
        List<OrderMaster> orderMasters = masterRepository.findByBuyerOpenidAndOrderStatus(buyerOpenid, orderStatus);
        return OrderMaster2OrderDto.convert(orderMasters);
    }

}
