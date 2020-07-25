package com.university.utils;

import java.util.Random;

/**
 * @auther 林就远
 * @date 2019/2/24 16:12
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：当前时间+6位随机数
     * @return
     */
    public static synchronized String getUniqueKey(){

        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);

    }
}
