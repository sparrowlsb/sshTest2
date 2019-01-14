package com.world.ico.dao;

import com.world.ico.entity.FundTransactionPo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundTransactionDao  extends PagingAndSortingRepository<FundTransactionPo, Integer> {
    @Modifying
    @Query(value = "INSERT INTO FUND_TRANSACTION (user_id,type,money,status,trader_money,fund_id,fund_price,fund_count,management_fee,management_cost) values (:userId,:type,:traderMoney-:managementCost,:status,:traderMoney,:fundId,:fundPrice,:fundCount,:managementFee,:managementCost)", nativeQuery = true)
    void insertFundTransaction(@Param("userId") Integer userId, @Param("type") String type, @Param("status") Integer status, @Param("traderMoney") BigDecimal traderMoney, @Param("fundId") Integer fundId, @Param("fundPrice") BigDecimal fundPrice, @Param("fundCount") BigDecimal fundCount, @Param("managementFee") BigDecimal managementFee, @Param("managementCost") BigDecimal managementCost);

   @Query(value = "SELECT id,fund_price,trader_money,management_fee,user_id,type,status,money,fund_count,fund_id,management_cost,transaction_date,buy_date  from FUND_TRANSACTION where user_id=:userId order by  transaction_date desc limit :page1,:page2", nativeQuery = true)
    ArrayList<FundTransactionPo> findHistFundTransaction(@Param("userId") Integer userId,@Param("page1") Integer page1,@Param("page2") Integer page2);

    @Query(value = "SELECT id,fund_price,trader_money,management_fee,user_id,type,status,money,fund_count,fund_id,management_cost,transaction_date,buy_date  from FUND_TRANSACTION where user_id=:userId and status=1 and fund_id=:fundId group by DATE_FORMAT(transaction_date,'%Y-%m-%d'),status order by  transaction_date desc", nativeQuery = true)
    ArrayList<FundTransactionPo> findSuccessHistFundTransaction(@Param("userId") Integer userId,@Param("fundId") Integer fundId);

    @Query(value = "SELECT count(*)  from FUND_TRANSACTION where user_id=:userId order by  transaction_date desc", nativeQuery = true)
    Integer findHistFundTransactionCount(@Param("userId") Integer userId);


    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date,buy_date,fund_count,fund_price,management_fee  from FUND_TRANSACTION where id=:fundOrderId and type=:type and status =:status ", nativeQuery = true)
    FundTransactionPo findRevokeFundTransactionInfo(@Param("fundOrderId") Integer fundOrderId,@Param("type") String type,@Param("status") Integer status);

    @Modifying
    @Query(value = "UPDATE  FUND_TRANSACTION t set t.status = -1 where id=:id", nativeQuery = true)
    void updateFundTransactionStatus(@Param("id") Integer id);

    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date,buy_date,fund_count,fund_price,management_fee  from FUND_TRANSACTION where user_id=:userId and type='BUY' and status =0  order by transaction_date desc", nativeQuery = true)
    ArrayList<FundTransactionPo> findDailyBuyFundTransaction(@Param("userId") Integer userId);


    @Query(value = "SELECT id,user_id,type,status,trader_money,fund_id,management_cost,transaction_date,buy_date,fund_count,fund_price,management_fee  from FUND_TRANSACTION where user_id=:userId and type='SELL' and status =0 order by transaction_date desc", nativeQuery = true)
    ArrayList<FundTransactionPo> findDailySellFundTransaction(@Param("userId") Integer userId);


    @Query(value = "SELECT count(*)  from FUND_TRANSACTION where user_id=:userId and type='BUY' and status =1", nativeQuery = true)
    Integer haveFundTransactionRecord(@Param("userId") Integer userId);

}
