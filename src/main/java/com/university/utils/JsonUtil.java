package com.university.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author 方翔鸣
 * @create 2019-03-13 7:42
 **/
public class JsonUtil {
    /**
     * 将对象格式化为json格式
     * @param object
     * @return
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
