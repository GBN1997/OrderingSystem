package com.xxx.ordersystem.repository;

import com.xxx.ordersystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 14:58
 * @Version: 1.0
 * @Description:
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByOpenid(String openid);
}
