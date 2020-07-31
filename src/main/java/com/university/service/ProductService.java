package com.university.service;

import com.university.dataobject.ProductInfo;
import com.university.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * @auther 方翔鸣
 * @date 2019/2/18 12:36
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /* 买家端查询所有在架商品列表 */
    List<ProductInfo> findUpAll();

    /* 卖家端查询所有商品，并且进行分页管理 */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //加月售
    void increaseSale(List<CartDTO> cartDTOList);

    //减月售
    void decreeseSale(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
