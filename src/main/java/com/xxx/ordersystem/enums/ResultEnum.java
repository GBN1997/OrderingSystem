package com.xxx.ordersystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回给前端的异常信息
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(0, "成功"),

    ORDER_CANCEL_SUCCESS(1, "订单取消成功"),

    ORDER_FINISH_SUCCESS(2, "订单完结成功"),

    CATEGORY_SAVE_SUCCESS(3, "类目保存成功"),

    PRODUCT_SAVE_SUCCESS(4, "商品保存成功"),

    PRODUCT_NOT_EXIST(10, "商品不存在！"),

    STOCK_SHORTAGE(11, "商品库存不足！"),

    OFF_SALE_SUCCESS(12, "商品下架成功！"),

    ON_SALE_SUCCESS(13, "商品上架成功！"),

    ORDER_NOT_EXIST(20, "订单不存在！"),

    ORDER_NO_PRODUCT(20, "订单中没有商品！"),

    ORDERDETAIL_NOT_EXIST(20, "订单详情不存在！"),

    ORDER_STATUS_ERROR(21, "订单状态错误！"),

    ORDER_CANCEL_FAILURE(22, "订单取消失败！"),

    ORDER_FINISH_FAILURE(23, "订单完结失败！"),

    ORDER_PAY_FAILURE(24, "订单支付失败！"),

    ORDER_PARAM_FAILURE(25,"订单参数不正确！"),

    OPENID_NOT_EXIST(26,"openid不能为空！"),

    OPENID_ERROR(28,"订单不属于当前用户！"),

    CART_EMPTY(27,"购物车为空！"),

    WECHAT_OAUTH_ERROR(30, "微信授权异常！"),

    LOGIN_FAIL(40, "登陆失败！"),

    LOGOUT_SUCCESS(41, "登出成功！"),

    COMMENT_PARAM_ERROR(50 , "评论参数异常");

    private Integer code;

    private String msg;
}
