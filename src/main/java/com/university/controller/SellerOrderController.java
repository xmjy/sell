package com.university.controller;

import com.university.dataobject.OrderMaster;
import com.university.dto.OrderDTO;
import com.university.enums.ResultEnum;
import com.university.exception.SellException;
import com.university.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 林就远
 * @create 2019-03-21 13:56
 **/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /***
     * 订单列表
     * @param page 第几页，从第一页开始
     * @param size 一页有多少数据
     * @param map 把数据传送给前台页面
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               @RequestParam("page") Integer page,
                               @RequestParam("size") Integer size,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 查询不到订单");
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.CANCEL_ORDER_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list?page="+page+"&size="+size);
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               @RequestParam("page") Integer page,
                               @RequestParam("size") Integer size,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);

        }catch (SellException e){
            log.error("【卖家端详情】 该订单没有详情");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("orderDTO",orderDTO);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/detail",map);

    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               @RequestParam("page") Integer page,
                               @RequestParam("size") Integer size,
                               Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端详情】 该订单没有详情");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg", ResultEnum.FINISH_ORDER_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list?page="+page+"&size="+size);
        return new ModelAndView("common/success",map);

    }

}
