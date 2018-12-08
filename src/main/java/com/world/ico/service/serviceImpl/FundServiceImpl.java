package com.world.ico.service.serviceImpl;

import com.world.ico.dao.*;
import com.world.ico.dto.CurbExchange;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.dto.UserWallet;
import com.world.ico.entity.*;
import com.world.ico.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public CurbExchangeDao curbExchangeDao;

    @Autowired
    public WalletDao walletDao;

    @Autowired
    public ManagementFeeDao managementFeeDao;

    @Autowired
    public FundTransactionDao fundTransactionDao;


    @Override
    public List<FundPrice> getFundDailyPrice() {

        ArrayList<FundPricePo> fundPriceList = fundPriceDao.getFundDailyPrice();

        ArrayList<FundPrice> fundPriceArrayList = new ArrayList<>();

        HashMap<String, FundPricePo> map = new HashMap<>();
        HashSet<String> dateHash = new HashSet<>();
        HashSet<Integer> fundSize = new HashSet<>();
        Integer size=0;
        for (FundPricePo fundPricePo : fundPriceList) {
            map.put(fundPricePo.getFundId() + "_" + fundPricePo.getDate(), fundPricePo);
            dateHash.add(fundPricePo.getDate());
            fundSize.add(fundPricePo.getFundId());

        }
        size=fundSize.size();

        String maxDateString = null;
        String minDateString = null;
        if (dateHash.size() != 0) {
            try {
                Date maxDate = null;
                Date minDate = null;
                for (String date : dateHash) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    if (maxDate==null||minDate==null){
                        if (maxDate==null) {
                            maxDate = df.parse(date);
                            maxDateString = date;
                        }
                        if (minDate==null){
                            minDate = df.parse(date);
                            minDateString = date;
                        }
                    }else if (df.parse(date).getTime() > maxDate.getTime()) {
                        maxDate = df.parse(date);
                        maxDateString = date;
                    }else if (df.parse(date).getTime() < minDate.getTime()) {
                        minDate = df.parse(date);
                        minDateString = date;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for (int i = 1; i <= size; i++) {
            FundPricePo fundPricePo = map.get(i + "_" + maxDateString);
            FundPrice fundPrice = new FundPrice();
            fundPrice.setFundId(fundPricePo.getFundId());
            fundPrice.setFundPrice(fundPricePo.getTodayPrice());
            fundPrice.setFundTotalMoney(fundPricePo.getTotalMoney());
            fundPrice.setFundInMoney(fundPricePo.getTodayInmoney());
            fundPrice.setFundOutMoney(fundPricePo.getTodayOutmoney());
            fundPrice.setFundDate(String.valueOf(fundPricePo.getDate()));

            BigDecimal quoteChange = BigDecimal.valueOf(0);
            BigDecimal quoteHistChange = BigDecimal.valueOf(0);
            BigDecimal newFundPrice = fundPricePo.getTodayPrice();
            BigDecimal oldFundPrice = map.get(i + "_" + minDateString).getTodayPrice();

            if (oldFundPrice.compareTo(BigDecimal.valueOf(0)) == 1) {
                quoteChange = (newFundPrice.subtract(oldFundPrice).divide(oldFundPrice, 4, BigDecimal.ROUND_HALF_UP));

            }
            quoteHistChange = (newFundPrice.subtract(BigDecimal.valueOf(1)).divide(BigDecimal.valueOf(1), 4, BigDecimal.ROUND_HALF_UP));
            fundPrice.setFundQuoteChange(quoteChange.multiply(BigDecimal.valueOf(100)));
            fundPrice.setFundQuoteHistChange(quoteHistChange.multiply(BigDecimal.valueOf(100)));

            FundPo fundPo = fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo != null) {
                String fundName = fundPo.getFundName();
                String fundType = fundPo.getFundType();
                fundPrice.setFundName(fundName);
                fundPrice.setFundType(fundType);
            }
            fundPriceArrayList.add(fundPrice);
        }
        return fundPriceArrayList;
    }

    @Override
    public HashMap<Integer, ArrayList<FundPrice>> getFundNarrowInfo() {
        HashMap<Integer,ArrayList<FundPrice>> fundPriceHashMap=new HashMap<>();
        ArrayList<FundPricePo> fundPriceList1= (ArrayList<FundPricePo>) fundPriceDao.getFundNarrowInfo(1);
        ArrayList<FundPricePo> fundPriceList2= (ArrayList<FundPricePo>) fundPriceDao.getFundNarrowInfo(2);
        ArrayList<FundPricePo> fundPriceList3= (ArrayList<FundPricePo>) fundPriceDao.getFundNarrowInfo(3);

        ArrayList<FundPrice> fundPriceArrayList1=new ArrayList<>();
        for(FundPricePo fundPricePo:fundPriceList1){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId( fundPricePo.getFundId());
            fundPrice.setFundPrice( fundPricePo.getTodayPrice());
            FundPo fundPo=fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundPrice.setFundName(fundName);
            }
            fundPrice.setFundDate( fundPricePo.getDate());
            fundPriceArrayList1.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList2=new ArrayList<>();
        for(FundPricePo fundPricePo:fundPriceList2){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId( fundPricePo.getFundId());
            fundPrice.setFundPrice( fundPricePo.getTodayPrice());
            FundPo fundPo=fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundPrice.setFundName(fundName);
            }
            fundPrice.setFundDate( fundPricePo.getDate());
            fundPriceArrayList2.add(fundPrice);
        }

        ArrayList<FundPrice> fundPriceArrayList3=new ArrayList<>();
        for(FundPricePo fundPricePo:fundPriceList3){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId( fundPricePo.getFundId());
            fundPrice.setFundPrice( fundPricePo.getTodayPrice());
            FundPo fundPo=fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundPrice.setFundName(fundName);
            }
            fundPrice.setFundDate( fundPricePo.getDate());
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

        ArrayList<FundPricePo> fundPriceList= (ArrayList<FundPricePo>) fundPriceDao.getFundInfo(fundId);


        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(FundPricePo fundPricePo:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            fundPrice.setFundId(fundPricePo.getFundId());
            fundPrice.setFundPrice(fundPricePo.getTodayPrice());
            fundPrice.setFundTotalMoney(fundPricePo.getTotalMoney());
            fundPrice.setFundInMoney(fundPricePo.getTodayInmoney());
            fundPrice.setFundOutMoney(fundPricePo.getTodayInmoney());
            fundPrice.setFundDate(fundPricePo.getDate());
            fundPriceArrayList.add(fundPrice);
            FundPo fundPo=fundDao.getFundInfo(fundPrice.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundPrice.setFundName(fundName);
            }
        }
        System.out.println(fundPriceArrayList);

        return fundPriceArrayList;
    }

    @Override
    public Integer buyFund(Integer userId, BigDecimal traderMoney, Integer fundId) {

        BigDecimal managementFee= managementFeeDao.getManagementFee();
        BigDecimal managementCost=managementFee.multiply(traderMoney);
        BigDecimal fundPrice =BigDecimal.valueOf(-1);
        BigDecimal fundCount =BigDecimal.valueOf(-1);

        BigDecimal totalMoney=walletDao.totalCount(userId,"USDT");
        if(totalMoney.compareTo(traderMoney)>=0){
            synchronized (this){
                walletDao.sellCount(userId,"USDT",traderMoney,traderMoney);
                walletDao.updateMoney(userId,"FUND_"+fundId,traderMoney);
                fundTransactionDao.insertFundTransaction(userId,"BUY",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
                return 1;
            }
        }else {
            return -1;
        }

        //整点时间定时更新insertFundTransaction 和walletDao中fund_id的price 和count；
        //以及walletDao扣除手续费之后的user_id下的tradermoney=今日price*fundcount

    }

    @Override
    public Integer sellFund(Integer userId, BigDecimal fundCount, Integer fundId) {
        BigDecimal managementFee = managementFeeDao.getManagementFee();
        BigDecimal totalCount = walletDao.totalCount(userId, "FUND_" + fundId);

        BigDecimal traderMoney = BigDecimal.valueOf(-1);
        BigDecimal fundPrice = BigDecimal.valueOf(-1);
        BigDecimal managementCost=managementFee.multiply(fundCount);

        if(totalCount.compareTo(fundCount)>=0){
            synchronized (this){
                walletDao.sellCount(userId,"FUND_"+fundId,fundCount, BigDecimal.valueOf(0));
                fundTransactionDao.insertFundTransaction(userId,"SELL",0,traderMoney,fundId,fundPrice,fundCount,managementFee,managementCost);
                return 1;
            }
        }else {
            return -1;
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

            if(f.getStatus()==0){
                fundTransaction.setTransactionStatus("待完成交易");

            }else if(f.getStatus()==-1){
                fundTransaction.setTransactionStatus("已撤销交易");
            }else if(f.getStatus()==1){
                fundTransaction.setTransactionStatus("完全成交");
            }else {
                fundTransaction.setTransactionStatus("");
            };
            fundTransaction.setFundCount(f.getFundCount());
            fundTransaction.setFundId(f.getFundId());

            FundPo fundPo=fundDao.getFundInfo(f.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundTransaction.setFundName(fundName);
            }
            fundTransaction.setTraderMoney(f.getTraderMoney());
            fundTransaction.setManagementCost(f.getManagementCost());
            fundTransaction.setTransactionDate(String.valueOf(f.getTransactionDate()));
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
            fundTransaction.setFundCount(f.getFundCount());
            fundTransaction.setFundId(f.getFundId());
            fundTransaction.setManagementCost(f.getManagementCost());
            fundTransaction.setTransactionDate(String.valueOf(f.getTransactionDate()));
            FundPo fundPo=fundDao.getFundInfo(f.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundTransaction.setFundName(fundName);
            }
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
            fundTransaction.setTransactionDate(String.valueOf(f.getTransactionDate()));
            FundPo fundPo=fundDao.getFundInfo(f.getFundId());
            if (fundPo!=null){
                String fundName=fundPo.getFundName();
                fundTransaction.setFundName(fundName);
            }
            fundTransactions.add(fundTransaction);

        }

        return fundTransactions;
    }

    @Override
    public void revokeFundTransaction(Integer transactionId,String type) {
        synchronized (this) {
            FundTransactionPo fundTransactionPo = fundTransactionDao.findRevokeFundTransactionInfo(transactionId, type, 0);
            String fundId="FUND_"+fundTransactionPo.getFundId();
            if(fundTransactionPo.getType().equalsIgnoreCase("BUY")) {
                walletDao.revokeMoney(fundTransactionPo.getUserId(), "USDT", fundTransactionPo.getTraderMoney());
                walletDao.revokeFund(fundTransactionPo.getUserId(), fundId, fundTransactionPo.getTraderMoney(), BigDecimal.valueOf(0));
            }if (fundTransactionPo.getType().equalsIgnoreCase("SELL")){
                walletDao.revokeFund(fundTransactionPo.getUserId(), fundId, BigDecimal.valueOf(0),fundTransactionPo.getFundCount());
            }
            fundTransactionDao.updateFundTransactionStatus(transactionId);

        }
    }



    @Override
    public BigDecimal totalMoney(Integer userId, String type) {

        return walletDao.totalCount(userId,type);
    }

    @Override
    public ArrayList<UserWallet> getFundsDetails(Integer userId) {

        ArrayList<UserWalletPo> userWalletPos=walletDao.getFundsDetails(userId);

        ArrayList<UserWallet>userWallets=new ArrayList<>();
        for (UserWalletPo userWalletPo:userWalletPos) {
            UserWallet userWallet = new UserWallet();

            if (userWalletPo.getType().equalsIgnoreCase("FUND:1")) {
                FundPo fundInfo = fundDao.getFundInfo(1);
                if (fundInfo!=null) {
                    userWallet.setType(fundInfo.getFundName());
                }
                userWallet.setMoney(userWalletPo.getCount());


            }
            if (userWalletPo.getType().equalsIgnoreCase("FUND_2")) {
                FundPo fundInfo = fundDao.getFundInfo(2);
                if (fundInfo!=null) {
                    userWallet.setType(fundInfo.getFundName());
                }
                userWallet.setMoney(userWalletPo.getCount());
            }
            if (userWalletPo.getType().equalsIgnoreCase("FUND_3")) {
                FundPo fundInfo = fundDao.getFundInfo(3);
                if (fundInfo!=null) {
                    userWallet.setType(fundInfo.getFundName());
                }
                userWallet.setMoney(userWalletPo.getCount());
            }
            if (userWalletPo.getType().equalsIgnoreCase("USDT")) {

                userWallet.setType("USDT");

                userWallet.setMoney(userWalletPo.getCount());
            }
            userWallets.add(userWallet);
        }
        return userWallets;

    }

    @Override
    public void sellMoney(Integer userId,String type ,BigDecimal money,BigDecimal count) {

        walletDao.sellMoney(userId,"USDT",money);
        curbExchangeDao.insertExchangeHist(userId,"",type,"USDT",count,0);
    }

    @Override
    public void buyMoney(Integer userId,String type ,BigDecimal money,BigDecimal count) {

//        walletDao.sellMoney(userId,"USDT",money);
        curbExchangeDao.insertExchangeHist(userId,"",type,"USDT",count,0);
    }

    @Override
    public ArrayList<CurbExchange> getTransactionHist(Integer userId,Integer page1,Integer page2) {
        ArrayList<CurbExchangePo> curbExchangePos=curbExchangeDao.getExchangeHist(userId,page1,page2);
        ArrayList<CurbExchange> curbExchanges=new ArrayList<>();

        for (CurbExchangePo curbExchangePo:curbExchangePos){
            CurbExchange curbExchange=new CurbExchange();
            curbExchange.setId(curbExchangePo.getId());
            curbExchange.setExchangeDate(curbExchangePo.getExchangeDate());
            curbExchange.setExchangePlatform(curbExchangePo.getExchangePlatform());
            if (curbExchangePo.getExchangeType().equalsIgnoreCase("buy")){
                curbExchange.setExchangeType("充值");
            }else if(curbExchangePo.getExchangeType().equalsIgnoreCase("sell")){
                curbExchange.setExchangeType("提现");
            }
            curbExchange.setType(curbExchangePo.getType());
            curbExchange.setMoney(curbExchangePo.getMoney());

            if (curbExchangePo.getStatus()==0){
                curbExchange.setStatus("正在交易中");
            }else if(curbExchangePo.getStatus()==-1){
                curbExchange.setStatus("交易失败，请联系客服");
            }else if(curbExchangePo.getStatus()==1){
                curbExchange.setStatus("交易完成");
            }
            curbExchanges.add(curbExchange);


        }
        return curbExchanges;
    }

    @Override
    public Integer getTransactionHistoryCount(Integer userId) {

        Integer exchangeHistCount =curbExchangeDao.getExchangeHistCount(userId);


        return exchangeHistCount;
    }
}
