package com.university.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @auther 林就远
 * @date 2019/2/24 15:55
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"表示成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"该商品不存在"),

    PRODUCT_STOCK_ERROR(11,"该商品数量不够"),

    ORDER_NOT_EXIST(12,"该订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"该订单详情不存在"),

    ORDER_STATUS_ERROR(14,"该订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车不能为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(20,"微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"系统金额与订单金额不一致"),

    CANCEL_ORDER_SUCCESS(22,"卖家端成功取消订单"),

    FINISH_ORDER_SUCCESS(23,"该订单已成功完结"),

    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    LOGIN_FAIL(25,"登录失败，登录信息不正确"),

    LOGOUT_SUCCESS(26,"登出成功");


    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
