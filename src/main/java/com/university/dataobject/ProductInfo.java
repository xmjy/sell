package com.university.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.university.enums.ProductStatusEnum;
import com.university.enums.ResultEnum;
import com.university.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @auther 方翔鸣
 * @date 2019/2/18 11:05
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -8344700240880818984L;

    /* 商品Id */
    @Id
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 商品库存 */
    private Integer productStock;

    /* 好评率 */
    private Integer productRating;

    /* 月售量 */
    private Integer productSale;

    /* 商品描述 */
    private String productDescription;

    /* 商品小图 */
    private String productIcon;

    /* 商品状态 0正常，1下架*/
    private Integer productStatus = ProductStatusEnum.up.getCode();

    /* 商品类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
       return  EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
