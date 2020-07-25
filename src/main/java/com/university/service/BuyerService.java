package com.university.service;

import com.university.dto.OrderDTO;

/**
 * 买家
 * @auther 林就远
 * @date 2019/2/26 21:15
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
