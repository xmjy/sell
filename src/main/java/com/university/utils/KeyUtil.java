package com.university.utils;

import java.util.Random;

/**
 * @auther 方翔鸣
 * @date 2019/2/24 16:12
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：当前时间+6位随机数
     * 注意：要加关键字synchronized，因为当多线程的是好，即使是毫秒级别也是会有很高的并发情况
     * @return
     */
    public static synchronized String getUniqueKey(){

        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);

    }
}
