package com.university.controller;

import com.lly835.bestpay.model.PayResponse;
import com.university.dto.OrderDTO;
import com.university.enums.ResultEnum;
import com.university.exception.SellException;
import com.university.service.OrderService;
import com.university.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Map;

/**
 * @author 方翔鸣
 * @create 2019-03-11 11:27
 **/
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView pay(@RequestParam("orderId") String orderId,
                            @RequestParam("returnUrl") String returnUrl,
                            Map<String,Object> map){


        //1:查找订单，如果不存在则抛出一个异常
//        orderId = "oTgZpwS95v0zDjfgJ4HXBIVOZ0BE";
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2:发起支付
        PayResponse payResponse = payService.create(orderDTO);


        map.put("payResponse",payResponse);
        map.put("returnUrl", URLDecoder.decode(returnUrl));
        return new ModelAndView("pay/create",map);


    }

    /*
        微信异步通知
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
