package com.university.repository;

import com.university.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @auther 方翔鸣
 * @date 2019/2/23 18:35
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {


    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}

