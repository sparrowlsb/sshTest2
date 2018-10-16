package com.world.ico.service;

import com.world.ico.dto.FundPrice;

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

    public void buyFund();

    public void sellFund();


    public Double totalMoney(Integer userId,String type);


    public void sellMoney(Integer userId,String type ,Double money);


}
