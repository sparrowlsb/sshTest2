package com.world.ico.service;

import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;

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

    public void buyFund(Integer userId,Double traderMoney,Integer fundId);

    public void sellFund(Integer userId,Double fundCount,Integer fundId);

    public ArrayList<FundTransaction> getSellFundHistory(Integer userId);

    public ArrayList<FundTransaction> getDailySellFundTransaction(Integer userId);

    public ArrayList<FundTransaction> getBuyFundHistory(Integer userId);

    public ArrayList<FundTransaction> getDailyBuyFundTransaction(Integer userId);

    public void revokeFundTransaction(Integer transactionId);

    public Double totalMoney(Integer userId,String type);

    public void sellMoney(Integer userId,String type ,Double money);


}
