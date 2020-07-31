package com.university.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 * @auther 方翔鸣
 * @date 2019/2/23 10:57
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -2047808707122574344L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    @JsonProperty("sellCount")
    private Integer productSale;

    @JsonProperty("rating")
    private Integer productRating;
}
