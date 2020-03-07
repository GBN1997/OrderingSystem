package com.xxx.ordersystem.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author: GuBoNan
 * @Date: 2020/1/6 9:52
 * @Version: 1.0
 * @Description:
 */
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(object);
    }
}
