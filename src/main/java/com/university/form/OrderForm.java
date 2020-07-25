package com.university.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用于表单验证
 * @auther 林就远
 * @date 2019/2/26 13:47
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "买家姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "买家手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址必填")
    private String address;

    /**
     * openid订单的查询，相当于一个用户
     */
    @NotEmpty(message = "买家的openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
