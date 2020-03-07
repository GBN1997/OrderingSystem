package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/17 16:55
 * @Version: 1.0
 * @Description:
 */
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findByPicId(Integer picId);
}
