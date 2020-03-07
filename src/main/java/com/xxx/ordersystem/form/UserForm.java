package com.xxx.ordersystem.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/16 18:23
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String username;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;


    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    private String zhuohao;
    private String renshu;
}
