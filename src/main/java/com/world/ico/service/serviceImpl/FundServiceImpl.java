package com.world.ico.service.serviceImpl;

import com.world.ico.dao.*;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.entity.FundPo;
import com.world.ico.entity.FundPricePo;
import com.world.ico.entity.FundTransactionPo;
import com.world.ico.entity.UserWalletPo;
import com.world.ico.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lsb on 2018/10/8.
 */
@Transactional
@Service
public class FundServiceImpl implements FundService {
    @Autowired
    public FundPriceDao fundPriceDao;

    @Autowired
    public FundDao fundDao;

    @Autowired
    public WalletDao walletDao;

    @Autowired
    public ManagementFeeDao managementFeeDao;

    @Autowired
    public FundTransactionDao fundTransactionDao;


    @Override
    public List<FundPrice> getFundDailyPrice() {
        ArrayList<FundPricePo> fundPriceList= (ArrayList<FundPricePo>) fundPriceDao.getFundDailyPrice();
        System.out.println(fundPriceList);
        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(FundPricePo fundPricePo:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId(fundPricePo.getFundId());
            fundPrice.setFundPrice(fundPricePo.getTodayPrice());
            fundPrice.setFundTotalMoney(fundPricePo.getTotalMoney());
            fundPrice.setFundInMoney(fundPricePo.getTodayInmoney());
            fundPrice.setFundOutMoney(fundPricePo.getTodayOutmoney());
            fundPrice.setFundDate(String.valueOf(fundPricePo.getDate()));

            FundPo fundPo=fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundPrice.setFundName(fundName);
            }
            fundPriceArrayList.add(fundPrice);
        }
        System.out.println(fundPriceArrayList.size());
        System.out.println(fundPriceArrayList);
        return fundPriceArrayList;
    }

    @Override
    public HashMap<Integer, ArrayList<FundPrice>> getFundNarrowInfo() {
        HashMap<Integer,ArrayList<FundPrice>> fundPriceHashMap=new HashMap<>();
        ArrayList<Object[]> fundPriceList1= (ArrayList<Object[]>) fundPriceDao.getFundNarrowInfo(1);
        ArrayList<Object[]> fundPriceList2= (ArrayList<Object[]>) fundPriceDao.getFundNarrowInfo(2);
        ArrayList<Object[]> fundPriceList3= (ArrayList<Object[]>) fundPriceDao.getFundNarrowInfo(3);

        ArrayList<FundPrice> fundPriceArrayList1=new ArrayList<>();
        for(Object[] object:fundPriceList1){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((BigDecimal) object[1]);

            fundPrice.setFundDate((String) object[2]);
            fundPriceArrayList1.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList2=new ArrayList<>();
        for(Object[] object:fundPriceList2){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((BigDecimal) object[1]);

            fundPrice.setFundDate((String) object[2]);
            fundPriceArrayList2.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList3=new ArrayList<>();
        for(Object[] object:fundPriceList3){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((BigDecimal) object[1]);

            fundPrice.setFundDate((String) object[2]);
            fundPriceArrayList3.add(fundPrice);
        }
        fundPriceHashMap.put(1,fundPriceArrayList1);
        fundPriceHashMap.put(2,fundPriceArrayList2);
        fundPriceHashMap.put(3,fundPriceArrayList3);
        System.out.println(fundPriceHashMap);

        return fundPriceHashMap;
    }

    @Override
    public List<FundPrice> getFundInfo(Integer fundId) {

        ArrayList<Object[]> fundPriceList= (ArrayList<Object[]>) fundPriceDao.getFundInfo(fundId);


        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(Object[] object:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((BigDecimal) object[1]);
            fundPrice.setFundTotalMoney((BigDecimal) object[2]);
            fundPrice.setFundInMoney((BigDecimal) object[3]);
            fundPrice.setFundOutMoney((BigDecimal) object[4]);
            fundPrice.setFundDate((String) object[5]);
            fundPriceArrayList.add(fundPrice);
        }
        System.out.println(fundPriceArrayList);

        return fundPriceArrayList;
    }

    @Override
    public void buyFund(Integer userId, BigDecimal traderMoney, Integer fundId) {

        BigDecimal managementFee= managementFeeDao.getManagementFee();
        BigDecimal managementCost=managementFee.multiply(traderMoney);
        BigDecimal fundPrice =BigDecimal.valueOf(-1);
        BigDecimal fundCount =BigDecimal.valueOf(-1);

        BigDecimal totalMoney=walletDao.totalCount(userId,"RMB");
        if(totalMoney.compareTo(traderMoney)==1){
            synchronized (this){
                walletDao.sellCount(userId,"RMB",traderMoney);
                walletDao.updateMoney(userId,"FUND_"+fundId,traderMoney);
                fundTransactionDao.insertFundTransaction(userId,"BUY",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
            }
        }

        //整点时间定时更新insertFundTransaction 和walletDao中fund_id的price 和count；
        //以及walletDao扣除手续费之后的user_id下的tradermoney=今日price*fundcount
    }

    @Override
    public void sellFund(Integer userId, BigDecimal fundCount, Integer fundId) {
        BigDecimal managementFee = managementFeeDao.getManagementFee();
        BigDecimal totalCount = walletDao.totalCount(userId, "FUND_" + fundId);

        BigDecimal traderMoney = BigDecimal.valueOf(-1);
        BigDecimal fundPrice = BigDecimal.valueOf(-1);
        BigDecimal managementCost = BigDecimal.valueOf(-1);

        if(totalCount.compareTo(fundCount)==1){

            synchronized (this){
                fundTransactionDao.insertFundTransaction(userId,"SELL",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
            }
        }
    }

        @Override
    public ArrayList<FundTransaction> getFundHistory(Integer userId,Integer page1,Integer page2) {
        ArrayList<FundTransactionPo> fundTransactionPos=fundTransactionDao.findHistFundTransaction(userId,page1,page2);
        ArrayList<FundTransaction>fundTransactions=new ArrayList<>();
        for (FundTransactionPo f :fundTransactionPos){
            FundTransaction fundTransaction=new FundTransaction();
            fundTransaction.setId(f.getId());
            fundTransaction.setUserId(f.getUserId());
            fundTransaction.setType(f.getType());
            fundTransaction.setStatus(f.getStatus());
            fundTransaction.setFundCount(f.getFundCount());
            fundTransaction.setFundId(f.getFundId());
            fundTransaction.setManagementCost(f.getManagementCost());
            fundTransaction.setTransactionDate(f.getTransactionDate());
            fundTransactions.add(fundTransaction);

        }

        return fundTransactions;
    }

    @Override
    public Integer getFundHistoryCount(Integer userId) {
        Integer fundTransactionCount=fundTransactionDao.findHistFundTransactionCount(userId);


        return fundTransactionCount;
    }

    @Override
    public ArrayList<FundTransaction> getDailySellFundTransaction(Integer userId) {
        ArrayList<FundTransactionPo> fundTransactionPos=fundTransactionDao.findDailySellFundTransaction(userId);
        ArrayList<FundTransaction>fundTransactions=new ArrayList<>();
        for (FundTransactionPo f :fundTransactionPos){
            FundTransaction fundTransaction=new FundTransaction();
            fundTransaction.setId(f.getId());
            fundTransaction.setUserId(f.getUserId());
            fundTransaction.setType(f.getType());
            fundTransaction.setStatus(f.getStatus());
            fundTransaction.setTraderMoney(f.getTraderMoney());
            fundTransaction.setFundId(f.getFundId());
            fundTransaction.setManagementCost(f.getManagementCost());
            fundTransaction.setTransactionDate(f.getTransactionDate());
            fundTransactions.add(fundTransaction);

        }

        return fundTransactions;
    }



    @Override
    public ArrayList<FundTransaction> getDailyBuyFundTransaction(Integer userId) {
        System.out.print("userId"+userId);
        ArrayList<FundTransactionPo> fundTransactionPos=fundTransactionDao.findDailyBuyFundTransaction(userId);
        ArrayList<FundTransaction>fundTransactions=new ArrayList<>();
        for (FundTransactionPo f :fundTransactionPos){
            FundTransaction fundTransaction=new FundTransaction();
            fundTransaction.setId(f.getId());
            fundTransaction.setUserId(f.getUserId());
            fundTransaction.setType(f.getType());
            fundTransaction.setStatus(f.getStatus());
            fundTransaction.setTraderMoney(f.getTraderMoney());
            fundTransaction.setFundId(f.getFundId());
            fundTransaction.setManagementCost(f.getManagementCost());
            fundTransaction.setTransactionDate(f.getTransactionDate());
            fundTransactions.add(fundTransaction);

        }

        return fundTransactions;
    }

    @Override
    public void revokeFundTransaction(Integer transactionId,String type) {
        synchronized (this) {
            FundTransactionPo fundTransactionPo = fundTransactionDao.findRevokeFundTransactionInfo(transactionId, type, 0);

            if(fundTransactionPo.getType()=="BUY") {
                walletDao.revokeMoney(fundTransactionPo.getUserId(), "RMB", fundTransactionPo.getTraderMoney());

                walletDao.revokeFund(fundTransactionPo.getUserId(), "FUND_" + fundTransactionPo.getFundId(), fundTransactionPo.getTraderMoney());
            }
            fundTransactionDao.updateFundTransactionStatus(transactionId);

        }
    }



    @Override
    public BigDecimal totalMoney(Integer userId, String type) {

        return walletDao.totalCount(userId,type);
    }

    @Override
    public ArrayList<UserWalletPo> getFundsDetails(Integer userId) {
        return walletDao.getFundsDetails(userId);
    }

    @Override
    public void sellMoney(Integer userId,String type ,BigDecimal money) {

        walletDao.sellCount(userId,type,money);
        walletDao.exchangeHist(userId,type,"RMB",money,0);
    }
}
