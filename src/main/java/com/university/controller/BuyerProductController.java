package com.university.controller;

import com.university.VO.ProductInfoVO;
import com.university.VO.ProductVO;
import com.university.VO.ResultVO;
import com.university.dataobject.ProductCategory;
import com.university.dataobject.ProductInfo;
import com.university.service.CategoryService;
import com.university.service.ProductService;
import com.university.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * @auther 方翔鸣
 * @date 2019/2/23 10:20
 */
/* 返回的是json格式，所以用restcontroller注释 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
//    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目(一次性查询)
        //List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        /*for(ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法
        List<Integer> categoryTypeList =  productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
