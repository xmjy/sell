package com.university.service;

import com.university.dataobject.SellerInfo;

/**
 * @author 林就远
 * @create 2019-03-25 20:26
 **/
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
