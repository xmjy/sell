package com.university.controller;

import com.university.VO.ResultVO;
import com.university.converter.OrderForm2OrderDTOConverter;
import com.university.dto.OrderDTO;
import com.university.enums.ResultEnum;
import com.university.exception.SellException;
import com.university.form.OrderForm;
import com.university.service.BuyerService;
import com.university.service.OrderService;
import com.university.service.PayService;
import com.university.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 方翔鸣
 * @date 2019/2/26 13:44
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);

    }

    //订单列表
    @PostMapping("/list")
    public ResultVO<List<OrderDTO>>  list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultVOUtil.success(orderDTOPage.getContent());

    }
    //订单详情（查看单个订单）
    @GetMapping("/detail")
    public ResultVO<Order> detail(@RequestParam("openid") String openid,
                                  @RequestParam("orderId") String orderId){
        //TODO 不安全的做法，待改进（已改进）
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);

    }

    //买家端取消订单，并且完成退款。
//    @GetMapping("cancel")
    @PostMapping("cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        //TODO 不安全的做法，待改进（已改进）
        /* 取消订单*/
        buyerService.cancelOrder(openid,orderId);

        /* 退款： 这里做一个判断，要是订单的支付状态是成功支付的话，才可以执行退款功能 */
        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO.getPayStatus() == 1){
            payService.refund(orderDTO);
//        }

        return ResultVOUtil.success();
    }
}
