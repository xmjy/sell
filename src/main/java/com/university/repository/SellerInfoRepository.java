package com.university.repository;

import com.university.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 方翔鸣
 * @create 2019-03-25 17:39
 **/
@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
