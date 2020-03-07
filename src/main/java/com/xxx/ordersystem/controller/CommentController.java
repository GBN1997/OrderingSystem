package com.xxx.ordersystem.controller;

import com.xxx.ordersystem.dto.OrderDTO;
import com.xxx.ordersystem.entity.Comment;
import com.xxx.ordersystem.entity.OrderMaster;
import com.xxx.ordersystem.enums.OrderStatusEnum;
import com.xxx.ordersystem.enums.ResultEnum;
import com.xxx.ordersystem.exception.OrderSystemException;
import com.xxx.ordersystem.repository.CommentRepository;
import com.xxx.ordersystem.repository.OrderMasterRepository;
import com.xxx.ordersystem.service.OrderMasterService;
import com.xxx.ordersystem.utils.ResultVOUtil;
import com.xxx.ordersystem.viewobject.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/17 14:02
 * @Version: 1.0
 * @Description:
 */
@RestController
public class CommentController {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderMasterRepository masterRepository;

    //订单详情
    @PostMapping("/comment")
    public ResultVO<Comment> detail(@RequestParam("openid") String openid,
                                    @RequestParam("orderId") String orderId,
                                    @RequestParam("name") String name,
                                    @RequestParam("avatarUrl") String avatarUrl,
                                    @RequestParam("content") String content) {
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)) {
            throw new OrderSystemException(ResultEnum.COMMENT_PARAM_ERROR);
        }
        //提交评论
        Comment comment = new Comment();
        comment.setName(name);
        comment.setAvatarUrl(avatarUrl);
        comment.setOpenid(openid);
        comment.setContent(content);
        Comment save = repository.save(comment);

        //修改订单状态
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.COMMENT.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        return ResultVOUtil.success(save);
    }

    //所有评论
    @GetMapping("/commentList")
    public ResultVO<List<Comment>> commentList() {
        List<Comment> all = repository.findAll();
        return ResultVOUtil.success(all);
    }

    //单个用户的所有评论
    @GetMapping("/userCommentList")
    public ResultVO<List<Comment>> userCommentList(@RequestParam("openid") String openid) {
        List<Comment> all = repository.findAllByOpenid(openid);
        return ResultVOUtil.success(all);
    }
}
