package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);

    List<OrderMaster> findByBuyerOpenidAndOrderStatus(String buyerOpenid, Integer orderStatus);
}
