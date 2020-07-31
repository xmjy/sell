package com.university.utils;

import com.university.enums.CodeEnum;

/**
 * @author 方翔鸣
 * @create 2019-03-21 17:37
 **/
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return  null;
    }
}
