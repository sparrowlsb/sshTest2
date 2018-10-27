package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
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

        if(userWallet.getSellMoney()<=0){
            return getError(jsonObject, "please sell >0 money");
        }
        Double totalMoney=fundService.totalMoney(userId,"RMB");
        if(totalMoney<userWallet.getSellMoney()){
            return getError(jsonObject, "total money not enough");
        }
        Double count= totalMoney-userWallet.getSellMoney();
        fundService.sellMoney(userId,"RMB",count);
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

        if(userWallet.getSellMoney()<=0){
            return getError(jsonObject, "please sell >0 money");
        }
        Double totalMoney=fundService.totalMoney(userId,"RMB");
        if(totalMoney<userWallet.getSellMoney()){
            return getError(jsonObject, "total money not enough");
        }
        Double count= totalMoney-userWallet.getSellMoney();
        fundService.sellMoney(userId,"RMB",count);
        return getSuccess(jsonObject,"" );


    }
}
