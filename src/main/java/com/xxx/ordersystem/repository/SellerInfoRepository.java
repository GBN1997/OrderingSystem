package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/27 8:57
 * @Version: 1.0
 * @Description:
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, Integer> {
    SellerInfo findByUsername(String username);

    SellerInfo findBySellerId(Integer sellerId);
}
