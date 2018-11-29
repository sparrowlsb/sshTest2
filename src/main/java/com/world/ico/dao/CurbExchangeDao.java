package com.world.ico.dao;

import com.world.ico.entity.CurbExchangePo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by lsb on 2018/11/29.
 */
public interface CurbExchangeDao extends PagingAndSortingRepository<CurbExchangePo, Integer> {

    @Modifying
    @Query(value = "Insert into CURB_EXCHANGE(user_id,exchange_platform,exchange_type,type,money,status) values(:user_id,:exchange_platform,:exchange_type,:type,:money,:status)", nativeQuery = true)
    void insertExchangeHist(@Param("user_id") Integer user_id, @Param("exchange_platform") String exchangePlatform, @Param("exchange_type") String exchange_type, @Param("type") String type, @Param("money") BigDecimal money, @Param("status") Integer status);

    @Query(value = "select id,user_id,exchange_date,exchange_platform,exchange_type,type,money,status from CURB_EXCHANGE where user_id=:user_id order by id desc limit :page1,:page2 ", nativeQuery = true)
    ArrayList<CurbExchangePo> getExchangeHist(@Param("user_id") Integer user_id,@Param("page1") Integer page1,@Param("page2") Integer page2);

    @Query(value = "select count(*) from CURB_EXCHANGE where user_id=:user_id ", nativeQuery = true)
    Integer getExchangeHistCount(@Param("user_id") Integer user_id);


}
