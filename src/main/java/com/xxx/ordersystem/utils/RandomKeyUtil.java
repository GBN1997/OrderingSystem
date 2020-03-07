package com.xxx.ordersystem.utils;

import java.util.Random;

public class RandomKeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static synchronized String genUniqueKey4Product(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return String.valueOf(number);
    }
}
