package com.university.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.university.dataobject.OrderDetail;
import com.university.dto.OrderDTO;
import com.university.enums.ResultEnum;
import com.university.exception.SellException;
import com.university.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @auther 林就远
 * @date 2019/2/26 14:03
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        //借号支付调试
//        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerOpenid("oTgZpwWLWbM9BMBHeIPFqpWMmvWQ");
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】 错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
