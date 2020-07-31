package com.university.dataobject;

import com.university.enums.OrderStatusEnum;
import com.university.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @auther 方翔鸣
 * @date 2019/2/23 18:14
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /* 订单id */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /* 订单支付状态，默认状态为0，等待支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;

    /* 一个订单里面可以有几个商品 */
    //@Transient //加这个注解是为了类与表的字段一一对应，忽略此字段
    //private List<OrderDetail> orderDetailList;
}
