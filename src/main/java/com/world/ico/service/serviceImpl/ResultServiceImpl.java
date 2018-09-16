package com.world.ico.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.EndResult;
import com.world.ico.fcm.myfcm;
import com.world.ico.service.ResultService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsb on 2017/3/20.
 */
@Service("ResultService")
@Transactional
public class ResultServiceImpl implements ResultService {
    @Override
    public List<EndResult> endlist(JSONArray array) {
        List<EndResult>endResults=new ArrayList<>();
        for (int i=0;i<array.size();i++){
            EndResult record=new EndResult();
            JSONObject object= (JSONObject) array.get(i);
            record.setDateTime(object.getString("time"));
            record.setProbability(Double.parseDouble(object.getString("value"))*100);
            record.setTimes(Integer.parseInt(object.getString("totle")));
            endResults.add(record);
        }
        myfcm fcm=new myfcm();
        fcm.beginfcm(endResults);
        return endResults;
    }
}
