package com.world.ico.controller;

import com.alibaba.fastjson.JSONObject;
import com.world.ico.dto.UserInfo;
import com.world.ico.service.LoginService;
import com.world.ico.service.UserInfoService;
import com.world.ico.service.serviceImpl.BaseImpl;
import com.world.ico.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hay on 2017/1/16.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseImpl {

    @Autowired
    private LoginService loginService;

    @Value("#{baseinfoMap}")
    private HashMap<String, String> baseinfoMap;

    @RequestMapping(method = RequestMethod.POST,value = "/image")
    @ResponseBody
    public Object uploadImage(MultipartFile image,HttpServletRequest request,HttpSession session) {
        Map<String,Object> result = new HashMap<>();
        try {
            JSONObject jsonObject=new JSONObject();
            String email= (String) session.getAttribute("email");
            if (email==null){
                return getError(jsonObject,"please login first");
            }
            Integer userId=loginService.findEmailIdByEmail(email);
            if (request instanceof MultipartHttpServletRequest && image != null){
                UploadUtil uploadImages = new UploadUtil();
                String path1 = baseinfoMap.get("imageuploadpath");
                String path2 = "";//保存的文件夹
                String bigImg = uploadImages.upLoadImage(request, image, path1, path2,userId);
                if (bigImg != null){
                    result.put("result",1);
                    result.put("url",bigImg);
                }else {
                    result.put("result",0);
                    result.put("message","上传错误");
                }
            }else {
                result.put("result",0);
                result.put("message","上传错误");
            }
        }catch (Exception e){
            result.put("result",0);
            result.put("message","上传错误");
        }
        return result;
    }
}
