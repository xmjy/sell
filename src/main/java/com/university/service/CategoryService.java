package com.university.service;

import com.university.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * @auther 方翔鸣
 * @date 2019/2/16 15:58
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
