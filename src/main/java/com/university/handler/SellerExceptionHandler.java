package com.university.handler;

import com.university.VO.ResultVO;
import com.university.config.ProjectUrlConfig;
import com.university.exception.ResponseBankException;
import com.university.exception.SellException;
import com.university.exception.SellerAuthorizeException;
import com.university.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 林就远
 * @create 2019-03-27 11:20
 **/
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                +"https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=wx6ad144e54af67d87&redirect_uri=" +
                "http://sell.springboot.cn/sell/qr/" +
                "oTgZpwWLWbM9BMBHeIPFqpWMmvWQ&response_type=code&" +
                "scope=snsapi_login&state=" +
                "http://nana.natapp1.cc/sell/wechat/qrUserInfo");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }

}
