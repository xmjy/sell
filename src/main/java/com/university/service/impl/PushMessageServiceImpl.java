package com.university.service.impl;

import com.university.config.WechatAccountConfig;
import com.university.dto.OrderDTO;
import com.university.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息推送
 * @author 方翔鸣
 * @create 2019-03-27 16:32
 **/
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;


    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        //测试公众号的模板id
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus2"));
        //消息推送给谁,本人的测试公众号openid
        templateMessage.setToUser("oQ1Rf1b0Z6Fi4KWG08ghPxjR3W5s");
        //主要的数据
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货"),
                new WxMpTemplateData("keyword1","翔鸣美食店"),
                new WxMpTemplateData("keyword2","13979347929"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getBuyerName()),
                new WxMpTemplateData("keyword5",orderDTO.getBuyerPhone()),
                new WxMpTemplateData("keyword6",orderDTO.getBuyerAddress()),
                new WxMpTemplateData("keyword7",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword8","￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临")
        );
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}",e);
        }
    }
}
