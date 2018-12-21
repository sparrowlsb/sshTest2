package com.world.ico.controller;

import com.world.ico.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hay on 2017/1/16.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(method = RequestMethod.POST,value = "/image")
    @ResponseBody
    public Object uploadImage(MultipartFile image,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        if (request instanceof MultipartHttpServletRequest && image != null){
            UploadUtil uploadImages = new UploadUtil();
            String path1 = request.getSession().getServletContext().getRealPath("/");  //上传的路径
            String path2 = "UploadFiles";//保存的文件夹
            String bigImg = uploadImages.upLoadImage(request, image, path1, path2);
            if (bigImg != null){
                result.put("result",1);
                result.put("url",bigImg);
            }else {
                result.put("result",0);
                result.put("message","上传错误");
            }
        }
        return result;
    }
}
