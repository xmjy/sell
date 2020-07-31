package com.university.repository;

import com.university.dataobject.SellerInfo;
import com.university.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("方翔鸣");
        sellerInfo.setPassword("xm970829");
        sellerInfo.setOpenid("o9AREv8WgDU5-_l-qbwjn0b8bVfw");

        SellerInfo result = repository.save(sellerInfo);
        //Assert.assertNotNull(result);
    }


    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = repository.findByOpenid("abc");
        Assert.assertNotNull("xm970829",sellerInfo.getOpenid());
    }

}