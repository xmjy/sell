package com.university.dataobject.mapper;

import com.university.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","peter love");
        map.put("category_type",101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByProductCategory() throws Exception{

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("方翔鸣");
        productCategory.setCategoryType(105);
        int result = mapper.insertByProductCategory(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = mapper.findByCategoryType(105);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> result = mapper.findByCategoryName("peter love");
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void updateByCategoryType(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("peter love1");
        productCategory.setCategoryType(102);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject(){
        int result = mapper.updateByCategoryType("今天是个好日子",102);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType(){
        int result = mapper.deleteByCategoryType(102);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(105);
        Assert.assertNotNull(productCategory);
    }
}