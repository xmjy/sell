package com.university.service;

import com.university.dto.OrderDTO;

/**
 * 消息推送
 * @author 方翔鸣
 * @create 2019-03-27 16:29
 **/
public interface PushMessageService {
    /**
     * 订单消息变更
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
