package com.world.ico.dao;

import com.world.ico.entity.FundPricePo;
import com.world.ico.entity.UserPo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundDao extends PagingAndSortingRepository<FundPricePo, Integer> {


    @Query(value = "select s.fund_id,s.today_price,s.total_money,s.today_inmoney,s.today_outmoney,DATE_FORMAT(s.`date`,'%Y-%m-%d')  from `FUND_PRICE` s where DATE_FORMAT(s.`date`,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') order by s.fund_id", nativeQuery = true)
    List<Object[]> getFundDailyPrice();

    @Query(value = "select s.fund_id,s.today_price,s.total_money,s.today_inmoney,s.today_outmoney,DATE_FORMAT(s.`date`,'%Y-%m-%d') from `FUND_PRICE` s where s.fund_id=:fund_id  order by s.date desc", nativeQuery = true)
    List<Object[]> getFundInfo(@Param("fund_id") Integer fundId);

    @Query(value = "select s.fund_id,s.today_price,DATE_FORMAT(s.`date`,'%Y-%m-%d') from `FUND_PRICE` s where s.fund_id=:fund_id  order by s.date desc", nativeQuery = true)
    List<Object[]> getFundNarrowInfo(@Param("fund_id") Integer fundId);



}
