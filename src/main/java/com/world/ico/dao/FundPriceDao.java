package com.world.ico.dao;

import com.world.ico.entity.FundPricePo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundPriceDao extends PagingAndSortingRepository<FundPricePo, Integer> {


    @Query(value = "select s.id,s.fund_id,s.today_price,s.total_money,s.today_inmoney,s.today_outmoney,DATE_FORMAT(s.`date`,'%Y-%m-%d') date  from `FUND_PRICE` s where DATE_FORMAT(s.`date`,'%Y-%m-%d') in (select x.d from (select distinct(DATE_FORMAT(`date`,'%Y-%m-%d')) d from `FUND_PRICE` order by DATE_FORMAT(`date`,'%Y-%m-%d') desc limit 0,2) as x) order by date desc", nativeQuery = true)
    ArrayList<FundPricePo> getFundDailyPrice();


    @Query(value = "select s.id,s.fund_id,s.today_price,s.total_money,s.today_inmoney,s.today_outmoney,DATE_FORMAT(s.`date`,'%Y-%m-%d') date from `FUND_PRICE` s where s.fund_id=:fund_id  order by s.date asc", nativeQuery = true)
    List<FundPricePo> getFundInfo(@Param("fund_id") Integer fundId);

    @Query(value = "select s.id,s.fund_id,s.today_price,s.total_money,s.today_inmoney,s.today_outmoney,DATE_FORMAT(s.`date`,'%Y-%m-%d') date from `FUND_PRICE` s where s.fund_id=:fund_id  order by s.date asc", nativeQuery = true)
    List<FundPricePo> getFundNarrowInfo(@Param("fund_id") Integer fundId);



}
