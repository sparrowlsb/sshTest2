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


        return getSuccess(jsonObject, "");
    }


    @RequestMapping(value = "fundInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundInfo(HttpSession session) {

        JSONObject jsonObject = new JSONObject();
        fundService.getFundInfo(1);

        return getSuccess(jsonObject, "");
    }

    @RequestMapping(value = "fundNarrowInfo", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject fundNarrowInfo(HttpSession session) {

        JSONObject jsonObject = new JSONObject();
        fundService.getFundNarrowInfo();

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