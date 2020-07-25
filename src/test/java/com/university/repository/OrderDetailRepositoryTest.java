package com.university.repository;

import com.university.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("zx");
        orderDetail.setOrderId("mao");
        orderDetail.setProductId("bad");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductName("毛果");
        orderDetail.setProductPrice(new BigDecimal(5));
        orderDetail.setProductQuantity(7);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByOrderId() {

        List<OrderDetail> orderDetailList = repository.findByOrderId("fxm");
        System.out.println(orderDetailList.size());
        Assert.assertNotEquals(0,orderDetailList.size());

    }
}