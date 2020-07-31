package com.university.dto;

import lombok.Data;

/**
 * 购物车
 * @auther 方翔鸣
 * @date 2019/2/24 17:56
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
