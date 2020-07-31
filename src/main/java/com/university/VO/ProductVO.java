package com.university.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 * @auther 方翔鸣
 * @date 2019/2/23 10:51
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -9188398320646125530L;

    /* 将categoryName在前台显示为name*/
    @JsonProperty("name")
    private String categoryName;

    /* 将categoryType在前台显示为type*/
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
