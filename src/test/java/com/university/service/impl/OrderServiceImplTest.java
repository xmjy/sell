package com.university.service.impl;

import com.university.dataobject.OrderDetail;
import com.university.dto.OrderDTO;
import com.university.enums.OrderStatusEnum;
import com.university.enums.PayStatusEnum;
import com.university.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buyerOpenid = "110112";

    private final String orderId = "1551087521997491240";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(buyerOpenid);
        orderDTO.setBuyerName("鲍勃");
        orderDTO.setBuyerPhone("1457852222");
        orderDTO.setBuyerAddress("五都镇镇中");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("abc");
        o1.setProductQuantity(28);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("bcd");
        o2.setProductQuantity(5);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(orderId);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(orderId,result.getOrderId());
    }

    @Test
    public void findList() {

        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        System.out.println("总共有几页元素？ ——》： "+orderDTOPage.getTotalPages()+"页");
        System.out.println("总共有几个元素？ ——》： "+orderDTOPage.getTotalElements()+"个");

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findListOne(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOS = orderService.findList(pageRequest);
        //Assert.assertNotEquals(0,orderDTOS.getTotalElements());
        //下面的message只有在报错才会显示出来，满足condition就通过，不满足condition就报错，显示message
        Assert.assertTrue("查询所有的订单列表",orderDTOS.getTotalElements() > 0);
    }
}