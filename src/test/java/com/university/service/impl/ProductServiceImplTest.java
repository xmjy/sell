package com.university.service.impl;

import com.university.dataobject.ProductInfo;
import com.university.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception{
        ProductInfo productInfo = productService.findOne("abc");
        Assert.assertEquals("abc",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception{
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        /* 看看第0页有几个元素 */
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception{
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("SaltedDuckEgg");
        productInfo.setProductName("咸鸭蛋");
        productInfo.setProductPrice(new BigDecimal(0.01));
        productInfo.setProductStock(66);
        productInfo.setProductDescription("次商品，多点不给");
        productInfo.setProductIcon("https://fuss10.elemecdn.com/a/6f/0c0d4dd29b5b455b9a4c0f71ee59cjpeg.jpeg?imageMogr2/thumbnail/100x100/format/webp/quality/85");
        productInfo.setProductStatus(ProductStatusEnum.down.getCode());
        productInfo.setCategoryType(1);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() throws Exception{
        ProductInfo result = productService.onSale("abc");
        //Assert.assertTrue("没成功",productService);
        Assert.assertEquals(ProductStatusEnum.up.getCode(),result.getProductStatus());
    }

    @Test
    public void offSale(){
        ProductInfo result = productService.offSale("bcd");
        //Assert.assertTrue("没成功",productService);
        Assert.assertEquals(ProductStatusEnum.down.getCode(),result.getProductStatus());
    }
}