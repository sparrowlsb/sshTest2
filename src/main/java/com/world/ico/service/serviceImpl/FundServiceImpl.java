package com.world.ico.service.serviceImpl;

import com.world.ico.dao.FundDao;
import com.world.ico.dto.FundPrice;
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


    @Override
    public List<FundPrice> getFundDailyPrice() {
        ArrayList<Object[]> fundPriceList= (ArrayList<Object[]>) fundDao.getFundDailyPrice();
        System.out.println(fundPriceList);
        ArrayList<FundPrice> fundPriceArrayList=new ArrayList<>();
        for(Object[] object:fundPriceList){
            FundPrice fundPrice=new FundPrice();
            System.out.println((Integer) object[0]);
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
    public void buyFund() {


    }

    @Override
    public void sellFund() {

    }
}
