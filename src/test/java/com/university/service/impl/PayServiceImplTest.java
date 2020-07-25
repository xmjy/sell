package com.university.service.impl;

import com.university.dto.OrderDTO;
import com.university.service.OrderService;
import com.university.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1554183098648681144");
        payService.create(orderDTO);
    }
    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1554805272929407971");
        payService.refund(orderDTO);
    }
}