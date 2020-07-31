package com.university.utils;

/**
 * @author 方翔鸣
 * @create 2019-03-12 20:26
 **/
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;
    /**
     * 比较2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1,Double d2){
        //math.abs(x)返回x的绝对值
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
