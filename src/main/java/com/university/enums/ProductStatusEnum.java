package com.university.enums;

import lombok.Getter;

/**
 * 商品状态
 * @auther 林就远
 * @date 2019/2/18 13:03
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {

    up(0,"在架"),
    down(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }


}
