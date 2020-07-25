package com.university.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 林就远
 * @create 2019-03-07 18:33
 **/
@RestController
@Slf4j
@RequestMapping("/weixin")
public class WinxinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入了微信");
        log.info("code = {}",code);

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc81905d0adc5a59f&secret=dd81bc78d95c9e116cffe38282fde3d8&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response = {}",response);
    }

}
