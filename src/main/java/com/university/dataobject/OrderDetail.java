package com.university.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @auther 林就远
 * @date 2019/2/23 18:30
 */
@Entity
@Data
public class OrderDetail {

    /* 详情id */
    @Id
    private String detailId;

    /* 订单id */
    private String orderId;

    /* 商品id */
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 商品数量 */
    private Integer productQuantity;

    /* 商品小图 */
    private String productIcon;
}
