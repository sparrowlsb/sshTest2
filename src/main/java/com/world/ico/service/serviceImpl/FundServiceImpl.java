package com.world.ico.service.serviceImpl;

import com.world.ico.dao.FundDao;
import com.world.ico.dao.FundTransactionDao;
import com.world.ico.dao.ManagementFeeDao;
import com.world.ico.dao.WalletDao;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.entity.FundTransactionPo;
import com.world.ico.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public FundDao fundDao;

    @Autowired
    public WalletDao walletDao;

    @Autowired
    public ManagementFeeDao managementFeeDao;

    @Autowired
    public FundTransactionDao fundTransactionDao;

    @Override
    public List<FundPrice> getFundDailyPrice() {
        ArrayList<Object[]> fundPriceList= (ArrayList<Object[]>) fundDao.getFundDailyPrice();
        System.out.println(fundPriceList);
        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(Object[] object:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((Double) object[1]);
            fundPrice.setFundTotalMoney((Double) object[2]);
            fundPrice.setFundDate((String) object[5]);
            fundPriceArrayList.add(fundPrice);
        }

        System.out.println(fundPriceArrayList);
        return fundPriceArrayList;
    }

    @Override
    public HashMap<Integer, ArrayList<FundPrice>> getFundNarrowInfo() {
        HashMap<Integer,ArrayList<FundPrice>> fundPriceHashMap=new HashMap<>();
        ArrayList<Object[]> fundPriceList1= (ArrayList<Object[]>) fundDao.getFundNarrowInfo(1);
        ArrayList<Object[]> fundPriceList2= (ArrayList<Object[]>) fundDao.getFundNarrowInfo(2);
        ArrayList<Object[]> fundPriceList3= (ArrayList<Object[]>) fundDao.getFundNarrowInfo(3);

        ArrayList<FundPrice> fundPriceArrayList1=new ArrayList<>();
        for(Object[] object:fundPriceList1){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((Double) object[1]);

            fundPrice.setFundDate((String) object[2]);
            fundPriceArrayList1.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList2=new ArrayList<>();
        for(Object[] object:fundPriceList2){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((Double) object[1]);

            fundPrice.setFundDate((String) object[2]);
            fundPriceArrayList2.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList3=new ArrayList<>();
        for(Object[] object:fundPriceList3){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((Double) object[1]);

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

        ArrayList<Object[]> fundPriceList= (ArrayList<Object[]>) fundDao.getFundInfo(fundId);


        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(Object[] object:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId((Integer) object[0]);
            fundPrice.setFundPrice((Double) object[1]);
            fundPrice.setFundTotalMoney((Double) object[2]);
            fundPrice.setFundInMoney((Double) object[3]);
            fundPrice.setFundOutMoney((Double) object[4]);
            fundPrice.setFundDate((String) object[5]);
            fundPriceArrayList.add(fundPrice);
        }
        System.out.println(fundPriceArrayList);

        return fundPriceArrayList;
    }

    @Override
    public void buyFund(Integer userId, Double traderMoney, Integer fundId) {

        Double managementFee= managementFeeDao.getManagementFee();
        Double managementCost=managementFee*traderMoney;
        Double fundPrice =Double.valueOf(-1);
        Double fundCount =Double.valueOf(-1);

        Double totalMoney=walletDao.totalCount(userId,"RMB");
        if(totalMoney-traderMoney>=0){
            synchronized (this){
                walletDao.sellCount(userId,"RMB",totalMoney-traderMoney);
                walletDao.updateMoney(userId,"FUND_"+fundId,traderMoney);
                fundTransactionDao.insertFundTransaction(userId,"BUY",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
            }
        }

        //整点时间定时更新insertFundTransaction 和walletDao中fund_id的price 和count；
        //以及walletDao扣除手续费之后的user_id下的tradermoney=今日price*fundcount
    }

    @Override
    public void sellFund(Integer userId, Double fundCount, Integer fundId) {
        Double managementFee = managementFeeDao.getManagementFee();
        Double totalCount = walletDao.totalCount(userId, "FUND_" + fundId);

        Double traderMoney = Double.valueOf(-1);
        Double fundPrice = Double.valueOf(-1);
        Double managementCost = Double.valueOf(-1);

        if(totalCount-fundCount>=0){
            synchronized (this){
                walletDao.sellCount(userId,"FUND_"+fundId,totalCount-fundCount);
                fundTransactionDao.insertFundTransaction(userId,"SELL",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
            }
        }
    }

        @Override
    public ArrayList<FundTransaction> getSellFundHistory(Integer userId) {
        ArrayList<FundTransactionPo> fundTransactionPos=fundTransactionDao.findSellFundTransaction(userId);
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
    public ArrayList<FundTransaction> getBuyFundHistory(Integer userId) {
        ArrayList<FundTransactionPo> fundTransactionPos=fundTransactionDao.findBuyFundTransaction(userId);
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
    public void revokeFundTransaction(Integer transactionId) {
        fundTransactionDao.updateFundTransactionStatus(transactionId);
    }



    @Override
    public Double totalMoney(Integer userId, String type) {

        return walletDao.totalCount(userId,type);
    }

    @Override
    public void sellMoney(Integer userId,String type ,Double money) {

        walletDao.sellCount(userId,type,money);
        walletDao.exchangeHist(userId,type,"RMB",money,0);
    }
}
