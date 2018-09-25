package com.world.ico.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.SmsInfo;
import com.world.ico.service.SmsService;
import com.world.ico.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by lsb on 2018/9/19.
 */
@Service("SmsServiceImpl")
@Transactional
public class SmsServiceImpl extends BaseImpl implements SmsService{

    @Override
    public String sendSms(SmsInfo smsInfo) {

        String result = "";

        try {
            String url = "https://open.ucpaas.com/ol/sms/sendsms";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", smsInfo.getSid());
            jsonObject.put("token", smsInfo.getToken());
            jsonObject.put("appid", smsInfo.getAppid());
            jsonObject.put("templateid", smsInfo.getTemplateid());
            jsonObject.put("param", smsInfo.getParam());
            jsonObject.put("mobile", smsInfo.getMobile());
            jsonObject.put("uid", smsInfo.getUid());

            String body = jsonObject.toJSONString();

            System.out.println("body = " + body);

            result = HttpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
