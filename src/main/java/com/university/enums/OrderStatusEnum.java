package com.university.enums;

import lombok.Getter;

/**
 * @auther 方翔鸣
 * @date 2019/2/23 18:17
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
