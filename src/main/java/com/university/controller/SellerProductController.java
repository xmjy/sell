package com.university.controller;

import com.lly835.bestpay.rest.type.Get;
import com.university.dataobject.ProductCategory;
import com.university.dataobject.ProductInfo;
import com.university.exception.SellException;
import com.university.form.ProductForm;
import com.university.service.CategoryService;
import com.university.service.ProductService;
import com.university.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author 方翔鸣
 * @create 2019-03-22 13:15
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
//        PageRequest pageRequest = new PageRequest(page-1,size);
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<ProductInfo> productInfoPage = productService.findAll(pageable);
        map.put("productInfoPage",productInfoPage);
        map.put("url","/seller/product/list");
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/list",map);
    }

    /**
     * 上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam String productId,
                               Map<String,Object> map){
        try{
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","成功上架该商品");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 下架
     * @param productId
     * @param map
     * @return
     */

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam String productId,
                               Map<String,Object> map){
        try{
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg","商品id不正确");
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","已下架该商品");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 新增商品首页，以及修改商品，根据是否传过来productId来判断修改、新增。
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);
    }

    /**
     * 提交修改商品和新增商品的提交保存按钮
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){

        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            //如果productId不为空，则表示修改，为空表示新增
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg","修改成功");
        return new ModelAndView("common/success",map);
    }

}
