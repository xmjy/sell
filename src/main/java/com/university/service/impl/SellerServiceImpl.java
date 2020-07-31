package com.university.service.impl;

import com.university.dataobject.SellerInfo;
import com.university.repository.SellerInfoRepository;
import com.university.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 方翔鸣
 * @create 2019-03-25 20:27
 **/
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
