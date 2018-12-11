package com.world.ico.service;

import com.world.ico.dto.CurbExchange;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.dto.UserWallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lsb on 2018/10/8.
 */
public interface FundService {

    public List<FundPrice> getFundDailyPrice();

    public HashMap<Integer,ArrayList<FundPrice>> getFundNarrowInfo();

    public List<FundPrice> getFundInfo(Integer fundId);

    public Integer buyFund(Integer userId, BigDecimal traderMoney, Integer fundId);

    public Integer sellFund(Integer userId, BigDecimal fundCount, Integer fundId);

    public ArrayList<FundTransaction> getFundHistory(Integer userId,Integer page1,Integer page2);

    public ArrayList<FundTransaction> getSuccessFundHistory(Integer userId,Integer fundId);

    public Integer getFundHistoryCount(Integer userId);

    public ArrayList<FundTransaction> getDailySellFundTransaction(Integer userId);

    public ArrayList<FundTransaction> getDailyBuyFundTransaction(Integer userId);

    public void revokeFundTransaction(Integer transactionId,String type);

    public BigDecimal totalMoney(Integer userId,String type);

    public ArrayList<UserWallet> getFundsDetails(Integer userId);

    public void sellMoney(Integer userId,String type ,BigDecimal money,BigDecimal count);

    public void buyMoney(Integer userId,String type ,BigDecimal money,BigDecimal count);

    public ArrayList<CurbExchange> getTransactionHist(Integer userId,Integer page1,Integer page2);

    public Integer getTransactionHistoryCount(Integer userId);

}
