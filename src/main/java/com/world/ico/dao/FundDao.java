package com.world.ico.dao;

import com.world.ico.entity.FundPo;
import com.world.ico.entity.FundPricePo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lsb on 2018/11/1.
 */
public interface FundDao extends PagingAndSortingRepository<FundPo, Integer> {


    @Query(value = "SELECT fund_id,fund_name,fund_manage_id,fund_type,fund_price,DATE_FORMAT(fund_start_date,'%Y-%m-%d') fund_start_date FROM FUND where fund_id=:fundId", nativeQuery = true)
    FundPo getFundInfo(@Param("fundId") Integer fundId);

}
