package com.university.service.impl;

import com.university.dataobject.ProductCategory;
import com.university.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception{
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception{
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory1 = new ProductCategory("美味套餐", 4);
        ProductCategory result1 = categoryService.save(productCategory1);
        ProductCategory productCategory2 = new ProductCategory("人气开胃套餐", 5);
        ProductCategory result2 = categoryService.save(productCategory2);
        ProductCategory productCategory3 = new ProductCategory("养生甜粥品类", 6);
        ProductCategory result3= categoryService.save(productCategory3);
        ProductCategory productCategory4 = new ProductCategory("营养咸粥品类", 7);
        ProductCategory result4 = categoryService.save(productCategory4);
        Assert.assertNotNull(result1);
    }
}