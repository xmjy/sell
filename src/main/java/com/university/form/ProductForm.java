package com.university.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author 林就远
 * @create 2019-03-22 17:08
 **/
@Data
public class ProductForm {
    /* 商品Id */
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 商品库存 */
    private Integer productStock;

    /* 商品描述 */
    private String productDescription;

    /* 商品小图 */
    private String productIcon;

    /* 商品类目编号 */
    private Integer categoryType;
}
