package com.world.ico.service;

import com.alibaba.fastjson.JSONArray;
import com.world.ico.dto.EndResult;

import java.util.List;

/**
 * Created by lsb on 2017/3/20.
 */
public interface ResultService {
    public List<EndResult> endlist(JSONArray array);
}
