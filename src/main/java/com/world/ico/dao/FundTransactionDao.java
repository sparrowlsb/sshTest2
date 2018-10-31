package com.world.ico.dao;

import com.world.ico.entity.FundTransactionPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundTransactionDao  extends PagingAndSortingRepository<FundTransactionPo, Integer> {
    @Modifying
    @Query(value = "INSERT INTO FUND_TRANSACTION (user_id,type,status,trader_money,fund_id,fund_price,fund_count,management_fee,management_cost) values (:userId,:type,:status,:traderMoney,:fundId,:fundPrice,:fundCount,:managementFee,:managementCost)", nativeQuery = true)
    void insertFundTransaction(@Param("userId") Integer userId, @Param("type") String type, @Param("status") Integer status, @Param("traderMoney") Double traderMoney, @Param("fundId") Integer fundId, @Param("fundPrice") Double fundPrice, @Param("fundCount") Double fundCount, @Param("managementFee") Double managementFee, @Param("managementCost") Double managementCost);


    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date  from FUND_TRANSACTION where user_id=:userId and type='BUY' and(status=1 or status =0 )", nativeQuery = true)
    ArrayList<FundTransactionPo> findBuyFundTransaction(@Param("userId") Integer userId);


    @Query(value = "SELECT id,user_id,type,status,fund_count,fund_id,management_cost,transaction_date  from FUND_TRANSACTION where user_id=:userId and type='SELL' and(status=1 or status =0 )", nativeQuery = true)
    ArrayList<FundTransactionPo> findSellFundTransaction(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "UPDATE  FUND_TRANSACTION t set t.status = -1 where id=:id", nativeQuery = true)
    void updateFundTransactionStatus(@Param("id") Integer id);

    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date,fund_count,fund_price,management_fee  from FUND_TRANSACTION where user_id=:userId and type='BUY' and status =0  ", nativeQuery = true)
    ArrayList<FundTransactionPo> findDailyBuyFundTransaction(@Param("userId") Integer userId);


    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date  from FUND_TRANSACTION where user_id=:userId and type='SELL' and status =0 ", nativeQuery = true)
    ArrayList<FundTransactionPo> findDailySellFundTransaction(@Param("userId") Integer userId);

}
