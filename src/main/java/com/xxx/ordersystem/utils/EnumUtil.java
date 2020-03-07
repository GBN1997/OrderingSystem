package com.xxx.ordersystem.utils;

import com.xxx.ordersystem.enums.CodeEnum;

/**
 * @Author: GuBoNan
 * @Date: 2019/12/27 10:53
 * @Version: 1.0
 * @Description:根据code获取订单状态的枚举类型
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
