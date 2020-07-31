package com.university.converter;

import com.university.dataobject.OrderMaster;
import com.university.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther 方翔鸣
 * @date 2019/2/25 17:14
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;

    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());

    }
}
