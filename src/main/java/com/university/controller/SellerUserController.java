package com.university.controller;

import com.university.config.ProjectUrlConfig;
import com.university.constant.CookieConstant;
import com.university.constant.RedisConstant;
import com.university.dataobject.SellerInfo;
import com.university.enums.ResultEnum;
import com.university.service.SellerService;
import com.university.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 方翔鸣
 * @create 2019-03-26 16:05
 **/
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){

        //1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null){
            map.put("msg",ResultEnum.LOGIN_FAIL.getMessage());
            //map.put("url","/sell/seller/order/list");
            map.put("url","https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http://sell.springboot.cn/sell/qr/oTgZpwS95v0zDjfgJ4HXBIVOZ0BE&response_type=code&scope=snsapi_login&state=http://university.natapp1.cc/sell/wechat/qrUserInfo");
            return new ModelAndView("common/error",map);
        }
        //2.设置token到redis里
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;//设置过期时间
        //key:String.format(RedisConstant.TOKEN_PREFIX,token),value ,过期时间，过期时间的单位
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX ,token),openid,expire, TimeUnit.SECONDS);
        //3.设置token到cookie
        //往response里面写cookie值
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);
        /* 跳转到订单页面，跳转最好用绝对路径，不推荐用相对路径*/
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){

        //1.从cookie里查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX ,cookie.getValue()));

            //3.清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        //map.put("url","/sell/seller/order/list");
        map.put("url","https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http://sell.springboot.cn/sell/qr/oTgZpwS95v0zDjfgJ4HXBIVOZ0BE&response_type=code&scope=snsapi_login&state=http://university.natapp1.cc/sell/wechat/qrUserInfo");
        return new ModelAndView("common/success",map);
    }

}
