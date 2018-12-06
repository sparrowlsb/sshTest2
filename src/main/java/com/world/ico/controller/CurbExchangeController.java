package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.CurbExchange;
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

/**
 * Created by lsb on 2018/10/27.
 */

@Controller
@RequestMapping("/curb")
public class CurbExchangeController extends BaseImpl {
    @Autowired
    private LoginService loginService;

    @Autowired
    private FundService fundService;

    @RequestMapping(value = "sellMoney", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sellMoney(@RequestBody UserWallet userWallet, HttpSession session) {

        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email.isEmpty()){
            return getError(jsonObject,"please login first");

        }

        Integer userId=loginService.findEmailIdByEmail(email);

        if(userWallet.getSellMoney().compareTo(BigDecimal.valueOf(0.0))==-1){
            return getError(jsonObject, "please sell >0 money");
        }
        BigDecimal totalMoney=fundService.totalMoney(userId,"USDT");
        if(totalMoney.compareTo(userWallet.getSellMoney())==-1){
            return getError(jsonObject, "total money not enough");
        }
        BigDecimal count= userWallet.getSellMoney();
        fundService.sellMoney(userId,"SELL",userWallet.getSellMoney(),count);
        return getSuccess(jsonObject,"" );


    }

    @RequestMapping(value = "buyMoney", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject buyMoney(@RequestBody UserWallet userWallet, HttpSession session) {

        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email.isEmpty()){
            return getError(jsonObject,"please login first");

        }

        Integer userId=loginService.findEmailIdByEmail(email);

        fundService.buyMoney(userId,"BUY",userWallet.getSellMoney(),userWallet.getSellMoney());
        return getSuccess(jsonObject,"" );


    }

    @RequestMapping(value = "getHist", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getHist(Integer page,HttpSession session) {

        JSONObject jsonObject=new JSONObject();
        String email = (String) session.getAttribute("email");
        if (email.isEmpty()){
            return getError(jsonObject,"please login first");

        }
        Integer pageSize = 5;
        if (page == null){
            page = 1;
            pageSize = 99999;
        }

        Integer userId=loginService.findEmailIdByEmail(email);
        Integer page1=pageSize*(page-1);
        Integer page2=pageSize;

        if (userId!=0){

            ArrayList<CurbExchange> transactionHist=fundService.getTransactionHist(userId,page1,page2);
            Integer fundCount=fundService.getTransactionHistoryCount(userId);

            Integer totalPages=(fundCount-1)/pageSize+1;
            Integer currentPage=page;
            Integer pageSizes=pageSize;
            Integer totalCount=fundCount;


            jsonObject.put("transactionHist",transactionHist);

            jsonObject.put("totalPages",totalPages);
            jsonObject.put("currentPage",currentPage);
            jsonObject.put("pageSize",pageSizes);
            jsonObject.put("totalCount",totalCount);

        }
        return getSuccess(jsonObject,"" );


    }
}
