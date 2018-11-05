package com.world.ico.service;

import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.entity.UserWalletPo;

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

    public void buyFund(Integer userId,BigDecimal traderMoney,Integer fundId);

    public void sellFund(Integer userId,BigDecimal fundCount,Integer fundId);

    public ArrayList<FundTransaction> getFundHistory(Integer userId,Integer page1,Integer page2);

    public Integer getFundHistoryCount(Integer userId);

    public ArrayList<FundTransaction> getDailySellFundTransaction(Integer userId);

    public ArrayList<FundTransaction> getDailyBuyFundTransaction(Integer userId);

    public void revokeFundTransaction(Integer transactionId,String type);

    public BigDecimal totalMoney(Integer userId,String type);

    public ArrayList<UserWalletPo> getFundsDetails(Integer userId);

    public void sellMoney(Integer userId,String type ,BigDecimal money);


}
