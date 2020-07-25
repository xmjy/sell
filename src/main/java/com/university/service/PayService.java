package com.university.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.university.dto.OrderDTO;

/**
 * @author 林就远
 * @create 2019-03-11 12:55
 **/
public interface PayService {

    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);
    RefundResponse refund(OrderDTO orderDTO);
}
