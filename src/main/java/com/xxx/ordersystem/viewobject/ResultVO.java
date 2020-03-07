package com.xxx.ordersystem.viewobject;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */

@Data
public class ResultVO<T> {
    private Integer code;

    private String msg;
    /** 具体内容 */
    private T Data;
}
