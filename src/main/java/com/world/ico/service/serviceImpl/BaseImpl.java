package com.world.ico.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by lsb on 2018/9/8.
 */
public abstract class BaseImpl {



    public JSONObject getSuccess(JSON obj, String message) {
        JSONObject json = new JSONObject();

        json.put("result", 1);
        json.put("data", obj);
        json.put("message", message);

        return json;
    }

    public JSONObject getSuccess(ArrayList obj, String message) {
        JSONObject json = new JSONObject();

        json.put("result", 1);
        json.put("data", obj);
        json.put("message", message);

        return json;
    }
    public JSONObject getDataSuccess(String[][] obj, String message) {
        JSONObject json = new JSONObject();

        json.put("result", 1);
        json.put("data", obj);
        json.put("message", message);

        return json;
    }

    public JSONObject getError(JSON obj,String message) {
        JSONObject json = new JSONObject();

        json.put("result", 0);
        json.put("data", obj);
        json.put("message", message);

        return json;
    }



}
