package com.university.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.university.dataobject.OrderDetail;
import com.university.enums.OrderStatusEnum;
import com.university.enums.PayStatusEnum;
import com.university.utils.EnumUtil;
import com.university.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @auther 林就远
 * @date 2019/2/24 15:17
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)//返回的结果有null的不显示给前台
public class OrderDTO {
    /* 订单id */
    private String orderId;

    /* 买家名字 */
    private String buyerName;

    /* 买家电话 */
    private String buyerPhone;

    /* 买家地址 */
    private String buyerAddress;

    /* 买家openid*/
    private String buyerOpenid;

    /* 订单总金额 */
    private BigDecimal orderAmount;

    /* 订单状态 默认状态为0，新订单*/
    private Integer orderStatus;

    /* 订单支付状态，默认状态为0，等待支付*/
    private Integer payStatus;

    /* 创建时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /* 更新时间 */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //@Transient //加这个注解是为了类与表的字段一一对应，忽略此字段
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
