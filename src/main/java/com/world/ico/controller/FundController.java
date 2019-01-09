package com.world.ico.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.FundTransaction;
import com.world.ico.dto.UserWallet;
import com.world.ico.service.FundService;
import com.world.ico.service.LoginService;
import com.world.ico.service.serviceImpl.BaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lsb on 2018/10/8.
 */
@Controller
@RequestMapping("/fund")
public class FundController extends BaseImpl {

    @Autowired
    private LoginService loginService;

    @Autowired
    private FundService fundService;


    @RequestMapping(value = "fundDailyPrice", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundDailyPrice(HttpSession session) {

        JSONObject jsonObject = new JSONObject();
        List<FundPrice> fundPriceArrayList=fundService.getFundDailyPrice();

        int i=0;
        HashMap<String,FundPrice>fundDailyPriceMap=new HashMap<>();
        for (int j=1;j<=3;j++){

            String fundId="fund"+j;
            fundDailyPriceMap.put(fundId,fundPriceArrayList.get(j-1));

        }
        String jsArr= JSON.toJSONString(fundDailyPriceMap, SerializerFeature.DisableCircularReferenceDetect);
        jsonObject=JSON.parseObject(jsArr);

        return getSuccess(jsonObject, "");
    }


    @RequestMapping(value = "fundInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundInfo(Integer fundId ,HttpSession session) {

        List<FundPrice> fundPriceArrayList=fundService.getFundInfo(fundId);

        String data[][]=new String[fundPriceArrayList.size()][6];
        int j=0;
        for (FundPrice fundPrice:fundPriceArrayList){
            data[j][0]=fundPrice.getFundDate();
            data[j][1]=String.valueOf(fundPrice.getFundName());
            data[j][2]=String.valueOf(fundPrice.getFundPrice());
            data[j][3]=String.valueOf(fundPrice.getFundTotalMoney());
            data[j][4]=String.valueOf(fundPrice.getFundInMoney());
            data[j][5]=String.valueOf(fundPrice.getFundOutMoney());

            j++;
            System.out.println("基金id："+fundPrice.getFundId()+"基金价格："+fundPrice.getFundPrice()+"基金交易量："+fundPrice.getFundTotalMoney()+"基金卖出："+fundPrice.getFundOutMoney()+"基金买入："+fundPrice.getFundInMoney()+"时间："+fundPrice.getFundDate());
        }

        return getDataSuccess(data, "");
    }

    @RequestMapping(value = "fundNarrowInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundNarrowInfo(HttpSession session) {

        JSONObject jsonObject = new JSONObject();
        HashMap<Integer,ArrayList<FundPrice>> fundPriceArrayMAP=fundService.getFundNarrowInfo();
        for (HashMap.Entry<Integer,ArrayList<FundPrice>> fundPrice2:fundPriceArrayMAP.entrySet()){
            System.out.print("基金id："+fundPrice2.getKey());
            List<FundPrice> fundPriceArrayList=fundPrice2.getValue();
            for (FundPrice fundPrice:fundPriceArrayList){
                System.out.println("基金id："+fundPrice.getFundId()+"基金价格："+fundPrice.getFundPrice()+"基金交易量："+fundPrice.getFundTotalMoney()+"基金卖出："+fundPrice.getFundOutMoney()+"基金买入："+fundPrice.getFundInMoney()+"时间："+fundPrice.getFundDate());
            }
        }
        return getSuccess(jsonObject, "");
    }

    @RequestMapping(value = "totalCount", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject totalMoney( String type, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }

        Integer userId=loginService.findEmailIdByEmail(email);
        BigDecimal totalcount=fundService.totalMoney(userId,type);

        jsonObject.put("totalcount",totalcount);
        return  getSuccess(jsonObject,"");
    }

    @RequestMapping(value = "fundsDetails", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundsDetails( HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }

        Integer userId=loginService.findEmailIdByEmail(email);
        ArrayList<UserWallet> userWallets=fundService.getFundsDetails(userId);


        jsonObject.put("fundsDetails",userWallets);

        jsonObject.put("totalPages",1);
        jsonObject.put("totalCount",4);

        return  getSuccess(jsonObject,"");
    }

    @RequestMapping(value = "buyfund", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject buyfund(@RequestBody FundTransaction fundTransaction, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        if (fundTransaction.getTraderMoney().compareTo(BigDecimal.valueOf(0.0))<=0){
            return  getError(jsonObject,"please entry the trader money");

        }
        if (fundTransaction.getTraderMoney().compareTo(BigDecimal.valueOf(10.0))<=0){
            return  getError(jsonObject,"the minimum trader money 10 USDT");
        }
        if (fundTransaction.getFundId()==0){
            return getError(jsonObject,"please entry the fundId");

        }
        Integer userId=loginService.findEmailIdByEmail(email);

        Integer result=fundService.buyFund(userId,fundTransaction.getTraderMoney(),fundTransaction.getFundId());
        if (result==-1){
            return  getError(jsonObject,  "please entry the right transaction money");
        }
        return  getSuccess(jsonObject,"");



    }

    @RequestMapping(value = "sellfund", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sellfund(@RequestBody FundTransaction fundTransaction, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        if (fundTransaction.getFundCount().compareTo(BigDecimal.valueOf(0))<=0){
            return  getError(jsonObject,"please entry the trader fund count");

        }
        if (fundTransaction.getFundId()==0){
            return getError(jsonObject,"please entry the fundId");

        }
        Integer userId=loginService.findEmailIdByEmail(email);
        Integer result=fundService.sellFund(userId,fundTransaction.getFundCount(),fundTransaction.getFundId());
        if (result==-1){
            return  getError(jsonObject,  "please entry the right transaction money");
        }

        return  getSuccess(jsonObject,"");

    }

    @RequestMapping(value = "getDailyBuyHistory", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getDailyBuyHistory(HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        Integer userId=loginService.findEmailIdByEmail(email);
        ArrayList<FundTransaction> buyFundHists=fundService.getDailyBuyFundTransaction(userId);

        String data[][]=new String[buyFundHists.size()][7];
        int j=0;
        for (FundTransaction fundHist:buyFundHists){
            data[j][0]= String.valueOf(fundHist.getId());
            data[j][1]=String.valueOf(fundHist.getType());
            data[j][2]=String.valueOf(fundHist.getFundId());
            data[j][3]=String.valueOf(fundHist.getFundName());
            data[j][4]=String.valueOf(fundHist.getTraderMoney());
            if(fundHist.getStatus()==0){
                data[j][5]="待完成交易...";
            }else if(fundHist.getStatus()==-1){
                data[j][5]="已撤销交易";
            }else if(fundHist.getStatus()==1){
                data[j][5]="完全成交";
            }else {
                data[j][5]="";
            };
            data[j][6]=String.valueOf(fundHist.getTransactionDate());
            j++;
        }
        return getDataSuccess(data, "");



    }

    @RequestMapping(value = "getDailySellHistory", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getDailySellHistory(HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        Integer userId=loginService.findEmailIdByEmail(email);
        ArrayList<FundTransaction> sellFundHists=fundService.getDailySellFundTransaction(userId);

        String data[][]=new String[sellFundHists.size()][7];
        int j=0;
        for (FundTransaction fundHist:sellFundHists){
            data[j][0]= String.valueOf(fundHist.getId());
            data[j][1]=String.valueOf(fundHist.getType());
            data[j][2]=String.valueOf(fundHist.getFundId());
            data[j][3]=String.valueOf(fundHist.getFundName());
            data[j][4]=String.valueOf(fundHist.getFundCount());
            if(fundHist.getStatus()==0){
                data[j][5]="待完成交易...";
            }else if(fundHist.getStatus()==-1){
                data[j][5]="已撤销交易";
            }else if(fundHist.getStatus()==1){
                data[j][5]="完全成交";
            }else {
                data[j][5]="";
            };
            data[j][6]=String.valueOf(fundHist.getTransactionDate());
            j++;
        }
        System.out.print("getDailySellHistory"+data);
        return getDataSuccess(data, "");


    }
    @RequestMapping(value = "revokefund", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject revokefund(@RequestBody FundTransaction fundTransaction, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }

        fundService.revokeFundTransaction(fundTransaction.getId(),fundTransaction.getType());
        return getSuccess(jsonObject,"" );


    }

    @RequestMapping(value = "getHistory", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getHistory(Integer page, HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        Integer userId=loginService.findEmailIdByEmail(email);
        Integer page1=5*(page-1);
        Integer page2=5;

        ArrayList<FundTransaction> fundHists=fundService.getFundHistory(userId,page1,page2);
        Integer fundCount=fundService.getFundHistoryCount(userId);

        Integer totalPages=(fundCount-1)/5+1;
        Integer currentPage=page;
        Integer pageSizes=5;
        Integer totalCount=fundCount;



        HashMap<String,ArrayList<FundTransaction>>histMap=new HashMap<>();
        histMap.put("HIST",fundHists);

        String jsArr= JSON.toJSONString(histMap, SerializerFeature.DisableCircularReferenceDetect);
        jsonObject=JSON.parseObject(jsArr);
        jsonObject.put("totalPages",totalPages);
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSizes);
        jsonObject.put("totalCount",totalCount);
        return getSuccess(jsonObject,"" );


    }
    @RequestMapping(value = "getSuccessHistory", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSuccessHistory(Integer fundId,HttpSession session) {
        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email==null){
            return getError(jsonObject,"please login first");

        }
        Integer userId=loginService.findEmailIdByEmail(email);

        ArrayList<FundTransaction> fundHists=fundService.getSuccessFundHistory(userId,fundId);

        String data[][]=new String[fundHists.size()][3];
        int j=0;
        for (FundTransaction fundTransaction:fundHists){
            data[j][0]=fundTransaction.getType();
            data[j][1]=fundTransaction.getTransactionDate();
            data[j][2]=String.valueOf(fundTransaction.getFundPrice());


            j++;

           }
        return getDataSuccess(data, "");


    }

}