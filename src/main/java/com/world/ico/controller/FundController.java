package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.FundPrice;
import com.world.ico.dto.UserInfo;
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

        for (FundPrice fundPrice:fundPriceArrayList){
            System.out.println("基金id："+fundPrice.getFundId()+"基金价格："+fundPrice.getFundPrice()+"基金交易量："+fundPrice.getFundTotalMoney()+"基金卖出："+fundPrice.getFundOutMoney()+"基金买入："+fundPrice.getFundInMoney()+"时间："+fundPrice.getFundDate());
        }

        return getSuccess(jsonObject, "");
    }


    @RequestMapping(value = "fundInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundInfo(HttpSession session) {

        ArrayList<String> list = new ArrayList<>();
        List<FundPrice> fundPriceArrayList=fundService.getFundInfo(1);

        String data[][]=new String[fundPriceArrayList.size()][6];
        int j=0;
        for (FundPrice fundPrice:fundPriceArrayList){
            data[j][0]=fundPrice.getFundDate();
            data[j][1]=String.valueOf(fundPrice.getFundId());
            data[j][2]=String.valueOf(fundPrice.getFundPrice());
            data[j][3]=String.valueOf(fundPrice.getFundTotalMoney());
            data[j][4]=String.valueOf(fundPrice.getFundInMoney());
            data[j][5]=String.valueOf(fundPrice.getFundOutMoney());
            j++;
            System.out.println("基金id："+fundPrice.getFundId()+"基金价格："+fundPrice.getFundPrice()+"基金交易量："+fundPrice.getFundTotalMoney()+"基金卖出："+fundPrice.getFundOutMoney()+"基金买入："+fundPrice.getFundInMoney()+"时间："+fundPrice.getFundDate());
        }
        System.out.print(data);
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


    @RequestMapping(value = "buyfund", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject buyfund(@RequestBody UserInfo userInfo, HttpSession session) {


        JSONObject jsonObject = new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email.isEmpty()) {
            return getError(jsonObject, "no email please login first");

        }


        int userId = loginService.findEmailIdByEmail(email);
        if (userId == 0) {
            return getError(jsonObject, "no userID");

        }
        if (userInfo.getUserId() == userId && userInfo.getEmail().equals(email)) {

        }
        fundService.getFundNarrowInfo();

        return getSuccess(jsonObject, "");

    }

    @RequestMapping(value = "sellfund", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sellfund(@RequestBody UserInfo userInfo, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        fundService.getFundNarrowInfo();

        return getSuccess(jsonObject, "");

    }
}